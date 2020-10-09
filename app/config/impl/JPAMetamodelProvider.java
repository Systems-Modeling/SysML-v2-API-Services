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

package config.impl;

import config.MetamodelProvider;
import org.omg.sysml.metamodel.MofObject;
import org.omg.sysml.query.Constraint;
import org.omg.sysml.record.Record;
import org.reflections.Reflections;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JPAMetamodelProvider implements MetamodelProvider {
    private static final Set<Class<?>> INTERFACES = new HashSet<>();
    private static final Set<Class<?>> IMPLEMENTATION_CLASSES = new HashSet<>();

    static {
        List<Class<?>> roots = Arrays.asList(
                MofObject.class,
                Record.class,
                Constraint.class
        );
        String packageScope = "org.omg.sysml";

        INTERFACES.addAll(roots);
        Reflections reflections = new Reflections(packageScope);
        roots.stream()
                .map(reflections::getSubTypesOf)
                .flatMap(Set::stream)
                .forEach(c -> (c.isInterface() ? INTERFACES : IMPLEMENTATION_CLASSES).add(c));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Class<T> getInterface(Class<? extends T> implementationClass) {
        try {
            return (Class<T>) Class.forName(implementationClass.getName().replaceFirst("impl.(\\w+)Impl", "$1"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Class<? extends T> getImplementationClass(Class<T> intrface) {
        try {
            return (Class<? extends T>) Class.forName(intrface.getName().replaceFirst("(.*\\.)(\\w+)$", "$1impl.$2Impl"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<Class<?>> getAllInterfaces() {
        return INTERFACES;
    }

    @Override
    public Set<Class<?>> getAllImplementationClasses() {
        return IMPLEMENTATION_CLASSES;
    }

}
