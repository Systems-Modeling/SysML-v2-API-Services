package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import config.MetamodelProvider;
import jackson.JacksonHelper;
import jackson.JsonLdMofObjectAdornment;
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
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static jackson.JsonLdMofObjectAdornment.JSONLD_MIME_TYPE;

/**
 * @author Manas Bajaj
 * <p>
 * Controller for handling all API requests related to SysML v2 elements
 */
public class ElementController extends Controller {
    @Inject
    private MetamodelProvider metamodelProvider;

    @Inject
    private ElementService elementService;

    @Inject
    private Environment environment;

    public Result byId(String id) {
        UUID uuid = UUID.fromString(id);
        Optional<Element> element = elementService.getById(uuid);
        return element.map(e -> ok(Json.toJson(e))).orElseGet(Results::notFound);
    }

    public Result all() {
        List<Element> elements = elementService.getAll();
        return ok(JacksonHelper.collectionValueToTree(List.class, metamodelProvider.getImplementationClass(Element.class), elements));
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
                .map(set -> JacksonHelper.collectionValueToTree(Set.class, respondWithJsonLd ?
                        JsonLdMofObjectAdornment.class :
                        metamodelProvider.getImplementationClass(Element.class), set)
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
                false
        );
    }

    public Result getRootsByProjectIdCommitId(UUID projectId, UUID commitId, Http.Request request) {
        Set<Element> roots = elementService.getRootsByProjectIdCommitId(projectId, commitId);
        boolean respondWithJsonLd = respondWithJsonLd(request);
        return ok(JacksonHelper.collectionValueToTree(Set.class,
                respondWithJsonLd ? JsonLdMofObjectAdornment.class : metamodelProvider.getImplementationClass(Element.class),
                roots.stream()
                        .map(e -> respondWithJsonLd ? adornMofObject(e, request, metamodelProvider, environment, projectId, commitId) : e)
                        .collect(Collectors.toSet())
        ));
    }

    static boolean respondWithJsonLd(Http.Request request) {
        return request.accepts(JSONLD_MIME_TYPE);
    }
}
