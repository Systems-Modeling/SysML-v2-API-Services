package org.omg.sysml.metamodel;

import java.util.Collection;

public interface Feature extends Category, MofObject {
    Collection<? extends Category> getReferencedType();

    Category getOwningCategory();

    Boolean getIsUnique();

    Boolean getIsOrdered();

    Collection<? extends Category> getType();

    Collection<? extends Category> getOwnedType();

    Collection<? extends Redefinition> getOwnedRedefinition();

    Collection<? extends Subsetting> getOwnedSubsetting();

    FeatureMembership getOwningFeatureMembership();

    Boolean getIsComposite();

    FeatureValue getValuation();

    Multiplicity getMultiplicity();

    Collection<? extends FeatureTyping> getTyping();

    Boolean getIsNonunique();
}