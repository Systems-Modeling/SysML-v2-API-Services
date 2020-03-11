package jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.omg.sysml.metamodel.MofObject;

import javax.persistence.PersistenceException;
import java.io.IOException;

public abstract class JpaIdentitySerializer<T> extends StdSerializer<T> {
    public JpaIdentitySerializer() {
        this(null);
    }

    public JpaIdentitySerializer(Class<T> clazz) {
        super(clazz);
    }

    protected abstract String getIdentityField();

    protected abstract Object serializeToIdentity(T t);

    @Override
    public void serialize(T value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        Object o = serializeToIdentity(value);
        if (o == null) {
            gen.writeNull();
            return;
        }
        gen.writeStartObject();
        gen.writeObjectField(getIdentityField(), o);
        gen.writeEndObject();
    }
}