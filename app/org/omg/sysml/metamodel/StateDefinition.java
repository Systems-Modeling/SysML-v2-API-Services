package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface StateDefinition extends Definition, Behavior, MofObject {
    Collection<? extends StateUsage> getState();
}