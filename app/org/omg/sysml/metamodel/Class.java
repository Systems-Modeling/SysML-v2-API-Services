package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface Class extends Category, MofObject {
    Collection<? extends Superclassing> getOwnedSuperclassing();
}