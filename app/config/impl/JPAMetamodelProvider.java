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
    private static Set<Class<?>> INTERFACES = new HashSet<>(), IMPLEMENTATION_CLASSES = new HashSet<>();

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

/*

        List<String> interfacePackages = Arrays.asList(
                "org.omg.sysml.internal",
                "org.omg.sysml.lifecycle",
                "org.omg.sysml.metamodel",
                "org.omg.sysml.query",
                "org.omg.sysml.record"
        );
        List<String> implementationPackages = interfacePackages.stream().map(i -> i + ".impl").collect(Collectors.toList());

        interfacePackages.stream().map(Reflections::new).flatMap(ref -> ref.getSubTypesOf(Object.class).stream()).forEach(INTERFACES::add);
        implementationPackages.stream().map(Reflections::new).flatMap(ref -> ref.getSubTypesOf(Object.class).stream()).forEach(IMPLEMENTATION_CLASSES::add);*/
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
