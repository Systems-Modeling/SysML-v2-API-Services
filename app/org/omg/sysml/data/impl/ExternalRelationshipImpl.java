/*
 * SysML v2 REST/HTTP Pilot Implementation
 * Copyright (C) 2020 InterCAX LLC
 * Copyright (C) 2020 California Institute of Technology ("Caltech")
 * Copyright (C) 2021 Twingineer LLC
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

package org.omg.sysml.data.impl;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jackson.DataDeserializer;
import jackson.DataSerializer;
import org.hibernate.annotations.Any;
import org.hibernate.annotations.FetchMode;
import org.omg.sysml.data.ExternalData;
import org.omg.sysml.data.ExternalRelationship;
import org.omg.sysml.lifecycle.impl.DataImpl;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.impl.ElementImpl;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "ExternalRelationshipImpl")
@SecondaryTable(name = "ExternalRelationship")
@org.hibernate.annotations.Table(appliesTo = "ExternalRelationship", fetch = FetchMode.SELECT, optional = false)
@DiscriminatorValue(value = "ExternalRelationship")
@JsonTypeName(value = "ExternalRelationship")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
public class ExternalRelationshipImpl extends DataImpl implements ExternalRelationship {

    private UUID id;

    @Column(name = "id", table = "ExternalRelationship")
    @JsonGetter(value = "@id")
    public UUID getId() {
        return id;
    }

    @JsonSetter(value = "@id")
    public void setId(UUID id) {
        this.id = id;
    }

    private String specification;

    @JsonGetter
    @Column(name = "specification", table = "ExternalRelationship")
    public String getSpecification() { return specification; }

    @JsonSetter
    public void setSpecification(String specification) {
        this.specification = specification;
    }

    private String language;

    @JsonGetter
    @Column(name = "language", table = "ExternalRelationship")
    public String getLanguage() { return language; }

    @JsonSetter
    public void setLanguage(String language) {
        this.language = language;
    }

    private Element elementEnd;

    @JsonGetter
    @JsonSerialize(using = DataSerializer.class)
    @Any(metaDef = "ElementMetaDef", metaColumn = @Column(name = "elementEndType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "elementEndId", table = "ExternalRelationship")
    public Element getElementEnd() {
        return elementEnd;
    }

    @JsonSetter
    @JsonDeserialize(using = DataDeserializer.class, as = ElementImpl.class)
    public void setElementEnd(Element elementEnd) {
        this.elementEnd = elementEnd;
    }

    private ExternalData externalDataEnd;

    @JsonGetter
    @JsonSerialize(using = DataSerializer.class)
    @ManyToOne(targetEntity = ExternalDataImpl.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "externalDataEndId", table = "ExternalRelationship")
    public ExternalData getExternalDataEnd() {
        return externalDataEnd;
    }

    @JsonSetter
    @JsonDeserialize(using = DataDeserializer.class, as = ExternalDataImpl.class)
    public void setExternalDataEnd(ExternalData externalDataEnd) {
        this.externalDataEnd = externalDataEnd;
    }
}
