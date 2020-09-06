package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface RenderingDefinition extends PartDefinition, MofObject {
    Collection<? extends RenderingUsage> getRendering();
}