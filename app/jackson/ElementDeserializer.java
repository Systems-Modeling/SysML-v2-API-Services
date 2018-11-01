package jackson;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import models.Element;
import models.Model;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.UUID;

public class ElementDeserializer extends StdDeserializer<Element> {
    private EntityManager entityManager;

    public ElementDeserializer(EntityManager entityManager) {
        super((Class<?>) null);
        this.entityManager = entityManager;
    }

    public ElementDeserializer() {
        this(null);
    }

    @Override
    public Element deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.currentToken() != JsonToken.START_OBJECT) {
            throw new JsonParseException(p, "Expected START_OBJECT. Received " + p.getCurrentName() + ".");
        }
        JsonToken token;
        Element mof = null;
        while ((token = p.nextToken()) != null && token != JsonToken.END_OBJECT) {
            if (mof == null && token == JsonToken.FIELD_NAME && "@id".equals(p.getCurrentName())) {
                p.nextToken();
                String id = p.getText();
                UUID uuid = UUID.fromString(id);
                mof = entityManager.find(Model.class, uuid);
                if (mof == null) {
                    mof = entityManager.find(Element.class, uuid);
                }
                if (mof == null) {
                    throw new IOException("Unable to find an object with id " + id);
                }
            }
        }
        return mof;
    }

    @Override
    public Object deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        return deserialize(p, ctxt);
    }
}