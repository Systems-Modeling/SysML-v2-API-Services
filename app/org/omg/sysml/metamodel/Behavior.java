package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface Behavior extends Class, MofObject {
    Collection<? extends Step> getStep();

    Collection<? extends Feature> getInvolvesFeature();

    Collection<? extends Parameter> getParameter();
}