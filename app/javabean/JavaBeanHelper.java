package javabean;

import java.beans.*;
import java.lang.reflect.InvocationTargetException;
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
            return Arrays.stream(Introspector.getBeanInfo(bean.getClass(), Object.class).getPropertyDescriptors())
                    .collect(Collectors.toMap(PropertyDescriptor::getName, Function.identity()));
        } catch (IntrospectionException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object getBeanPropertyValue(Object bean, PropertyDescriptor property) {
        Objects.requireNonNull(property.getReadMethod());
        try {
            return property.getReadMethod().invoke(bean);
        } catch (InvocationTargetException | IllegalAccessException ignored) {
            return null;
        }
    }

    // https://stackoverflow.com/a/8524043
    public static Map<String, Object> getBeanPropertyValues(Object bean) {
        return getBeanProperties(bean).values().stream()
                .filter(pd -> Objects.nonNull(pd.getReadMethod()))
                .collect(HashMap::new, (map, pd) -> map.put(pd.getName(), getBeanPropertyValue(bean, pd)), Map::putAll);
    }

    public static Map<Method, Method> getBeanGettersSetters(Object bean) {
        return getBeanProperties(bean).values().stream()
                .filter(pd -> Objects.nonNull(pd.getReadMethod()))
                .collect(HashMap::new, (map, pd) -> map.put(pd.getReadMethod(), pd.getWriteMethod()), Map::putAll);
    }

    @SuppressWarnings("unchecked")
    public static <C> C convert(String text, Class<C> targetType) {
        PropertyEditor editor = PropertyEditorManager.findEditor(targetType);
        editor.setAsText(text);
        return (C) editor.getValue();
    }
}
