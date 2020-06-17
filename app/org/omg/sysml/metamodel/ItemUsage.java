package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface ItemUsage extends Usage, MofObject {
    Collection<? extends Class> getItemDefinition();
}