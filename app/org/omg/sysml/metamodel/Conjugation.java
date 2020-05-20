package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface Conjugation extends Relationship, MofObject {
    Type getOriginalType();

    Type getConjugatedType();

    Type getOwningType();
}