package org.omg.sysml.metamodel;

public interface FeatureValue extends Relationship, MofObject {
    Expression getValue();

    Feature getFeatureWithValue();
}