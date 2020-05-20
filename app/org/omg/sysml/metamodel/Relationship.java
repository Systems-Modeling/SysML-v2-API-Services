package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface Relationship extends Element, MofObject {
    Collection<? extends Element> getRelatedElement();

    Collection<? extends Element> getTarget();

    Collection<? extends Element> getSource();

    Element getOwningRelatedElement();

    Collection<? extends Element> getOwnedRelatedElement();
}