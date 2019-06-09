package org.omg.sysml.metamodel;

public interface FeatureTyping extends Generalization, MofObject {
    Feature getTypedFeature();

    Category getType();
}