/*
 * SysML v2 REST/HTTP Pilot Implementation
 * Copyright (C) 2020 InterCAX LLC
 * Copyright (C) 2020 California Institute of Technology ("Caltech")
 * Copyright (C) 2022 Twingineer LLC
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
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.time.Instant;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

public abstract class BaseController extends Controller {

    protected static char CURSOR_SEPARATOR = '|';
    protected static int DEFAULT_PAGE_SIZE = 100;

    private static String stringFromCursor(String cursor) throws IllegalArgumentException {
        byte[] decoded = Base64.getUrlDecoder().decode(cursor);
        int separatorIndex = Bytes.indexOf(decoded, (byte) CURSOR_SEPARATOR);
        if (separatorIndex < 0) {
            throw new IllegalArgumentException("Provided cursor is malformed");
        }
        return new String(decoded, separatorIndex + 1, decoded.length - separatorIndex - 1);
    }

    private static String stringToCursor(String string) {
        String unencoded = String.valueOf(Instant.now().toEpochMilli()) +
                CURSOR_SEPARATOR +
                string;
        return Base64.getUrlEncoder().withoutPadding().encodeToString(unencoded.getBytes());
    }

    private static UUID uuidFromCursor(String cursor) throws IllegalArgumentException {
        return UUID.fromString(stringFromCursor(cursor));
    }

    private static String uuidToCursor(UUID id) throws IllegalArgumentException {
        return stringToCursor(id.toString());
    }

    protected static class PageRequest<T> {
        private final T after;
        private final T before;
        private final int size;

        private PageRequest(T after, T before, int size) {
            if (size <= 0) {
                throw new IllegalArgumentException("Page size must be greater than zero");
            }

            this.after = after;
            this.before = before;
            this.size = size;
        }

        public T getAfter() {
            return after;
        }

        public T getBefore() {
            return before;
        }

        public int getSize() {
            return size;
        }
    }

    protected static PageRequest<UUID> uuidRequest(Http.Request request) throws IllegalArgumentException {
        return request(request, BaseController::uuidFromCursor);
    }

    protected static PageRequest<String> stringRequest(Http.Request request) throws IllegalArgumentException {
        return request(request, BaseController::stringFromCursor);
    }

    private static <T> PageRequest<T> request(Http.Request request, Function<String, T> decoder) throws IllegalArgumentException {
        T after = Optional.ofNullable(request.getQueryString("page[after]"))
                .map(decoder)
                .orElse(null);
        T before = Optional.ofNullable(request.getQueryString("page[before]"))
                .map(decoder)
                .orElse(null);
        int size = Optional.ofNullable(request.getQueryString("page[size]"))
                .map(Integer::parseInt)
                .orElse(DEFAULT_PAGE_SIZE);
        return new PageRequest<>(after, before, size);
    }

    protected static Result uuidResponse(Result result, int resultSize, Function<Integer, UUID> idAtIndex, Http.Request request, PageRequest<UUID> pageRequest) {
        return response(result, resultSize, idAtIndex, request, pageRequest, BaseController::uuidToCursor);
    }

    protected static Result stringResponse(Result result, int resultSize, Function<Integer, String> idAtIndex, Http.Request request, PageRequest<String> pageRequest) {
        return response(result, resultSize, idAtIndex, request, pageRequest, BaseController::stringToCursor);
    }

    private static <T> Result response(Result result, int resultSize, Function<Integer, T> idAtIndex, Http.Request request, PageRequest<T> pageRequest, Function<T, String> encoder) {
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
                        encoder.apply(idAtIndex.apply(resultSize - 1)),
                        pageRequest.getSize()));
                if (hasPrev) {
                    linkHeaderValueBuilder.append(", ");
                }
            }
            if (hasPrev) {
                linkHeaderValueBuilder.append(String.format("<http://%s%s?page[before]=%s&page[size]=%s>; rel=\"prev\"",
                        request.host(),
                        request.path(),
                        encoder.apply(idAtIndex.apply(0)),
                        pageRequest.getSize()));
            }
            if (linkHeaderValueBuilder.length() > 0) {
                result = result.withHeader("Link", linkHeaderValueBuilder.toString());
            }
        }
        return result;
    }
}
