package org.omg.sysml.metamodel;

public interface Multiplicity extends Relationship, MofObject {
    Expression getLower();

    Expression getUpper();

    Feature getFeatureWithMultiplicity();
}