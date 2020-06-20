package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface AttributeUsage extends Usage, MofObject {
    Collection<? extends DataType> getAttributeDefinition();
}