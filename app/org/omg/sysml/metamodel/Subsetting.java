package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface Subsetting extends Generalization, MofObject {
    Feature getSubsettedFeature();

    Feature getSubsettingFeature();

    Feature getOwningFeature();
}