package org.omg.sysml.metamodel;

import java.util.Collection;

public interface Class extends Category, MofObject {
    Collection<? extends Superclassing> getOwnedSuperclassing();
}