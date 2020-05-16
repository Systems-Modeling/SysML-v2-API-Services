package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface RequirementDefinition extends ConstraintDefinition {
    Parameter getSubjectParameter();

    String getReqId();

    String getText();

    Collection<? extends ConstraintUsage> getAssumedConstraint();

    Collection<? extends ConstraintUsage> getRequiredConstraint();
}