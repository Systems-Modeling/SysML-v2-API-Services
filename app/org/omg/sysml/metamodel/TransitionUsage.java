package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface TransitionUsage extends Step, Usage, MofObject {
    Usage getTransitionOwningUsage();

    Step getSource();

    Step getTarget();

    AcceptActionUsage getTriggerAction();

    Expression getGuardExpression();

    ActionUsage getEffectAction();

    Succession getSuccession();
}