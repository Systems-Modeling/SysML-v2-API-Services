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
