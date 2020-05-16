package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface Generalization extends Relationship {
    Type getOwningType();

    Type getGeneral();

    Type getSpecific();
}