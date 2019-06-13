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
        JsonToken token;
        MofObjectImpl mof = null;
        while ((token = p.nextToken()) != null && token != JsonToken.END_OBJECT) {
            if (mof == null && token == JsonToken.FIELD_NAME && "identifier".equals(p.getCurrentName())) {
                p.nextToken();
                Object id = p.getText();
                if ("java.util.UUID".endsWith("UUID")) {
                    id = java.util.UUID.fromString(id.toString());
                }
                mof = entityManager.find(MofObjectImpl.class, id);
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