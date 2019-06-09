package org.omg.sysml.metamodel;

public interface FeatureMembership extends Membership, MofObject {
    Category getOwningCategory();

    Boolean getIsDerived();

    Boolean getIsReadOnly();

    Feature getMemberFeature();

    Feature getOwnedMemberFeature();

    Boolean getIsPart();

    Boolean getIsPort();

    FeatureDirectionKind getDirection();
}