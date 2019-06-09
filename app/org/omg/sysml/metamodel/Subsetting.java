package org.omg.sysml.metamodel;

public interface Subsetting extends Generalization, MofObject {
    Feature getSubsettedFeature();

    Feature getSubsettingFeature();

    Feature getOwningFeature();
}