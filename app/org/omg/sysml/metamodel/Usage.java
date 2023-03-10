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

public interface Usage extends Feature, SysMLType {
    Boolean getIsReference();

    Boolean getIsVariation();

    Collection<? extends Usage> getVariant();

    Collection<? extends VariantMembership> getVariantMembership();

    Definition getOwningDefinition();

    Usage getOwningUsage();

    List<? extends Classifier> getDefinition();

    List<? extends Usage> getUsage();

    List<? extends Usage> getDirectedUsage();

    List<? extends Usage> getNestedUsage();

    List<? extends ReferenceUsage> getNestedReference();

    List<? extends AttributeUsage> getNestedAttribute();

    List<? extends EnumerationUsage> getNestedEnumeration();

    List<? extends OccurrenceUsage> getNestedOccurrence();

    List<? extends ItemUsage> getNestedItem();

    List<? extends PartUsage> getNestedPart();

    List<? extends PortUsage> getNestedPort();

    List<? extends ConnectorAsUsage> getNestedConnection();

    Collection<? extends FlowConnectionUsage> getNestedFlow();

    List<? extends InterfaceUsage> getNestedInterface();

    List<? extends AllocationUsage> getNestedAllocation();

    List<? extends ActionUsage> getNestedAction();

    List<? extends StateUsage> getNestedState();

    Collection<? extends TransitionUsage> getNestedTransition();

    List<? extends CalculationUsage> getNestedCalculation();

    List<? extends ConstraintUsage> getNestedConstraint();

    List<? extends RequirementUsage> getNestedRequirement();

    Collection<? extends ConcernUsage> getNestedConcern();

    List<? extends CaseUsage> getNestedCase();

    List<? extends AnalysisCaseUsage> getNestedAnalysisCase();

    List<? extends VerificationCaseUsage> getNestedVerificationCase();

    List<? extends UseCaseUsage> getNestedUseCase();

    List<? extends ViewUsage> getNestedView();

    List<? extends ViewpointUsage> getNestedViewpoint();

    List<? extends RenderingUsage> getNestedRendering();

    List<? extends MetadataUsage> getNestedMetadata();
}