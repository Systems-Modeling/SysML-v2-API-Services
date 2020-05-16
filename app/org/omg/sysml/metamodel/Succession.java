package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface Succession extends Connector {
    Step getTransitionStep();

    Collection<? extends Step> getTriggerStep();

    Collection<? extends Step> getEffectStep();

    Collection<? extends Expression> getGuardExpression();
}