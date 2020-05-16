package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface PortUsage extends Usage {
    PortDefinition getPortDefinition();

    Definition getPortOwningDefinition();

    Usage getPortOwningUsage();
}