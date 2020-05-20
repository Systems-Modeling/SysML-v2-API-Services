package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface StateDefinition extends Activity, MofObject {
    Collection<? extends StateUsage> getState();

    ActionUsage getEntryAction();

    ActionUsage getDoAction();

    ActionUsage getExitAction();
}