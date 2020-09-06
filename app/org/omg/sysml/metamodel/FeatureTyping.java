package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface FeatureTyping extends Generalization, MofObject {
    Feature getTypedFeature();

    Type getType();

    Feature getOwningFeature();
}