package jackson;

import com.fasterxml.jackson.databind.*;
import play.libs.Json;

import java.io.IOException;
import java.util.Collection;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class JacksonHelper {
    // Java type erasure necessitates explicitly specifying JavaType for Collections. See https://github.com/FasterXML/jackson-databind/issues/23#issuecomment-6251193.
    public static JsonNode collectionToTree(Collection<?> collection, @SuppressWarnings("rawtypes") Class<? extends Collection> collectionClass, Class<?> elementClass) {
        return collectionToTree(collection, collectionClass, elementClass, Json::mapper, UnaryOperator.identity());
    }

    public static JsonNode collectionToTree(Collection<?> collection, @SuppressWarnings("rawtypes") Class<? extends Collection> collectionClass, Class<?> elementClass, Supplier<ObjectMapper> mapperSupplier, UnaryOperator<ObjectWriter> writerOperator) {
        JavaType javaType = mapperSupplier.get().getTypeFactory().constructCollectionType(collectionClass, elementClass);
        return objectToTree(collection, mapperSupplier, mapper -> writerOperator.apply(mapper.writerFor(javaType)), mapper -> mapper.readerFor(javaType));
    }

    public static JsonNode objectToTree(Object o, Supplier<ObjectMapper> mapperSupplier, Function<ObjectMapper, ObjectWriter> writerFunction, Function<ObjectMapper, ObjectReader> readerFunction) {
        ObjectMapper mapper = mapperSupplier.get();
        try {
            return readerFunction.apply(mapper).readTree(writerFunction.apply(mapper).writeValueAsString(o));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
