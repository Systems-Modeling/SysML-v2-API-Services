package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface Type extends Package, MofObject {
    List<? extends Generalization> getOwnedGeneralization();

    List<? extends FeatureMembership> getOwnedFeatureMembership();

    Collection<? extends Feature> getFeature();

    Collection<? extends Feature> getOwnedFeature();

    Collection<? extends Feature> getInput();

    Collection<? extends Feature> getOutput();

    Boolean getIsAbstract();

    List<? extends Membership> getInheritedMembership();

    Collection<? extends Feature> getEndFeature();

    Collection<? extends Feature> getOwnedEndFeature();

    Boolean getIsSufficient();

    Conjugation getOwnedConjugator();

    Boolean getIsConjugated();

    Conjugation getConjugator();

    List<? extends FeatureMembership> getFeatureMembership();

    Collection<? extends Feature> getInheritedFeature();

    Multiplicity getMultiplicity();
}