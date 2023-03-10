/*
 * SysML v2 REST/HTTP Pilot Implementation
 * Copyright (C) 2020 InterCAX LLC
 * Copyright (C) 2020 California Institute of Technology ("Caltech")
 * Copyright (C) 2021-2022 Twingineer LLC
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

public interface Definition extends Classifier, SysMLType {
    Boolean getIsVariation();

    Collection<? extends Usage> getVariant();

    Collection<? extends VariantMembership> getVariantMembership();

    List<? extends Usage> getUsage();

    List<? extends Usage> getDirectedUsage();

    List<? extends Usage> getOwnedUsage();

    List<? extends ReferenceUsage> getOwnedReference();

    List<? extends AttributeUsage> getOwnedAttribute();

    List<? extends EnumerationUsage> getOwnedEnumeration();

    List<? extends OccurrenceUsage> getOwnedOccurrence();

    List<? extends ItemUsage> getOwnedItem();

    List<? extends PartUsage> getOwnedPart();

    List<? extends PortUsage> getOwnedPort();

    List<? extends ConnectorAsUsage> getOwnedConnection();

    Collection<? extends FlowConnectionUsage> getOwnedFlow();

    List<? extends InterfaceUsage> getOwnedInterface();

    List<? extends AllocationUsage> getOwnedAllocation();

    List<? extends ActionUsage> getOwnedAction();

    List<? extends StateUsage> getOwnedState();

    Collection<? extends TransitionUsage> getOwnedTransition();

    List<? extends CalculationUsage> getOwnedCalculation();

    List<? extends ConstraintUsage> getOwnedConstraint();

    List<? extends RequirementUsage> getOwnedRequirement();

    Collection<? extends ConcernUsage> getOwnedConcern();

    List<? extends CaseUsage> getOwnedCase();

    List<? extends AnalysisCaseUsage> getOwnedAnalysisCase();

    List<? extends VerificationCaseUsage> getOwnedVerificationCase();

    List<? extends UseCaseUsage> getOwnedUseCase();

    List<? extends ViewUsage> getOwnedView();

    List<? extends ViewpointUsage> getOwnedViewpoint();

    List<? extends RenderingUsage> getOwnedRendering();

    List<? extends MetadataUsage> getOwnedMetadata();
}