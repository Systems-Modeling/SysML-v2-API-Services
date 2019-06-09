package org.omg.sysml.metamodel;

import java.util.Collection;

public interface Ownership extends Relationship, MofObject {
    Collection<? extends Element> getSource();

    Collection<? extends Element> getTarget();
}