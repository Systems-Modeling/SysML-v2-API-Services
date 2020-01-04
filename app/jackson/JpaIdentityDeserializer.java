package jackson;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;

import javax.persistence.EntityManager;
import java.io.IOException;

public abstract class JpaIdentityDeserializer<T> extends StdDeserializer<T> {
    private EntityManager entityManager;

    public JpaIdentityDeserializer(EntityManager entityManager) {
        super((Class<?>) null);
        this.entityManager = entityManager;
    }

    public JpaIdentityDeserializer() {
        this(null);
    }

    protected abstract boolean isIdentityField(String field);

    protected abstract T deserializeFromIdentity(JsonParser parser) throws IOException;

    @Override
    public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.currentToken() != JsonToken.START_OBJECT) {
            throw new JsonParseException(p, "Expected START_OBJECT. Received " + p.getCurrentName() + ".");
        }
        JsonToken token;
        while ((token = p.nextToken()) != null && token != JsonToken.END_OBJECT) {
            if (token == JsonToken.FIELD_NAME && isIdentityField(p.getCurrentName())) {
                if (p.nextToken() == null) {
                    throw new JsonParseException(p, "No value for identity field.");
                }
                return deserializeFromIdentity(p);
            }
        }
        return null;
    }

    @Override
    public Object deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        return deserialize(p, ctxt);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}