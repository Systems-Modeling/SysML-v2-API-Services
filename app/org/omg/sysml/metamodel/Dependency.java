package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface Dependency extends Relationship, MofObject {
    Collection<? extends Element> getClient();

    Collection<? extends Element> getSupplier();
}