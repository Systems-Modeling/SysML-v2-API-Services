package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface Activity extends Definition, Behavior, MofObject {
    Collection<? extends ActionUsage> getAction();
}