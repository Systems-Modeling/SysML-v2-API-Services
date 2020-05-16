package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface Usage extends Feature {
    Collection<? extends Usage> getNestedUsage();

    Usage getOwningUsage();

    Definition getOwningDefinition();

    Collection<? extends PortUsage> getNestedPort();

    Collection<? extends Property> getNestedProperty();

    Collection<? extends Property> getProperty();

    Collection<? extends ActionUsage> getNestedAction();

    Collection<? extends StateUsage> getNestedState();

    Collection<? extends ConstraintUsage> getNestedConstraint();

    Collection<? extends TransitionUsage> getNestedTransition();

    Collection<? extends RequirementUsage> getNestedRequirement();

    Collection<? extends FunctionUsage> getNestedFunction();
}