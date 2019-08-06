package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface Ownership extends Relationship, MofObject {
    Collection<? extends Element> getSource();

    Collection<? extends Element> getTarget();
}