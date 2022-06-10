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

public interface Feature extends Type, SysMLType {
    Type getOwningType();

    Boolean getIsUnique();

    Boolean getIsOrdered();

    List<? extends Type> getType();

    Collection<? extends Redefinition> getOwnedRedefinition();

    Collection<? extends Subsetting> getOwnedSubsetting();

    FeatureMembership getOwningFeatureMembership();

    Boolean getIsComposite();

    Boolean getIsEnd();

    Type getEndOwningType();

    List<? extends FeatureTyping> getOwnedTyping();

    List<? extends Type> getFeaturingType();

    List<? extends TypeFeaturing> getOwnedTypeFeaturing();

    Boolean getIsDerived();

    List<? extends Feature> getChainingFeature();

    Collection<? extends FeatureInverting> getOwnedFeatureInverting();

    List<? extends FeatureChaining> getOwnedFeatureChaining();

    Boolean getIsReadOnly();

    Boolean getIsPortion();

    FeatureDirectionKind getDirection();

    Boolean getIsNonunique();
}