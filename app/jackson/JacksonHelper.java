package jackson;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;

import java.io.IOException;
import java.util.Collection;

public class JacksonHelper {
    // Java type erasure necessitates explicitly specifying JavaType for Collections. See https://github.com/FasterXML/jackson-databind/issues/23#issuecomment-6251193.
    public static JsonNode collectionValueToTree(@SuppressWarnings("rawtypes") Class<? extends Collection> collectionClass, Class<?> elementClass, Collection<?> collection) {
        JavaType javaType = Json.mapper().getTypeFactory().constructCollectionType(collectionClass, elementClass);
        try {
            return Json.mapper().readerFor(javaType).readTree(Json.mapper().writerFor(javaType).writeValueAsString(collection));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
