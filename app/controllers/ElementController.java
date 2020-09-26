package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import config.MetamodelProvider;
import jackson.JacksonHelper;
import jackson.JsonLdMofObjectAdornment;
import jackson.filter.AllowedPropertyFilter;
import jackson.filter.DynamicFilterMixin;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.MofObject;
import play.Environment;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import services.ElementService;

import javax.inject.Inject;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static jackson.JsonLdMofObjectAdornment.JSONLD_MIME_TYPE;

public class ElementController extends Controller {

    private final ElementService elementService;
    private final MetamodelProvider metamodelProvider;
    private final Environment environment;

    @Inject
    public ElementController(ElementService elementService, MetamodelProvider metamodelProvider, Environment environment) {
        this.elementService = elementService;
        this.metamodelProvider = metamodelProvider;
        this.environment = environment;
    }

    private static final boolean INLINE_JSON_LD_CONTEXT_DEFAULT = true;
    private static final boolean INLINE_JSON_LD_CONTEXT = Optional.ofNullable(System.getenv("INLINE_JSON_LD_CONTEXT"))
            .map(Boolean::parseBoolean).orElse(INLINE_JSON_LD_CONTEXT_DEFAULT);

    public Result byId(String id) {
        UUID uuid = UUID.fromString(id);
        Optional<Element> element = elementService.getById(uuid);
        return element.map(e -> ok(Json.toJson(e))).orElseGet(Results::notFound);
    }

    public Result all() {
        List<Element> elements = elementService.getAll();
        return ok(JacksonHelper.collectionToTree(elements, List.class, metamodelProvider.getImplementationClass(Element.class)));
    }

    public Result create(Http.Request request) {
        JsonNode requestBodyJson = request.body().asJson();
        MofObject requestedObject = Json.fromJson(requestBodyJson, metamodelProvider.getImplementationClass(MofObject.class));
        if (!(requestedObject instanceof Element)) {
            return Results.badRequest();
        }
        Optional<Element> responseElement = elementService.create(((Element) requestedObject));
        return responseElement.map(e -> created(Json.toJson(e))).orElseGet(Results::internalServerError);
    }

    public Result getElementsByProjectIdCommitId(UUID projectId, UUID commitId, Http.Request request) {
        Set<Element> elements = elementService.getElementsByProjectIdCommitId(projectId, commitId);
        boolean respondWithJsonLd = respondWithJsonLd(request);
        return Optional.of(
                elements.stream()
                        .map(e -> respondWithJsonLd ?
                                adornMofObject(e, request, metamodelProvider, environment, projectId, commitId) :
                                e
                        )
                        .collect(Collectors.toSet())
        )
                .map(set -> JacksonHelper.collectionToTree(set, Set.class, respondWithJsonLd ?
                        JsonLdMofObjectAdornment.class :
                        metamodelProvider.getImplementationClass(Element.class))
                )
                .map(Results::ok)
                .map(result -> respondWithJsonLd ? result.as(JSONLD_MIME_TYPE) : result)
                .orElseThrow();
    }

    public Result getElementByProjectIdCommitIdElementId(UUID projectId, UUID commitId, UUID elementId, Http.Request request) {
        Optional<Element> element = elementService.getElementsByProjectIdCommitIdElementId(projectId, commitId, elementId);
        boolean respondWithJsonLd = respondWithJsonLd(request);
        return element
                .map(e -> respondWithJsonLd ? adornMofObject(e, request, metamodelProvider, environment, projectId, commitId) : e)
                .map(Json::toJson)
                .map(Results::ok)
                .map(result -> respondWithJsonLd ? result.as(JSONLD_MIME_TYPE) : result)
                .orElseGet(Results::notFound);

    }

    static JsonLdMofObjectAdornment adornMofObject(MofObject mof, Http.Request request, MetamodelProvider metamodelProvider, Environment environment, UUID projectId, UUID commitId) {
        return new JsonLdMofObjectAdornment(mof, metamodelProvider, environment,
                String.format("http://%s", request.host()),
                String.format("/projects/%s/commits/%s/elements/", projectId, commitId),
                INLINE_JSON_LD_CONTEXT
        );
    }

    public Result getRootsByProjectIdCommitId(UUID projectId, UUID commitId, Http.Request request) {
        Set<Element> roots = elementService.getRootsByProjectIdCommitId(projectId, commitId);
        boolean respondWithJsonLd = respondWithJsonLd(request);
        return ok(JacksonHelper.collectionToTree(roots.stream()
                        .map(e -> respondWithJsonLd ? adornMofObject(e, request, metamodelProvider, environment, projectId, commitId) : e)
                        .collect(Collectors.toSet()), Set.class,
                respondWithJsonLd ? JsonLdMofObjectAdornment.class : metamodelProvider.getImplementationClass(Element.class)
        ));
    }

    public Result getQueryResultsByProjectIdCommitIdQueryId(UUID projectId, UUID commitId, UUID queryId, Http.Request request) {
        Entry<Set<Element>, AllowedPropertyFilter> results = elementService.getQueryResultsByProjectIdCommitIdQueryId(projectId, commitId, queryId);
        Set<Element> elements = results.getKey();
        AllowedPropertyFilter filter = results.getValue();
        boolean respondWithJsonLd = respondWithJsonLd(request);
        JsonNode json = JacksonHelper.collectionToTree(elements.stream()
                        .map(e -> respondWithJsonLd ? adornMofObject(e, request, metamodelProvider, environment, projectId, commitId) : e)
                        .collect(Collectors.toSet()), Set.class,
                respondWithJsonLd ? JsonLdMofObjectAdornment.class : metamodelProvider.getImplementationClass(Element.class),
                filter != null ? () -> Json.mapper().copy().addMixIn(MofObject.class, DynamicFilterMixin.class) : Json::mapper,
                filter != null ? writer -> writer.with(new SimpleFilterProvider().addFilter(DynamicFilterMixin.FILTER_NAME, filter)) : UnaryOperator.identity()
        );
        // Workaround for JSON always containing "@type"
        if (filter != null && !filter.getAllowedProperties().contains("@type")) {
            StreamSupport.stream(Spliterators.spliteratorUnknownSize(json.elements(), Spliterator.ORDERED), false)
                    .filter(n -> n instanceof ObjectNode)
                    .forEach(n -> ((ObjectNode) n).remove("@type"));
        }
        return ok(json);
    }

    static boolean respondWithJsonLd(Http.Request request) {
        return request.accepts(JSONLD_MIME_TYPE);
    }
}
