package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface ActionUsage extends Usage, Step, MofObject {
    Collection<? extends Behavior> getActivity();

    Definition getActionOwningDefinition();

    Usage getActionOwningUsage();
}