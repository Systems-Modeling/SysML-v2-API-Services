package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface RequirementUsage extends ConstraintUsage, MofObject {
    RequirementDefinition getRequirementDefinition();

    String getReqId();

    Collection<? extends String> getText();

    Collection<? extends ConstraintUsage> getRequiredConstraint();

    Collection<? extends ConstraintUsage> getAssumedConstraint();

    Usage getSubjectParameter();
}