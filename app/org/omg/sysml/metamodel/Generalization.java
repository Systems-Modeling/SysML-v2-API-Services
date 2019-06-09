package org.omg.sysml.metamodel;

public interface Generalization extends Relationship, MofObject {
    Category getOwningCategory();

    Category getGeneral();

    Category getSpecific();
}