package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface TransitionUsage extends ActionUsage, MofObject {
    ActionUsage getSource();

    ActionUsage getTarget();

    Collection<? extends AcceptActionUsage> getTriggerAction();

    Collection<? extends Expression> getGuardExpression();

    Collection<? extends ActionUsage> getEffectAction();

    Succession getSuccession();
}