package javabean;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class JavaBeanHelper {
    public static Map<String, PropertyDescriptor> getBeanProperties(Object bean) {
        try {
            return Arrays.stream(Introspector.getBeanInfo(bean.getClass(), Object.class).getPropertyDescriptors()).collect(Collectors.toMap(PropertyDescriptor::getName, Function.identity()));
        } catch (IntrospectionException e) {
            throw new RuntimeException(e);
        }
    }

    // https://stackoverflow.com/a/8524043
    public static Map<String, Object> getBeanPropertyValues(Object bean) {
        return getBeanProperties(bean).values().stream().filter(pd -> Objects.nonNull(pd.getReadMethod())).collect(HashMap::new, (map, pd) -> {
            Object value = null;
            try {
                value = pd.getReadMethod().invoke(bean);
            } catch (Exception ignored) {
            }
            map.put(pd.getName(), value);
        }, Map::putAll);
    }

    public static Map<Method, Method> getBeanGettersSetters(Object bean) {
        return getBeanProperties(bean).values().stream().filter(pd -> Objects.nonNull(pd.getReadMethod())).collect(HashMap::new, (map, pd) -> map.put(pd.getReadMethod(), pd.getWriteMethod()), Map::putAll);
    }
}
