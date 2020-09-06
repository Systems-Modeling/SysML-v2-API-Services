package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface Feature extends Type, MofObject {
    Collection<? extends Type> getReferencedType();

    Type getOwningType();

    Boolean getIsUnique();

    Boolean getIsOrdered();

    Collection<? extends Type> getType();

    Collection<? extends Type> getOwnedType();

    Collection<? extends Redefinition> getOwnedRedefinition();

    Collection<? extends Subsetting> getOwnedSubsetting();

    FeatureMembership getOwningFeatureMembership();

    Boolean getIsComposite();

    Boolean getIsEnd();

    Type getEndOwningType();

    Collection<? extends FeatureTyping> getOwnedTyping();

    Boolean getIsNonunique();
}