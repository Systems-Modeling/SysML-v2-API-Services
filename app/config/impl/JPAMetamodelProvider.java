package config.impl;

import config.MetamodelProvider;
import org.omg.sysml.metamodel.MofObject;
import org.omg.sysml.metamodel.impl.MofObjectImpl;
import org.reflections.Reflections;

import java.util.HashSet;
import java.util.Set;

public class JPAMetamodelProvider implements MetamodelProvider {
    private static Set<Class<?>> INTERFACES = new HashSet<>(), IMPLEMENTATION_CLASSES = new HashSet<>();

    static {
        INTERFACES.add(MofObject.class);
        for (String pakkage : new String[]{"org.omg.sysml.metamodel", "org.omg.sysml.extension", "org.omg.sysml.versioning"}) {
            INTERFACES.addAll(new Reflections(pakkage).getSubTypesOf(MofObject.class));
        }
        IMPLEMENTATION_CLASSES.add(MofObjectImpl.class);
        for (String pakkage : new String[]{"org.omg.sysml.metamodel.impl", "org.omg.sysml.extension.impl", "org.omg.sysml.versioning.impl"}) {
            IMPLEMENTATION_CLASSES.addAll(new Reflections(pakkage).getSubTypesOf(MofObjectImpl.class));
        }
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
