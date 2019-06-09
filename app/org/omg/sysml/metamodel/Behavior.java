package org.omg.sysml.metamodel;

import java.util.Collection;

public interface Behavior extends Class, MofObject {
    Collection<? extends Step> getStep();

    Collection<? extends Feature> getInvolvesFeature();

    Collection<? extends Parameter> getParameter();
}