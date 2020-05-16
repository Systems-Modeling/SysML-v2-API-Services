package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface StateUsage extends ActionUsage {
    Usage getStateOwningUsage();

    Definition getStateOwningDefinition();

    Collection<? extends Behavior> getStateDefinition();

    ActionUsage getEntryAction();

    ActionUsage getDoAction();

    ActionUsage getExitAction();
}