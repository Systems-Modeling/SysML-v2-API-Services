package org.omg.sysml.metamodel;

public interface Redefinition extends Subsetting, MofObject {
    Feature getRedefiningFeature();

    Feature getRedefinedFeature();
}