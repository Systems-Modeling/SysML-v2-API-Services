package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface ActionUsage extends Step, Usage, MofObject {
    Collection<? extends Behavior> getActionDefinition();
}