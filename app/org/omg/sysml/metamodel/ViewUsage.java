package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface ViewUsage extends PartUsage, MofObject {
    ViewDefinition getViewDefinition();

    Collection<? extends ViewpointUsage> getSatisfiedViewpoint();

    Collection<? extends Package> getExposedPackage();

    RenderingUsage getRendering();
}