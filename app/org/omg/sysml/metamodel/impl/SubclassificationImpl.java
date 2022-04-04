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

package org.omg.sysml.metamodel.impl;

import org.omg.sysml.metamodel.*;

import org.omg.sysml.metamodel.Package;
import org.omg.sysml.metamodel.Class;

import jackson.DataSerializer;
import jackson.DataDeserializer;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.hibernate.annotations.Any;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.FetchMode;

// import info.archinnov.achilles.annotations.UDT;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.EnumType;
import javax.persistence.ElementCollection;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.FetchType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Table;
import javax.persistence.SecondaryTable;
import javax.persistence.CollectionTable;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

@Entity(name = "SubclassificationImpl")
@SecondaryTable(name = "Subclassification")
@org.hibernate.annotations.Table(appliesTo = "Subclassification", fetch = FetchMode.SELECT, optional = false)
// @info.archinnov.achilles.annotations.Table(table = "Subclassification")
@DiscriminatorValue(value = "Subclassification")
@JsonTypeName(value = "Subclassification")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
public class SubclassificationImpl extends SysMLTypeImpl implements Subclassification {
    // @info.archinnov.achilles.annotations.Column("aliasId")
    private List<String> aliasId;

    @JsonGetter
    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "Subclassification_aliasId",
            joinColumns = @JoinColumn(name = "SubclassificationId"))
    public List<String> getAliasId() {
        if (aliasId == null) {
            aliasId = new ArrayList<>();
        }
        return aliasId;
    }

    @JsonSetter
    public void setAliasId(List<String> aliasId) {
        this.aliasId = aliasId;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("documentation")
    private List<Documentation> documentation;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "DocumentationMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Subclassification_documentation",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<Documentation> getDocumentation() {
        if (documentation == null) {
            documentation = new ArrayList<>();
        }
        return documentation;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = DocumentationImpl.class)
    public void setDocumentation(List<Documentation> documentation) {
        this.documentation = documentation;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("effectiveName")
    private String effectiveName;

    @JsonGetter
    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    // @javax.persistence.Transient
    @javax.persistence.Column(name = "effectiveName", table = "Subclassification")
    public String getEffectiveName() {
        return effectiveName;
    }

    @JsonSetter
    public void setEffectiveName(String effectiveName) {
        this.effectiveName = effectiveName;
    }



    // @info.archinnov.achilles.annotations.Column("general")
    private Type general;

    @JsonGetter
    @JsonSerialize(using = DataSerializer.class)
    @Any(metaDef = "TypeMetaDef", metaColumn = @javax.persistence.Column(name = "generalType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "generalId", table = "Subclassification")
    public Type getGeneral() {
        return general;
    }

    @JsonSetter
    @JsonDeserialize(using = DataDeserializer.class, as = TypeImpl.class)
    public void setGeneral(Type general) {
        this.general = general;
    }



    // @info.archinnov.achilles.annotations.Column("humanId")
    private String humanId;

    @JsonGetter
    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    @javax.persistence.Column(name = "humanId", table = "Subclassification")
    public String getHumanId() {
        return humanId;
    }

    @JsonSetter
    public void setHumanId(String humanId) {
        this.humanId = humanId;
    }



    // @info.archinnov.achilles.annotations.Column("identifier")
    private java.util.UUID identifier;

    @JsonGetter
    @javax.persistence.Column(name = "identifier", table = "Subclassification")
    public java.util.UUID getIdentifier() {
        return identifier;
    }

    @JsonSetter
    public void setIdentifier(java.util.UUID identifier) {
        this.identifier = identifier;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("name")
    private String name;

    @JsonGetter
    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    // @javax.persistence.Transient
    @javax.persistence.Column(name = "name", table = "Subclassification")
    public String getName() {
        return name;
    }

    @JsonSetter
    public void setName(String name) {
        this.name = name;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedAnnotation")
    private List<Annotation> ownedAnnotation;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "AnnotationMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Subclassification_ownedAnnotation",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<Annotation> getOwnedAnnotation() {
        if (ownedAnnotation == null) {
            ownedAnnotation = new ArrayList<>();
        }
        return ownedAnnotation;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = AnnotationImpl.class)
    public void setOwnedAnnotation(List<Annotation> ownedAnnotation) {
        this.ownedAnnotation = ownedAnnotation;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedElement")
    private List<Element> ownedElement;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ElementMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Subclassification_ownedElement",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<Element> getOwnedElement() {
        if (ownedElement == null) {
            ownedElement = new ArrayList<>();
        }
        return ownedElement;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = ElementImpl.class)
    public void setOwnedElement(List<Element> ownedElement) {
        this.ownedElement = ownedElement;
    }



    // @info.archinnov.achilles.annotations.Column("ownedRelatedElement")
    private List<Element> ownedRelatedElement;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    @ManyToAny(metaDef = "ElementMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Subclassification_ownedRelatedElement",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<Element> getOwnedRelatedElement() {
        if (ownedRelatedElement == null) {
            ownedRelatedElement = new ArrayList<>();
        }
        return ownedRelatedElement;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = ElementImpl.class)
    public void setOwnedRelatedElement(List<Element> ownedRelatedElement) {
        this.ownedRelatedElement = ownedRelatedElement;
    }



    // @info.archinnov.achilles.annotations.Column("ownedRelationship")
    private List<Relationship> ownedRelationship;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    @ManyToAny(metaDef = "RelationshipMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Subclassification_ownedRelationship",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<Relationship> getOwnedRelationship() {
        if (ownedRelationship == null) {
            ownedRelationship = new ArrayList<>();
        }
        return ownedRelationship;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = RelationshipImpl.class)
    public void setOwnedRelationship(List<Relationship> ownedRelationship) {
        this.ownedRelationship = ownedRelationship;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("owner")
    private Element owner;

    @JsonGetter
    @JsonSerialize(using = DataSerializer.class)
    // @javax.persistence.Transient
    @Any(metaDef = "ElementMetaDef", metaColumn = @javax.persistence.Column(name = "ownerType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "ownerId", table = "Subclassification")
    public Element getOwner() {
        return owner;
    }

    @JsonSetter
    @JsonDeserialize(using = DataDeserializer.class, as = ElementImpl.class)
    public void setOwner(Element owner) {
        this.owner = owner;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("owningClassifier")
    private Classifier owningClassifier;

    @JsonGetter
    @JsonSerialize(using = DataSerializer.class)
    // @javax.persistence.Transient
    @Any(metaDef = "ClassifierMetaDef", metaColumn = @javax.persistence.Column(name = "owningClassifierType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "owningClassifierId", table = "Subclassification")
    public Classifier getOwningClassifier() {
        return owningClassifier;
    }

    @JsonSetter
    @JsonDeserialize(using = DataDeserializer.class, as = ClassifierImpl.class)
    public void setOwningClassifier(Classifier owningClassifier) {
        this.owningClassifier = owningClassifier;
    }



    // @info.archinnov.achilles.annotations.Column("owningMembership")
    private Membership owningMembership;

    @JsonGetter
    @JsonSerialize(using = DataSerializer.class)
    @Any(metaDef = "MembershipMetaDef", metaColumn = @javax.persistence.Column(name = "owningMembershipType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "owningMembershipId", table = "Subclassification")
    public Membership getOwningMembership() {
        return owningMembership;
    }

    @JsonSetter
    @JsonDeserialize(using = DataDeserializer.class, as = MembershipImpl.class)
    public void setOwningMembership(Membership owningMembership) {
        this.owningMembership = owningMembership;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("owningNamespace")
    private Namespace owningNamespace;

    @JsonGetter
    @JsonSerialize(using = DataSerializer.class)
    // @javax.persistence.Transient
    @Any(metaDef = "NamespaceMetaDef", metaColumn = @javax.persistence.Column(name = "owningNamespaceType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "owningNamespaceId", table = "Subclassification")
    public Namespace getOwningNamespace() {
        return owningNamespace;
    }

    @JsonSetter
    @JsonDeserialize(using = DataDeserializer.class, as = NamespaceImpl.class)
    public void setOwningNamespace(Namespace owningNamespace) {
        this.owningNamespace = owningNamespace;
    }



    // @info.archinnov.achilles.annotations.Column("owningRelatedElement")
    private Element owningRelatedElement;

    @JsonGetter
    @JsonSerialize(using = DataSerializer.class)
    @Any(metaDef = "ElementMetaDef", metaColumn = @javax.persistence.Column(name = "owningRelatedElementType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "owningRelatedElementId", table = "Subclassification")
    public Element getOwningRelatedElement() {
        return owningRelatedElement;
    }

    @JsonSetter
    @JsonDeserialize(using = DataDeserializer.class, as = ElementImpl.class)
    public void setOwningRelatedElement(Element owningRelatedElement) {
        this.owningRelatedElement = owningRelatedElement;
    }



    // @info.archinnov.achilles.annotations.Column("owningRelationship")
    private Relationship owningRelationship;

    @JsonGetter
    @JsonSerialize(using = DataSerializer.class)
    @Any(metaDef = "RelationshipMetaDef", metaColumn = @javax.persistence.Column(name = "owningRelationshipType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "owningRelationshipId", table = "Subclassification")
    public Relationship getOwningRelationship() {
        return owningRelationship;
    }

    @JsonSetter
    @JsonDeserialize(using = DataDeserializer.class, as = RelationshipImpl.class)
    public void setOwningRelationship(Relationship owningRelationship) {
        this.owningRelationship = owningRelationship;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("owningType")
    private Type owningType;

    @JsonGetter
    @JsonSerialize(using = DataSerializer.class)
    // @javax.persistence.Transient
    @Any(metaDef = "TypeMetaDef", metaColumn = @javax.persistence.Column(name = "owningTypeType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "owningTypeId", table = "Subclassification")
    public Type getOwningType() {
        return owningType;
    }

    @JsonSetter
    @JsonDeserialize(using = DataDeserializer.class, as = TypeImpl.class)
    public void setOwningType(Type owningType) {
        this.owningType = owningType;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("qualifiedName")
    private String qualifiedName;

    @JsonGetter
    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    // @javax.persistence.Transient
    @javax.persistence.Column(name = "qualifiedName", table = "Subclassification")
    public String getQualifiedName() {
        return qualifiedName;
    }

    @JsonSetter
    public void setQualifiedName(String qualifiedName) {
        this.qualifiedName = qualifiedName;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("relatedElement")
    private List<Element> relatedElement;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ElementMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Subclassification_relatedElement",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<Element> getRelatedElement() {
        if (relatedElement == null) {
            relatedElement = new ArrayList<>();
        }
        return relatedElement;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = ElementImpl.class)
    public void setRelatedElement(List<Element> relatedElement) {
        this.relatedElement = relatedElement;
    }



    // @info.archinnov.achilles.annotations.Column("source")
    private List<Element> source;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    @ManyToAny(metaDef = "ElementMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Subclassification_source",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<Element> getSource() {
        if (source == null) {
            source = new ArrayList<>();
        }
        return source;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = ElementImpl.class)
    public void setSource(List<Element> source) {
        this.source = source;
    }



    // @info.archinnov.achilles.annotations.Column("specific")
    private Type specific;

    @JsonGetter
    @JsonSerialize(using = DataSerializer.class)
    @Any(metaDef = "TypeMetaDef", metaColumn = @javax.persistence.Column(name = "specificType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "specificId", table = "Subclassification")
    public Type getSpecific() {
        return specific;
    }

    @JsonSetter
    @JsonDeserialize(using = DataDeserializer.class, as = TypeImpl.class)
    public void setSpecific(Type specific) {
        this.specific = specific;
    }



    // @info.archinnov.achilles.annotations.Column("subclassifier")
    private Classifier subclassifier;

    @JsonGetter
    @JsonSerialize(using = DataSerializer.class)
    @Any(metaDef = "ClassifierMetaDef", metaColumn = @javax.persistence.Column(name = "subclassifierType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "subclassifierId", table = "Subclassification")
    public Classifier getSubclassifier() {
        return subclassifier;
    }

    @JsonSetter
    @JsonDeserialize(using = DataDeserializer.class, as = ClassifierImpl.class)
    public void setSubclassifier(Classifier subclassifier) {
        this.subclassifier = subclassifier;
    }



    // @info.archinnov.achilles.annotations.Column("superclassifier")
    private Classifier superclassifier;

    @JsonGetter
    @JsonSerialize(using = DataSerializer.class)
    @Any(metaDef = "ClassifierMetaDef", metaColumn = @javax.persistence.Column(name = "superclassifierType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "superclassifierId", table = "Subclassification")
    public Classifier getSuperclassifier() {
        return superclassifier;
    }

    @JsonSetter
    @JsonDeserialize(using = DataDeserializer.class, as = ClassifierImpl.class)
    public void setSuperclassifier(Classifier superclassifier) {
        this.superclassifier = superclassifier;
    }



    // @info.archinnov.achilles.annotations.Column("target")
    private List<Element> target;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    @ManyToAny(metaDef = "ElementMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Subclassification_target",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<Element> getTarget() {
        if (target == null) {
            target = new ArrayList<>();
        }
        return target;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = ElementImpl.class)
    public void setTarget(List<Element> target) {
        this.target = target;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("textualRepresentation")
    private List<TextualRepresentation> textualRepresentation;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "TextualRepresentationMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Subclassification_textualRepresentation",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<TextualRepresentation> getTextualRepresentation() {
        if (textualRepresentation == null) {
            textualRepresentation = new ArrayList<>();
        }
        return textualRepresentation;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = TextualRepresentationImpl.class)
    public void setTextualRepresentation(List<TextualRepresentation> textualRepresentation) {
        this.textualRepresentation = textualRepresentation;
    }



}
