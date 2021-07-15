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

public interface Usage extends Feature, MofObject {
    List<? extends Usage> getNestedUsage();

    Usage getOwningUsage();

    Definition getOwningDefinition();

    List<? extends PortUsage> getNestedPort();

    List<? extends ActionUsage> getNestedAction();

    List<? extends StateUsage> getNestedState();

    List<? extends ConstraintUsage> getNestedConstraint();

    Collection<? extends TransitionUsage> getNestedTransition();

    List<? extends RequirementUsage> getNestedRequirement();

    List<? extends CalculationUsage> getNestedCalculation();

    Boolean getIsVariation();

    List<? extends Usage> getDirectedUsage();

    List<? extends CaseUsage> getNestedCase();

    List<? extends AnalysisCaseUsage> getNestedAnalysisCase();

    Collection<? extends VariantMembership> getVariantMembership();

    List<? extends Usage> getUsage();

    Collection<? extends Usage> getVariant();

    List<? extends ReferenceUsage> getNestedReference();

    List<? extends ConnectorAsUsage> getNestedConnection();

    List<? extends ItemUsage> getNestedItem();

    List<? extends PartUsage> getNestedPart();

    List<? extends InterfaceUsage> getNestedInterface();

    List<? extends AttributeUsage> getNestedAttribute();

    List<? extends ViewUsage> getNestedView();

    List<? extends ViewpointUsage> getNestedViewpoint();

    List<? extends RenderingUsage> getNestedRendering();

    List<? extends VerificationCaseUsage> getNestedVerificationCase();

    List<? extends EnumerationUsage> getNestedEnumeration();

    List<? extends AllocationUsage> getNestedAllocation();

    Collection<? extends ConcernUsage> getNestedConcern();

    List<? extends OccurrenceUsage> getNestedOccurrence();

    List<? extends Classifier> getDefinition();

    List<? extends UseCaseUsage> getNestedUseCase();

    Boolean getIsReference();

    Collection<? extends FlowConnectionUsage> getNestedFlow();
}