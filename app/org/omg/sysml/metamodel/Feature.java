package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

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