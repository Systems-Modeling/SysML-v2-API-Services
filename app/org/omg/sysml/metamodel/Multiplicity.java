package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface Multiplicity extends Relationship, MofObject {
    Expression getLower();

    Expression getUpper();

    Feature getFeatureWithMultiplicity();
}