package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface ViewDefinition extends PartDefinition, MofObject {
    Collection<? extends ViewUsage> getView();

    Collection<? extends ViewpointUsage> getSatisfiedViewpoint();

    ModelQuery getModelQuery();

    RenderingUsage getRendering();
}