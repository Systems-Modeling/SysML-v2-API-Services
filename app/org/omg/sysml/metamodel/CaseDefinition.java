package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface CaseDefinition extends CalculationDefinition, MofObject {
    RequirementUsage getObjectiveRequirement();

    Parameter getSubjectParameter();
}