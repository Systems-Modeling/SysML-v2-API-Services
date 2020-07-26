package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface Relationship extends Element, MofObject {
    List<? extends Element> getRelatedElement();

    List<? extends Element> getTarget();

    List<? extends Element> getSource();

    Element getOwningRelatedElement();

    List<? extends Element> getOwnedRelatedElement();
}