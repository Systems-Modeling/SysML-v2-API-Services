package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface Category extends Package, MofObject {
    List<? extends Generalization> getOwnedGeneralization();

    List<? extends FeatureMembership> getOwnedFeatureMembership();

    Collection<? extends Feature> getFeature();

    Collection<? extends Feature> getOwnedFeature();

    Collection<? extends Feature> getInput();

    Collection<? extends Feature> getOutput();

    Boolean getIsAbstract();

    List<? extends Membership> getInheritedMembership();
}