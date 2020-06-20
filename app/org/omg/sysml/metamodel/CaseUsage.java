package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface CaseUsage extends CalculationUsage, MofObject {
    RequirementUsage getObjectiveRequirement();

    Parameter getSubjectParameter();

    CaseDefinition getCaseDefinition();
}