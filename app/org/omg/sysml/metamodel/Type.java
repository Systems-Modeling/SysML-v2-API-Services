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

public interface Type extends Namespace, SysMLType {
    List<? extends Specialization> getOwnedSpecialization();

    List<? extends FeatureMembership> getOwnedFeatureMembership();

    List<? extends Feature> getFeature();

    List<? extends Feature> getOwnedFeature();

    List<? extends Feature> getInput();

    List<? extends Feature> getOutput();

    Boolean getIsAbstract();

    List<? extends Membership> getInheritedMembership();

    List<? extends Feature> getEndFeature();

    List<? extends Feature> getOwnedEndFeature();

    Boolean getIsSufficient();

    Conjugation getOwnedConjugator();

    Boolean getIsConjugated();

    List<? extends Feature> getInheritedFeature();

    Multiplicity getMultiplicity();

    List<? extends Type> getUnioningType();

    List<? extends Intersecting> getOwnedIntersecting();

    List<? extends Type> getIntersectingType();

    List<? extends Unioning> getOwnedUnioning();

    Collection<? extends Disjoining> getOwnedDisjoining();

    List<? extends FeatureMembership> getFeatureMembership();

    List<? extends Type> getDifferencingType();

    List<? extends Differencing> getOwnedDifferencing();

    List<? extends Feature> getDirectedFeature();
}