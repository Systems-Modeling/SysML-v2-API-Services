package org.omg.sysml.metamodel;

import java.util.Collection;

public interface AtomicInteraction extends MofObject {
    Collection<? extends Class> getItemType();
}