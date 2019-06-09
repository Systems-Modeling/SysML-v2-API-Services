package org.omg.sysml.metamodel;

public interface EndFeatureMembership extends FeatureMembership, MofObject {
    Association getOwningAssociation();
}