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

    Collection<? extends Usage> getFlowFeature();

    Collection<? extends CaseUsage> getNestedCase();

    Collection<? extends AnalysisCaseUsage> getNestedAnalysisCase();

    Collection<? extends VariantMembership> getVariantMembership();

    Collection<? extends Usage> getUsage();

    Collection<? extends Usage> getVariant();

    Collection<? extends ReferenceUsage> getNestedReference();

    Collection<? extends ConnectionUsage> getNestedConnection();

    Collection<? extends ItemUsage> getNestedItem();

    Collection<? extends PartUsage> getNestedPart();

    Collection<? extends InterfaceUsage> getNestedInterface();

    Collection<? extends AttributeUsage> getNestedAttribute();

    Collection<? extends ViewUsage> getNestedView();

    Collection<? extends ViewpointUsage> getNestedViewpoint();

    Collection<? extends RenderingUsage> getNestedRendering();

    Collection<? extends VerificationCaseUsage> getNestedVerificationCase();

    Collection<? extends EnumerationUsage> getNestedEnumeration();

    Collection<? extends AllocationUsage> getNestedAllocation();

    Collection<? extends ConcernUsage> getNestedConcern();

    Collection<? extends StakeholderUsage> getNestedStakeholder();

    Collection<? extends OccurrenceUsage> getNestedOccurrence();

    List<? extends Classifier> getDefinition();
}