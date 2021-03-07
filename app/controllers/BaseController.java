/*
 * SysML v2 REST/HTTP Pilot Implementation
 * Copyright (C) 2020  InterCAX LLC
 * Copyright (C) 2020  California Institute of Technology ("Caltech")
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 * @license LGPL-3.0-or-later <http://spdx.org/licenses/LGPL-3.0-or-later>
 */

package controllers;

import com.google.common.primitives.Bytes;
import config.MetamodelProvider;
import jackson.JacksonHelper;
import jackson.JsonLdMofObjectAdornment;
import org.omg.sysml.metamodel.MofObject;
import play.Environment;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

import java.time.Instant;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import static jackson.JsonLdMofObjectAdornment.JSONLD_MIME_TYPE;

public abstract class BaseController extends Controller {

    protected static final boolean INLINE_JSON_LD_CONTEXT_DEFAULT = true;
    protected static final boolean INLINE_JSON_LD_CONTEXT = Optional.ofNullable(System.getenv("INLINE_JSON_LD_CONTEXT"))
            .map(Boolean::parseBoolean)
            .orElse(INLINE_JSON_LD_CONTEXT_DEFAULT);

    protected static char CURSOR_SEPARATOR = '|';
    protected static int DEFAULT_PAGE_SIZE = 100;

    protected static UUID fromCursor(String cursor) throws IllegalArgumentException {
        byte[] decoded = Base64.getUrlDecoder().decode(cursor);
        int separatorIndex = Bytes.indexOf(decoded, (byte) CURSOR_SEPARATOR);
        if (separatorIndex < 0) {
            throw new IllegalArgumentException("Cursor separator missing");
        }
        return UUID.fromString(
                new String(decoded, separatorIndex + 1, decoded.length - separatorIndex - 1)
        );
    }

    protected static String toCursor(UUID id) throws IllegalArgumentException {
        String unencoded = String.valueOf(Instant.now().toEpochMilli()) +
                CURSOR_SEPARATOR +
                id.toString();
        return Base64.getUrlEncoder().withoutPadding().encodeToString(unencoded.getBytes());
    }

    protected static class PageRequest {
        private final UUID after;
        private final UUID before;
        private final int size;

        private PageRequest(UUID after, UUID before, int size) {
            this.after = after;
            this.before = before;
            this.size = size;
        }

        public UUID getAfter() {
            return after;
        }

        public UUID getBefore() {
            return before;
        }

        public int getSize() {
            return size;
        }

        protected static PageRequest from(Http.Request request) throws IllegalArgumentException {
            UUID after = Optional.ofNullable(request.getQueryString("page[after]"))
                    .map(BaseController::fromCursor)
                    //.map(UUID::fromString)
                    .orElse(null);
            UUID before = Optional.ofNullable(request.getQueryString("page[before]"))
                    .map(BaseController::fromCursor)
                    //.map(UUID::fromString)
                    .orElse(null);
            int size = Optional.ofNullable(request.getQueryString("page[size]"))
                    .map(Integer::parseInt)
                    .orElse(DEFAULT_PAGE_SIZE);
            if (size <= 0) {
                throw new IllegalArgumentException("Page size must be greater than zero.");
            }
            return new PageRequest(after, before, size);
        }
    }

    protected static Result paginateResult(Result result, int resultSize, Function<Integer, UUID> idAtIndex, Http.Request request, PageRequest pageRequest) {
        if (resultSize > 0) {
            boolean pageFull = resultSize == pageRequest.getSize();
            boolean hasNext = pageFull || pageRequest.getBefore() != null;
            boolean hasPrev = pageFull && pageRequest.getBefore() != null || pageRequest.getAfter() != null;
            // hasPrev -> !pageFull || pageAfter != null
            StringBuilder linkHeaderValueBuilder = new StringBuilder();
            if (hasNext) {
                linkHeaderValueBuilder.append(String.format("<http://%s%s?page[after]=%s&page[size]=%s>; rel=\"next\"",
                        request.host(),
                        request.path(),
                        toCursor(idAtIndex.apply(resultSize - 1)),
                        pageRequest.getSize()));
                if (hasPrev) {
                    linkHeaderValueBuilder.append(", ");
                }
            }
            if (hasPrev) {
                linkHeaderValueBuilder.append(String.format("<http://%s%s?page[before]=%s&page[size]=%s>; rel=\"prev\"",
                        request.host(),
                        request.path(),
                        toCursor(idAtIndex.apply(0)),
                        pageRequest.getSize()));
            }
            if (linkHeaderValueBuilder.length() > 0) {
                result = result.withHeader("Link", linkHeaderValueBuilder.toString());
            }
        }
        return result;
    }

    protected static <X extends MofObject> Result buildResult(List<X> mofs, Class<X> clazz, Function<X, UUID> idFunction, UUID projectId, UUID commitId, Http.Request request, PageRequest pageRequest, MetamodelProvider metamodelProvider, Environment environment) {
        boolean respondWithJsonLd = respondWithJsonLd(request);
        return Optional.of(
                mofs.stream()
                        .map(e -> respondWithJsonLd ?
                                adornMofObject(e, request, metamodelProvider, environment, projectId, commitId) :
                                e
                        )
                        .collect(Collectors.toList())
        )
                .map(collection -> JacksonHelper.collectionToTree(collection, List.class, respondWithJsonLd ?
                        JsonLdMofObjectAdornment.class :
                        metamodelProvider.getImplementationClass(clazz))
                )
                .map(Results::ok)
                .map(result -> respondWithJsonLd ? result.as(JSONLD_MIME_TYPE) : result)
                .map(result -> paginateResult(
                        result,
                        mofs.size(),
                        idx -> idFunction.apply(mofs.get(idx)),
                        request,
                        pageRequest
                ))
                .orElseThrow();
    }

    protected static JsonLdMofObjectAdornment adornMofObject(MofObject mof, Http.Request request, MetamodelProvider metamodelProvider, Environment environment, UUID projectId, UUID commitId) {
        return new JsonLdMofObjectAdornment(mof, metamodelProvider, environment,
                String.format("http://%s", request.host()),
                String.format("/projects/%s/commits/%s/elements/", projectId, commitId),
                INLINE_JSON_LD_CONTEXT
        );
    }

    protected static boolean respondWithJsonLd(Http.Request request) {
        return request.accepts(JSONLD_MIME_TYPE);
    }
}
