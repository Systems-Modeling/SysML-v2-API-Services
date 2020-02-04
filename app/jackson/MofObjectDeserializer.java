package jackson;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import javax.persistence.EntityManager;
import org.omg.sysml.metamodel.impl.MofObjectImpl;
import java.io.IOException;
import java.util.UUID;

public class MofObjectDeserializer extends StdDeserializer<MofObjectImpl> {
    private EntityManager entityManager;

    public MofObjectDeserializer(EntityManager entityManager) {
        super((Class<?>) null);
        this.entityManager = entityManager;
    }

    public MofObjectDeserializer() {
        this(null);
    }

    @Override
    public MofObjectImpl deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.currentToken() != JsonToken.START_OBJECT) {
            throw new JsonParseException(p, "Expected START_OBJECT. Received " + p.getCurrentName() + ".");
        }
        MofObjectImpl mof;
        try {
            mof = (MofObjectImpl) ctxt.getContextualType().getRawClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new IOException(e);
        }

        JsonToken token;
        while ((token = p.nextToken()) != null && token != JsonToken.END_OBJECT) {
            if (token == JsonToken.FIELD_NAME && "identifier".equals(p.getCurrentName())) {
                p.nextToken();
                mof.setIdentifier(UUID.fromString(p.getText()));
            }
        }
        return mof;
    }

    @Override
    public Object deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        return deserialize(p, ctxt);
    }
}