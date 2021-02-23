/*
 * SysML v2 REST/HTTP Pilot Implementation
 * Copyright (C) 2020  InterCAX LLC
 * Copyright (C) 2020  California Institute of Technology ("Caltech")
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 * @license LGPL-3.0-or-later <http://spdx.org/licenses/LGPL-3.0-or-later>
 */

package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface Definition extends Classifier, MofObject {
    Collection<? extends Usage> getOwnedUsage();

    Collection<? extends PortUsage> getOwnedPort();

    Collection<? extends Usage> getFlowFeature();

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

    Collection<? extends ViewUsage> getOwnedView();

    Collection<? extends ViewpointUsage> getOwnedViewpoint();

    Collection<? extends RenderingUsage> getOwnedRendering();

    Collection<? extends VerificationCaseUsage> getOwnedVerificationCase();

    Collection<? extends EnumerationUsage> getOwnedEnumeration();

    Collection<? extends AllocationUsage> getOwnedAllocation();
}