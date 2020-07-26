package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface ConnectionUsage extends Connector, PartUsage, MofObject {
    Collection<? extends Association> getConnectionDefinition();
}