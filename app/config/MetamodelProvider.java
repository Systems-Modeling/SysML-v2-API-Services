package config;

import java.util.Set;

public interface MetamodelProvider {
    <T> Class<? extends T> getImplementationClass(Class<T> intrface);

    <T> Class<T> getInterface(Class<? extends T> implementationClass) throws ClassNotFoundException;

    Set<Class<?>> getAllImplementationClasses();

    Set<Class<?>> getAllInterfaces();
}
