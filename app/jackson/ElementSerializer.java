package jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import models.Element;

import java.io.IOException;

public class ElementSerializer extends StdSerializer<Element> {
    public ElementSerializer() {
        this(null);
    }

    public ElementSerializer(Class<Element> clazz) {
        super(clazz);
    }

    @Override
    public void serialize(Element value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (value == null || value.getId() == null) {
            gen.writeNull();
            return;
        }
        gen.writeStartObject();
        gen.writeObjectField("@id", value.getId());
        gen.writeEndObject();
    }

    @Override
    public void serializeWithType(Element value, JsonGenerator gen,
                                  SerializerProvider provider, TypeSerializer typeSer)
            throws IOException {
        serialize(value, gen, provider);
    }
}