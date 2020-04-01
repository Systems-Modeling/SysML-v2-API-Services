package jackson;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import javassist.util.proxy.ProxyFactory;
import org.omg.sysml.metamodel.impl.MofObjectImpl;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MofObjectDeserializer extends StdDeserializer<MofObjectImpl> implements ContextualDeserializer {
    private EntityManager entityManager;
    private JavaType type;

    private static Map<Class<?>, Class<?>> PROXY_MAP = new HashMap<>();

    public MofObjectDeserializer(EntityManager entityManager) {
        this(entityManager, null);
    }

    public MofObjectDeserializer(EntityManager entityManager, JavaType type) {
        super((Class<?>) null);
        this.entityManager = entityManager;
        this.type = type;
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
        // Proxy class to handle abstract classes
        Class<?> proxyClass = PROXY_MAP.computeIfAbsent(type.getRawClass(), clazz -> {
            ProxyFactory factory = new ProxyFactory();
            factory.setSuperclass(clazz);
            return factory.createClass();
        });
        try {
            mof = (MofObjectImpl) proxyClass.getConstructor().newInstance();
            //mof = (MofObjectImpl) type.getRawClass().getConstructor().newInstance();
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

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) {
        //beanProperty is null when the type to deserialize is the top-level type or a generic type, not a type of a bean property
        JavaType type = ctxt.getContextualType() != null ? ctxt.getContextualType() : property.getMember().getType();
        return new MofObjectDeserializer(entityManager, type);
    }
}