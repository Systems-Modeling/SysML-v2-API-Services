package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface RenderingUsage extends PartUsage, MofObject {
    RenderingDefinition getRenderingDefinition();
}