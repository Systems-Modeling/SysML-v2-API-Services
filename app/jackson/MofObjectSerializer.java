package jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import javax.persistence.PersistenceException;
import org.omg.sysml.metamodel.MofObject;
import org.omg.sysml.metamodel.impl.MofObjectImpl;

import java.io.IOException;

public class MofObjectSerializer extends StdSerializer<MofObject> {
    public MofObjectSerializer() {
        this(null);
    }

    public MofObjectSerializer(Class<MofObject> clazz) {
        super(clazz);
    }

    @Override
    public void serialize(MofObject value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        try {
            if (value == null || value.getId() == null) {
                gen.writeNull();
                return;
            }
        } catch (PersistenceException e) {
            gen.writeNull();
            return;
        }
        gen.writeStartObject();
        // TODO Decide if @type and id should be exposed
        // gen.writeObjectField("@type", value.getClass().getSimpleName());
        // gen.writeObjectField("id", value.getId());
        if (value instanceof MofObjectImpl) {
            gen.writeObjectField("identifier", value.getIdentifier());
        }
        gen.writeEndObject();
    }
}