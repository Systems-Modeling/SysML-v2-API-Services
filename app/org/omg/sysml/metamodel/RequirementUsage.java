package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface RequirementUsage extends ConstraintUsage, MofObject {
    RequirementDefinition getRequirementDefinition();

    Parameter getSubjectParameter();

    String getReqId();

    String getText();

    Collection<? extends ConstraintUsage> getRequiredConstraint();

    Collection<? extends ConstraintUsage> getAssumedConstraint();
}