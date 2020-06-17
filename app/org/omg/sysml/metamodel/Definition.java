package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface Definition extends Classifier, MofObject {
    Collection<? extends Usage> getOwnedUsage();

    Collection<? extends PortUsage> getOwnedPort();

    Collection<? extends Usage> getFlow();

    Collection<? extends Usage> getUsage();

    Collection<? extends StateUsage> getOwnedState();

    Collection<? extends ConstraintUsage> getOwnedConstraint();

    Collection<? extends TransitionUsage> getOwnedTransition();

    Collection<? extends RequirementUsage> getOwnedRequirement();

    Collection<? extends CalculationUsage> getOwnedCalculation();

    Boolean getIsVariation();

    Collection<? extends VariantMembership> getVariantMembership();

    Collection<? extends AnalysisCaseUsage> getOwnedAnalysisCase();

    Collection<? extends Usage> getVariant();

    Collection<? extends CaseUsage> getOwnedCase();

    Collection<? extends ReferenceUsage> getOwnedReference();

    Collection<? extends ActionUsage> getOwnedAction();

    Collection<? extends ConnectionUsage> getOwnedConnection();

    Collection<? extends ItemUsage> getOwnedItem();

    Collection<? extends PartUsage> getOwnedPart();

    Collection<? extends IndividualUsage> getOwnedIndividual();

    Collection<? extends InterfaceUsage> getOwnedInterface();

    Collection<? extends AttributeUsage> getOwnedAttribute();
}