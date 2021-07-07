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

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.omg.sysml.metamodel.MofObject;
import org.omg.sysml.metamodel.impl.MofObjectImpl;

import javax.persistence.PersistenceException;
import java.io.IOException;

// TODO inherit from JpaIdentitySerializer
public class MofObjectSerializer extends StdSerializer<MofObject> {
    public MofObjectSerializer() {
        this(null);
    }

    public MofObjectSerializer(Class<MofObject> clazz) {
        super(clazz);
    }

    @Override
    public void serialize(MofObject value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        try {
            if (value == null || value.getId_() == null) {
                gen.writeNull();
                return;
            }
        } catch (PersistenceException e) {
            gen.writeNull();
            return;
        }
        gen.writeStartObject();
        if (value instanceof MofObjectImpl) {
            gen.writeObjectField("@id", value.getId_());
        }
        gen.writeEndObject();
    }
}