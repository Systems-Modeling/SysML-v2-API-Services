package org.omg.sysml.metamodel;

public interface ParameterMembership extends FeatureMembership, MofObject {
    Parameter getMemberParameter();

    Parameter getOwnedMemberParameter();
}