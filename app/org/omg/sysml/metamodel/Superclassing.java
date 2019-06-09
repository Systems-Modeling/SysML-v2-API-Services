package org.omg.sysml.metamodel;

public interface Superclassing extends Generalization, MofObject {
    Class getSuperclass();

    Class getSubclass();

    Class getOwningClass();
}