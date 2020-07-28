package jackson;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.node.ObjectNode;
import config.MetamodelProvider;
import org.omg.sysml.metamodel.MofObject;
import org.omg.sysml.metamodel.impl.MofObjectImpl;
import play.Environment;
import play.libs.Json;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class JsonLdMofObjectAdornment {
    public static final String JSONLD_MIME_TYPE = "application/ld+json";

    private static final Map<String, JsonNode> CONTEXT_CACHE = new HashMap<>();

    private final MofObject mof;
    private final String type;
    private final JsonNode context;

    public JsonLdMofObjectAdornment(MofObject mof, MetamodelProvider metamodelProvider, Environment environment, String host, String basePath, boolean inline) {
        this.mof = mof;
        try {
            this.type = metamodelProvider.getInterface(mof.getClass()).getSimpleName();
        } catch (ClassNotFoundException | NullPointerException e) {
            throw new IllegalStateException(e);
        }
        ObjectNode contextObjectNode = Json.mapper().createObjectNode();
        if (basePath != null) {
            contextObjectNode.put("@base", URI.create(host).resolve(basePath).toString());
        }
        if (inline) {
            JsonNode cachedContext = CONTEXT_CACHE.computeIfAbsent(type, t -> Json.parse(environment.resourceAsStream(String.format("public/jsonld/%s.jsonld", t))));
            if (!(cachedContext instanceof ObjectNode)) {
                throw new IllegalStateException("context expected to be an ObjectNode");
            }
            contextObjectNode.setAll((ObjectNode) cachedContext.get("@context"));
        }
        else {
            contextObjectNode.put("@import", URI.create(host).resolve(String.format("/jsonld/%s.jsonld", type)).toString());
        }
        this.context = contextObjectNode;
    }

    @JsonUnwrapped
    @JsonDeserialize(as = MofObjectImpl.class)
    // JsonTypeInfo doesn't work in conjunction with JsonUnwrapped. See SerializationFeature#FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS.
    // @JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
    public MofObject getMof() {
        return mof;
    }

    @JsonProperty("@context")
    public JsonNode getContext() {
        return context;
    }

    @JsonProperty("@type")
    public String getType() {
        return type;
    }
}
