package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface RequirementConstraintMembership extends FeatureMembership, MofObject {
    RequirementConstraintKind getKind();

    ConstraintUsage getConstraint();
}