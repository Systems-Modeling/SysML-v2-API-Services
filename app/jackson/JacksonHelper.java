package jackson;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectWriter;
import play.libs.Json;

import java.io.IOException;
import java.util.Collection;
import java.util.function.Function;

public class JacksonHelper {
    public static JsonNode collectionValueToTree(@SuppressWarnings("rawtypes") Class<? extends Collection> collectionClass, Class<?> elementClass, Collection<?> collection) {
        return collectionValueToTree(collectionClass, elementClass, collection, Function.identity());
    }

    // Java type erasure necessitates explicitly specifying JavaType for Collections. See https://github.com/FasterXML/jackson-databind/issues/23#issuecomment-6251193.
    public static JsonNode collectionValueToTree(@SuppressWarnings("rawtypes") Class<? extends Collection> collectionClass, Class<?> elementClass, Collection<?> collection, Function<ObjectWriter, ObjectWriter> objectWriterFunction) {
        JavaType javaType = Json.mapper().getTypeFactory().constructCollectionType(collectionClass, elementClass);
        try {
            return Json.mapper().readerFor(javaType).readTree(objectWriterFunction.apply(Json.mapper().writerFor(javaType)).writeValueAsString(collection));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JsonNode valueToTree(Object value, Function<ObjectWriter, ObjectWriter> objectWriterFunction) {
        try {
            return Json.mapper().readTree(objectWriterFunction.apply(Json.mapper().writer()).writeValueAsBytes(value));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
