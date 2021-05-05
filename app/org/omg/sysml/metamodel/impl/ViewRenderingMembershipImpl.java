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

import jackson.MofObjectSerializer;
import jackson.MofObjectDeserializer;

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

@Entity(name = "ViewRenderingMembershipImpl")
@SecondaryTable(name = "ViewRenderingMembership")
@org.hibernate.annotations.Table(appliesTo = "ViewRenderingMembership", fetch = FetchMode.SELECT, optional = false)
// @info.archinnov.achilles.annotations.Table(table = "ViewRenderingMembership")
@DiscriminatorValue(value = "ViewRenderingMembership")
@JsonTypeName(value = "ViewRenderingMembership")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
public class ViewRenderingMembershipImpl extends MofObjectImpl implements ViewRenderingMembership {
    // @info.archinnov.achilles.annotations.Column("aliasId")
    private List<String> aliasId;

    @JsonGetter
    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "ViewRenderingMembership_aliasId",
            joinColumns = @JoinColumn(name = "ViewRenderingMembershipId"))
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



    // @info.archinnov.achilles.annotations.Column("direction")
    // @info.archinnov.achilles.annotations.Enumerated(info.archinnov.achilles.annotations.Enumerated.Encoding.NAME)
    private FeatureDirectionKind direction;

    @JsonGetter
    @javax.persistence.Enumerated(EnumType.STRING)
    @javax.persistence.Column(name = "direction", table = "ViewRenderingMembership")
    public FeatureDirectionKind getDirection() {
        return direction;
    }

    @JsonSetter
    public void setDirection(FeatureDirectionKind direction) {
        this.direction = direction;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("documentation")
    private List<Documentation> documentation;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "DocumentationMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "ViewRenderingMembership_documentation",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<Documentation> getDocumentation() {
        if (documentation == null) {
            documentation = new ArrayList<>();
        }
        return documentation;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = DocumentationImpl.class)
    public void setDocumentation(List<Documentation> documentation) {
        this.documentation = documentation;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("documentationComment")
    private List<Comment> documentationComment;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "CommentMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "ViewRenderingMembership_documentationComment",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<Comment> getDocumentationComment() {
        if (documentationComment == null) {
            documentationComment = new ArrayList<>();
        }
        return documentationComment;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = CommentImpl.class)
    public void setDocumentationComment(List<Comment> documentationComment) {
        this.documentationComment = documentationComment;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("effectiveMemberName")
    private String effectiveMemberName;

    @JsonGetter
    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    // @javax.persistence.Transient
    @javax.persistence.Column(name = "effectiveMemberName", table = "ViewRenderingMembership")
    public String getEffectiveMemberName() {
        return effectiveMemberName;
    }

    @JsonSetter
    public void setEffectiveMemberName(String effectiveMemberName) {
        this.effectiveMemberName = effectiveMemberName;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("effectiveName")
    private String effectiveName;

    @JsonGetter
    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    // @javax.persistence.Transient
    @javax.persistence.Column(name = "effectiveName", table = "ViewRenderingMembership")
    public String getEffectiveName() {
        return effectiveName;
    }

    @JsonSetter
    public void setEffectiveName(String effectiveName) {
        this.effectiveName = effectiveName;
    }



    // @info.archinnov.achilles.annotations.Column("featureOfType")
    private Feature featureOfType;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "FeatureMetaDef", metaColumn = @javax.persistence.Column(name = "featureOfTypeType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "featureOfTypeId", table = "ViewRenderingMembership")
    public Feature getFeatureOfType() {
        return featureOfType;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = FeatureImpl.class)
    public void setFeatureOfType(Feature featureOfType) {
        this.featureOfType = featureOfType;
    }



    // @info.archinnov.achilles.annotations.Column("featuringType")
    private Type featuringType;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "TypeMetaDef", metaColumn = @javax.persistence.Column(name = "featuringTypeType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "featuringTypeId", table = "ViewRenderingMembership")
    public Type getFeaturingType() {
        return featuringType;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = TypeImpl.class)
    public void setFeaturingType(Type featuringType) {
        this.featuringType = featuringType;
    }



    // @info.archinnov.achilles.annotations.Column("humanId")
    private String humanId;

    @JsonGetter
    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    @javax.persistence.Column(name = "humanId", table = "ViewRenderingMembership")
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
    @javax.persistence.Column(name = "identifier", table = "ViewRenderingMembership")
    public java.util.UUID getIdentifier() {
        return identifier;
    }

    @JsonSetter
    public void setIdentifier(java.util.UUID identifier) {
        this.identifier = identifier;
    }



    // @info.archinnov.achilles.annotations.Column("isComposite")
    private Boolean isComposite;

    @JsonGetter
    @javax.persistence.Column(name = "isComposite", table = "ViewRenderingMembership")
    public Boolean getIsComposite() {
        return isComposite;
    }

    @JsonSetter
    public void setIsComposite(Boolean isComposite) {
        this.isComposite = isComposite;
    }



    // @info.archinnov.achilles.annotations.Column("isDerived")
    private Boolean isDerived;

    @JsonGetter
    @javax.persistence.Column(name = "isDerived", table = "ViewRenderingMembership")
    public Boolean getIsDerived() {
        return isDerived;
    }

    @JsonSetter
    public void setIsDerived(Boolean isDerived) {
        this.isDerived = isDerived;
    }



    // @info.archinnov.achilles.annotations.Column("isPort")
    private Boolean isPort;

    @JsonGetter
    @javax.persistence.Column(name = "isPort", table = "ViewRenderingMembership")
    public Boolean getIsPort() {
        return isPort;
    }

    @JsonSetter
    public void setIsPort(Boolean isPort) {
        this.isPort = isPort;
    }



    // @info.archinnov.achilles.annotations.Column("isPortion")
    private Boolean isPortion;

    @JsonGetter
    @javax.persistence.Column(name = "isPortion", table = "ViewRenderingMembership")
    public Boolean getIsPortion() {
        return isPortion;
    }

    @JsonSetter
    public void setIsPortion(Boolean isPortion) {
        this.isPortion = isPortion;
    }



    // @info.archinnov.achilles.annotations.Column("isReadOnly")
    private Boolean isReadOnly;

    @JsonGetter
    @javax.persistence.Column(name = "isReadOnly", table = "ViewRenderingMembership")
    public Boolean getIsReadOnly() {
        return isReadOnly;
    }

    @JsonSetter
    public void setIsReadOnly(Boolean isReadOnly) {
        this.isReadOnly = isReadOnly;
    }



    // @info.archinnov.achilles.annotations.Column("memberElement")
    private Element memberElement;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "ElementMetaDef", metaColumn = @javax.persistence.Column(name = "memberElementType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "memberElementId", table = "ViewRenderingMembership")
    public Element getMemberElement() {
        return memberElement;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = ElementImpl.class)
    public void setMemberElement(Element memberElement) {
        this.memberElement = memberElement;
    }



    // @info.archinnov.achilles.annotations.Column("memberFeature")
    private Feature memberFeature;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "FeatureMetaDef", metaColumn = @javax.persistence.Column(name = "memberFeatureType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "memberFeatureId", table = "ViewRenderingMembership")
    public Feature getMemberFeature() {
        return memberFeature;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = FeatureImpl.class)
    public void setMemberFeature(Feature memberFeature) {
        this.memberFeature = memberFeature;
    }



    // @info.archinnov.achilles.annotations.Column("memberName")
    private String memberName;

    @JsonGetter
    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    @javax.persistence.Column(name = "memberName", table = "ViewRenderingMembership")
    public String getMemberName() {
        return memberName;
    }

    @JsonSetter
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("membershipOwningNamespace")
    private Namespace membershipOwningNamespace;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @Any(metaDef = "NamespaceMetaDef", metaColumn = @javax.persistence.Column(name = "membershipOwningNamespaceType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "membershipOwningNamespaceId", table = "ViewRenderingMembership")
    public Namespace getMembershipOwningNamespace() {
        return membershipOwningNamespace;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = NamespaceImpl.class)
    public void setMembershipOwningNamespace(Namespace membershipOwningNamespace) {
        this.membershipOwningNamespace = membershipOwningNamespace;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("name")
    private String name;

    @JsonGetter
    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    // @javax.persistence.Transient
    @javax.persistence.Column(name = "name", table = "ViewRenderingMembership")
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
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "AnnotationMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "ViewRenderingMembership_ownedAnnotation",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<Annotation> getOwnedAnnotation() {
        if (ownedAnnotation == null) {
            ownedAnnotation = new ArrayList<>();
        }
        return ownedAnnotation;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = AnnotationImpl.class)
    public void setOwnedAnnotation(List<Annotation> ownedAnnotation) {
        this.ownedAnnotation = ownedAnnotation;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedElement")
    private List<Element> ownedElement;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ElementMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "ViewRenderingMembership_ownedElement",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<Element> getOwnedElement() {
        if (ownedElement == null) {
            ownedElement = new ArrayList<>();
        }
        return ownedElement;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ElementImpl.class)
    public void setOwnedElement(List<Element> ownedElement) {
        this.ownedElement = ownedElement;
    }



    // @info.archinnov.achilles.annotations.Column("ownedMemberElement")
    private Element ownedMemberElement;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "ElementMetaDef", metaColumn = @javax.persistence.Column(name = "ownedMemberElementType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "ownedMemberElementId", table = "ViewRenderingMembership")
    public Element getOwnedMemberElement() {
        return ownedMemberElement;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = ElementImpl.class)
    public void setOwnedMemberElement(Element ownedMemberElement) {
        this.ownedMemberElement = ownedMemberElement;
    }



    // @info.archinnov.achilles.annotations.Column("ownedMemberFeature")
    private Feature ownedMemberFeature;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "FeatureMetaDef", metaColumn = @javax.persistence.Column(name = "ownedMemberFeatureType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "ownedMemberFeatureId", table = "ViewRenderingMembership")
    public Feature getOwnedMemberFeature() {
        return ownedMemberFeature;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = FeatureImpl.class)
    public void setOwnedMemberFeature(Feature ownedMemberFeature) {
        this.ownedMemberFeature = ownedMemberFeature;
    }



    // @info.archinnov.achilles.annotations.Column("ownedRelatedElement")
    private List<Element> ownedRelatedElement;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @ManyToAny(metaDef = "ElementMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "ViewRenderingMembership_ownedRelatedElement",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<Element> getOwnedRelatedElement() {
        if (ownedRelatedElement == null) {
            ownedRelatedElement = new ArrayList<>();
        }
        return ownedRelatedElement;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ElementImpl.class)
    public void setOwnedRelatedElement(List<Element> ownedRelatedElement) {
        this.ownedRelatedElement = ownedRelatedElement;
    }



    // @info.archinnov.achilles.annotations.Column("ownedRelationship")
    private List<Relationship> ownedRelationship;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @ManyToAny(metaDef = "RelationshipMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "ViewRenderingMembership_ownedRelationship",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<Relationship> getOwnedRelationship() {
        if (ownedRelationship == null) {
            ownedRelationship = new ArrayList<>();
        }
        return ownedRelationship;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = RelationshipImpl.class)
    public void setOwnedRelationship(List<Relationship> ownedRelationship) {
        this.ownedRelationship = ownedRelationship;
    }



    // @info.archinnov.achilles.annotations.Column("ownedRendering")
    private RenderingUsage ownedRendering;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "RenderingUsageMetaDef", metaColumn = @javax.persistence.Column(name = "ownedRenderingType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "ownedRenderingId", table = "ViewRenderingMembership")
    public RenderingUsage getOwnedRendering() {
        return ownedRendering;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = RenderingUsageImpl.class)
    public void setOwnedRendering(RenderingUsage ownedRendering) {
        this.ownedRendering = ownedRendering;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedTextualRepresentation")
    private Collection<TextualRepresentation> ownedTextualRepresentation;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "TextualRepresentationMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "ViewRenderingMembership_ownedTextualRepresentation",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public Collection<TextualRepresentation> getOwnedTextualRepresentation() {
        if (ownedTextualRepresentation == null) {
            ownedTextualRepresentation = new ArrayList<>();
        }
        return ownedTextualRepresentation;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = TextualRepresentationImpl.class)
    public void setOwnedTextualRepresentation(Collection<TextualRepresentation> ownedTextualRepresentation) {
        this.ownedTextualRepresentation = ownedTextualRepresentation;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("owner")
    private Element owner;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @Any(metaDef = "ElementMetaDef", metaColumn = @javax.persistence.Column(name = "ownerType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "ownerId", table = "ViewRenderingMembership")
    public Element getOwner() {
        return owner;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = ElementImpl.class)
    public void setOwner(Element owner) {
        this.owner = owner;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("owningFeatureOfType")
    private Feature owningFeatureOfType;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @Any(metaDef = "FeatureMetaDef", metaColumn = @javax.persistence.Column(name = "owningFeatureOfTypeType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "owningFeatureOfTypeId", table = "ViewRenderingMembership")
    public Feature getOwningFeatureOfType() {
        return owningFeatureOfType;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = FeatureImpl.class)
    public void setOwningFeatureOfType(Feature owningFeatureOfType) {
        this.owningFeatureOfType = owningFeatureOfType;
    }



    // @info.archinnov.achilles.annotations.Column("owningMembership")
    private Membership owningMembership;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "MembershipMetaDef", metaColumn = @javax.persistence.Column(name = "owningMembershipType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "owningMembershipId", table = "ViewRenderingMembership")
    public Membership getOwningMembership() {
        return owningMembership;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = MembershipImpl.class)
    public void setOwningMembership(Membership owningMembership) {
        this.owningMembership = owningMembership;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("owningNamespace")
    private Namespace owningNamespace;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @Any(metaDef = "NamespaceMetaDef", metaColumn = @javax.persistence.Column(name = "owningNamespaceType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "owningNamespaceId", table = "ViewRenderingMembership")
    public Namespace getOwningNamespace() {
        return owningNamespace;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = NamespaceImpl.class)
    public void setOwningNamespace(Namespace owningNamespace) {
        this.owningNamespace = owningNamespace;
    }



    // @info.archinnov.achilles.annotations.Column("owningRelatedElement")
    private Element owningRelatedElement;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "ElementMetaDef", metaColumn = @javax.persistence.Column(name = "owningRelatedElementType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "owningRelatedElementId", table = "ViewRenderingMembership")
    public Element getOwningRelatedElement() {
        return owningRelatedElement;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = ElementImpl.class)
    public void setOwningRelatedElement(Element owningRelatedElement) {
        this.owningRelatedElement = owningRelatedElement;
    }



    // @info.archinnov.achilles.annotations.Column("owningRelationship")
    private Relationship owningRelationship;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "RelationshipMetaDef", metaColumn = @javax.persistence.Column(name = "owningRelationshipType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "owningRelationshipId", table = "ViewRenderingMembership")
    public Relationship getOwningRelationship() {
        return owningRelationship;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = RelationshipImpl.class)
    public void setOwningRelationship(Relationship owningRelationship) {
        this.owningRelationship = owningRelationship;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("owningType")
    private Type owningType;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @Any(metaDef = "TypeMetaDef", metaColumn = @javax.persistence.Column(name = "owningTypeType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "owningTypeId", table = "ViewRenderingMembership")
    public Type getOwningType() {
        return owningType;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = TypeImpl.class)
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
    @javax.persistence.Column(name = "qualifiedName", table = "ViewRenderingMembership")
    public String getQualifiedName() {
        return qualifiedName;
    }

    @JsonSetter
    public void setQualifiedName(String qualifiedName) {
        this.qualifiedName = qualifiedName;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("referencedRendering")
    private RenderingUsage referencedRendering;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @Any(metaDef = "RenderingUsageMetaDef", metaColumn = @javax.persistence.Column(name = "referencedRenderingType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "referencedRenderingId", table = "ViewRenderingMembership")
    public RenderingUsage getReferencedRendering() {
        return referencedRendering;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = RenderingUsageImpl.class)
    public void setReferencedRendering(RenderingUsage referencedRendering) {
        this.referencedRendering = referencedRendering;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("relatedElement")
    private List<Element> relatedElement;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ElementMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "ViewRenderingMembership_relatedElement",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<Element> getRelatedElement() {
        if (relatedElement == null) {
            relatedElement = new ArrayList<>();
        }
        return relatedElement;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ElementImpl.class)
    public void setRelatedElement(List<Element> relatedElement) {
        this.relatedElement = relatedElement;
    }



    // @info.archinnov.achilles.annotations.Column("source")
    private List<Element> source;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @ManyToAny(metaDef = "ElementMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "ViewRenderingMembership_source",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<Element> getSource() {
        if (source == null) {
            source = new ArrayList<>();
        }
        return source;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ElementImpl.class)
    public void setSource(List<Element> source) {
        this.source = source;
    }



    // @info.archinnov.achilles.annotations.Column("target")
    private List<Element> target;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @ManyToAny(metaDef = "ElementMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "ViewRenderingMembership_target",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<Element> getTarget() {
        if (target == null) {
            target = new ArrayList<>();
        }
        return target;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ElementImpl.class)
    public void setTarget(List<Element> target) {
        this.target = target;
    }



    // @info.archinnov.achilles.annotations.Column("visibility")
    // @info.archinnov.achilles.annotations.Enumerated(info.archinnov.achilles.annotations.Enumerated.Encoding.NAME)
    private VisibilityKind visibility;

    @JsonGetter
    @javax.persistence.Enumerated(EnumType.STRING)
    @javax.persistence.Column(name = "visibility", table = "ViewRenderingMembership")
    public VisibilityKind getVisibility() {
        return visibility;
    }

    @JsonSetter
    public void setVisibility(VisibilityKind visibility) {
        this.visibility = visibility;
    }



}
