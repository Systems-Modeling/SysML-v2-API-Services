package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface ActionDefinition extends Behavior, Definition, MofObject {
    Collection<? extends ActionUsage> getAction();
}