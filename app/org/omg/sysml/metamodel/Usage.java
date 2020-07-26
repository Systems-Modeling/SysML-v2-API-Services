package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface Usage extends Feature, MofObject {
    Collection<? extends Usage> getNestedUsage();

    Usage getOwningUsage();

    Definition getOwningDefinition();

    Collection<? extends PortUsage> getNestedPort();

    Collection<? extends ActionUsage> getNestedAction();

    Collection<? extends StateUsage> getNestedState();

    Collection<? extends ConstraintUsage> getNestedConstraint();

    Collection<? extends TransitionUsage> getNestedTransition();

    Collection<? extends RequirementUsage> getNestedRequirement();

    Collection<? extends CalculationUsage> getNestedCalculation();

    Boolean getIsVariation();

    Collection<? extends Usage> getFlow();

    Collection<? extends CaseUsage> getNestedCase();

    Collection<? extends AnalysisCaseUsage> getNestedAnalysisCase();

    Collection<? extends VariantMembership> getVariantMembership();

    Collection<? extends Usage> getUsage();

    Collection<? extends Usage> getVariant();

    Collection<? extends ReferenceUsage> getNestedReference();

    Collection<? extends ConnectionUsage> getNestedConnection();

    Collection<? extends ItemUsage> getNestedItem();

    Collection<? extends PartUsage> getNestedPart();

    Collection<? extends IndividualUsage> getNestedIndividual();

    Collection<? extends InterfaceUsage> getNestedInterface();

    Collection<? extends AttributeUsage> getNestedAttribute();
}