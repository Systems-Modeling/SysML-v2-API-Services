package org.omg.sysml.metamodel;

import java.util.Collection;

public interface Step extends Feature, MofObject {
    Collection<? extends Behavior> getType();
}