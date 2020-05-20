package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface Property extends Usage, MofObject {
    Definition getPropertyOwningDefinition();

    Usage getPropertyOwningUsage();
}