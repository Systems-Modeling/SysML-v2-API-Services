/*
 * SysML v2 REST/HTTP Pilot Implementation
 * Copyright (C) 2020  InterCAX LLC
 * Copyright (C) 2020  California Institute of Technology ("Caltech")
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 * @license LGPL-3.0-or-later <http://spdx.org/licenses/LGPL-3.0-or-later>
 */

package javabean;

import java.beans.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
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
        if (editor == null) {
            if (UUID.class.equals(targetType)) {
                PropertyEditorManager.registerEditor(UUID.class, UuidPropertyEditor.class);
                editor = PropertyEditorManager.findEditor(UUID.class);
            } else {
                return null;
            }
        }
        editor.setAsText(text);
        return (C) editor.getValue();
    }

}
