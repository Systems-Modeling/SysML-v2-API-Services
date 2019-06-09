package org.omg.sysml.metamodel;

public interface FeatureReferenceExpression extends Expression, MofObject {
    Feature getReferent();
}