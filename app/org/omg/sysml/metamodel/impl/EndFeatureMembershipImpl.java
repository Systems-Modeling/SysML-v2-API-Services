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

@Entity(name = "EndFeatureMembershipImpl")
@SecondaryTable(name = "EndFeatureMembership")
@org.hibernate.annotations.Table(appliesTo = "EndFeatureMembership", fetch = FetchMode.SELECT, optional = false)
// @info.archinnov.achilles.annotations.Table(table = "EndFeatureMembership")
@DiscriminatorValue(value = "EndFeatureMembership")
@JsonTypeName(value = "EndFeatureMembership")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
public class EndFeatureMembershipImpl extends MofObjectImpl implements EndFeatureMembership {
    // @info.archinnov.achilles.annotations.Column("owningType")
    private Type owningType;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "TypeMetaDef", metaColumn = @javax.persistence.Column(name = "owningTypeType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "owningTypeId", table = "EndFeatureMembership")
    public Type getOwningType() {
        return owningType;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = TypeImpl.class)
    public void setOwningType(Type owningType) {
        this.owningType = owningType;
    }



    // @info.archinnov.achilles.annotations.Column("isDerived")
    private Boolean isDerived;

    @JsonGetter
    @javax.persistence.Column(name = "isDerived", table = "EndFeatureMembership")
    public Boolean getIsDerived() {
        return isDerived;
    }

    @JsonSetter
    public void setIsDerived(Boolean isDerived) {
        this.isDerived = isDerived;
    }



    // @info.archinnov.achilles.annotations.Column("isReadOnly")
    private Boolean isReadOnly;

    @JsonGetter
    @javax.persistence.Column(name = "isReadOnly", table = "EndFeatureMembership")
    public Boolean getIsReadOnly() {
        return isReadOnly;
    }

    @JsonSetter
    public void setIsReadOnly(Boolean isReadOnly) {
        this.isReadOnly = isReadOnly;
    }



    // @info.archinnov.achilles.annotations.Column("memberFeature")
    private Feature memberFeature;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "FeatureMetaDef", metaColumn = @javax.persistence.Column(name = "memberFeatureType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "memberFeatureId", table = "EndFeatureMembership")
    public Feature getMemberFeature() {
        return memberFeature;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = FeatureImpl.class)
    public void setMemberFeature(Feature memberFeature) {
        this.memberFeature = memberFeature;
    }



    // @info.archinnov.achilles.annotations.Column("ownedMemberFeature")
    private Feature ownedMemberFeature;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "FeatureMetaDef", metaColumn = @javax.persistence.Column(name = "ownedMemberFeatureType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "ownedMemberFeatureId", table = "EndFeatureMembership")
    public Feature getOwnedMemberFeature() {
        return ownedMemberFeature;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = FeatureImpl.class)
    public void setOwnedMemberFeature(Feature ownedMemberFeature) {
        this.ownedMemberFeature = ownedMemberFeature;
    }



    // @info.archinnov.achilles.annotations.Column("isComposite")
    private Boolean isComposite;

    @JsonGetter
    @javax.persistence.Column(name = "isComposite", table = "EndFeatureMembership")
    public Boolean getIsComposite() {
        return isComposite;
    }

    @JsonSetter
    public void setIsComposite(Boolean isComposite) {
        this.isComposite = isComposite;
    }



    // @info.archinnov.achilles.annotations.Column("isPortion")
    private Boolean isPortion;

    @JsonGetter
    @javax.persistence.Column(name = "isPortion", table = "EndFeatureMembership")
    public Boolean getIsPortion() {
        return isPortion;
    }

    @JsonSetter
    public void setIsPortion(Boolean isPortion) {
        this.isPortion = isPortion;
    }



    // @info.archinnov.achilles.annotations.Column("isPort")
    private Boolean isPort;

    @JsonGetter
    @javax.persistence.Column(name = "isPort", table = "EndFeatureMembership")
    public Boolean getIsPort() {
        return isPort;
    }

    @JsonSetter
    public void setIsPort(Boolean isPort) {
        this.isPort = isPort;
    }



    // @info.archinnov.achilles.annotations.Column("direction")
    // @info.archinnov.achilles.annotations.Enumerated(info.archinnov.achilles.annotations.Enumerated.Encoding.NAME)
    private FeatureDirectionKind direction;

    @JsonGetter
    @javax.persistence.Enumerated(EnumType.STRING)
    @javax.persistence.Column(name = "direction", table = "EndFeatureMembership")
    public FeatureDirectionKind getDirection() {
        return direction;
    }

    @JsonSetter
    public void setDirection(FeatureDirectionKind direction) {
        this.direction = direction;
    }



    // @info.archinnov.achilles.annotations.Column("memberName")
    private String memberName;

    @JsonGetter
    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    @javax.persistence.Column(name = "memberName", table = "EndFeatureMembership")
    public String getMemberName() {
        return memberName;
    }

    @JsonSetter
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }



    // @info.archinnov.achilles.annotations.Column("visibility")
    // @info.archinnov.achilles.annotations.Enumerated(info.archinnov.achilles.annotations.Enumerated.Encoding.NAME)
    private VisibilityKind visibility;

    @JsonGetter
    @javax.persistence.Enumerated(EnumType.STRING)
    @javax.persistence.Column(name = "visibility", table = "EndFeatureMembership")
    public VisibilityKind getVisibility() {
        return visibility;
    }

    @JsonSetter
    public void setVisibility(VisibilityKind visibility) {
        this.visibility = visibility;
    }



    // @info.archinnov.achilles.annotations.Column("aliases")
    private Collection<String> aliases;

    @JsonGetter
    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "EndFeatureMembership_aliases",
            joinColumns = @JoinColumn(name = "EndFeatureMembershipId"))
    public Collection<String> getAliases() {
        if (aliases == null) {
            aliases = new ArrayList<>();
        }
        return aliases;
    }

    @JsonSetter
    public void setAliases(Collection<String> aliases) {
        this.aliases = aliases;
    }



    // @info.archinnov.achilles.annotations.Column("memberElement")
    private Element memberElement;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "ElementMetaDef", metaColumn = @javax.persistence.Column(name = "memberElementType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "memberElementId", table = "EndFeatureMembership")
    public Element getMemberElement() {
        return memberElement;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = ElementImpl.class)
    public void setMemberElement(Element memberElement) {
        this.memberElement = memberElement;
    }



    // @info.archinnov.achilles.annotations.Column("ownedMemberElement")
    private Element ownedMemberElement;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "ElementMetaDef", metaColumn = @javax.persistence.Column(name = "ownedMemberElementType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "ownedMemberElementId", table = "EndFeatureMembership")
    public Element getOwnedMemberElement() {
        return ownedMemberElement;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = ElementImpl.class)
    public void setOwnedMemberElement(Element ownedMemberElement) {
        this.ownedMemberElement = ownedMemberElement;
    }



    // @info.archinnov.achilles.annotations.Column("membershipOwningPackage")
    private Package membershipOwningPackage;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "PackageMetaDef", metaColumn = @javax.persistence.Column(name = "membershipOwningPackageType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "membershipOwningPackageId", table = "EndFeatureMembership")
    public Package getMembershipOwningPackage() {
        return membershipOwningPackage;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = PackageImpl.class)
    public void setMembershipOwningPackage(Package membershipOwningPackage) {
        this.membershipOwningPackage = membershipOwningPackage;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("relatedElement")
    private Collection<Element> relatedElement;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ElementMetaDef", metaColumn = @javax.persistence.Column(name = "relatedElementType"), fetch = FetchType.LAZY)
    @JoinTable(name = "EndFeatureMembership_relatedElement",
            joinColumns = @JoinColumn(name = "EndFeatureMembershipId"),
            inverseJoinColumns = @JoinColumn(name = "relatedElementId"))
    public Collection<Element> getRelatedElement() {
        if (relatedElement == null) {
            relatedElement = new ArrayList<>();
        }
        return relatedElement;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ElementImpl.class)
    public void setRelatedElement(Collection<Element> relatedElement) {
        this.relatedElement = relatedElement;
    }



    // @info.archinnov.achilles.annotations.Column("target")
    private Collection<Element> target;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @ManyToAny(metaDef = "ElementMetaDef", metaColumn = @javax.persistence.Column(name = "targetType"), fetch = FetchType.LAZY)
    @JoinTable(name = "EndFeatureMembership_target",
            joinColumns = @JoinColumn(name = "EndFeatureMembershipId"),
            inverseJoinColumns = @JoinColumn(name = "targetId"))
    public Collection<Element> getTarget() {
        if (target == null) {
            target = new ArrayList<>();
        }
        return target;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ElementImpl.class)
    public void setTarget(Collection<Element> target) {
        this.target = target;
    }



    // @info.archinnov.achilles.annotations.Column("source")
    private Collection<Element> source;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @ManyToAny(metaDef = "ElementMetaDef", metaColumn = @javax.persistence.Column(name = "sourceType"), fetch = FetchType.LAZY)
    @JoinTable(name = "EndFeatureMembership_source",
            joinColumns = @JoinColumn(name = "EndFeatureMembershipId"),
            inverseJoinColumns = @JoinColumn(name = "sourceId"))
    public Collection<Element> getSource() {
        if (source == null) {
            source = new ArrayList<>();
        }
        return source;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ElementImpl.class)
    public void setSource(Collection<Element> source) {
        this.source = source;
    }



    // @info.archinnov.achilles.annotations.Column("owningRelatedElement")
    private Element owningRelatedElement;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "ElementMetaDef", metaColumn = @javax.persistence.Column(name = "owningRelatedElementType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "owningRelatedElementId", table = "EndFeatureMembership")
    public Element getOwningRelatedElement() {
        return owningRelatedElement;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = ElementImpl.class)
    public void setOwningRelatedElement(Element owningRelatedElement) {
        this.owningRelatedElement = owningRelatedElement;
    }



    // @info.archinnov.achilles.annotations.Column("ownedRelatedElement")
    private Collection<Element> ownedRelatedElement;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @ManyToAny(metaDef = "ElementMetaDef", metaColumn = @javax.persistence.Column(name = "ownedRelatedElementType"), fetch = FetchType.LAZY)
    @JoinTable(name = "EndFeatureMembership_ownedRelatedElement",
            joinColumns = @JoinColumn(name = "EndFeatureMembershipId"),
            inverseJoinColumns = @JoinColumn(name = "ownedRelatedElementId"))
    public Collection<Element> getOwnedRelatedElement() {
        if (ownedRelatedElement == null) {
            ownedRelatedElement = new ArrayList<>();
        }
        return ownedRelatedElement;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ElementImpl.class)
    public void setOwnedRelatedElement(Collection<Element> ownedRelatedElement) {
        this.ownedRelatedElement = ownedRelatedElement;
    }



    // @info.archinnov.achilles.annotations.Column("owningMembership")
    private Membership owningMembership;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "MembershipMetaDef", metaColumn = @javax.persistence.Column(name = "owningMembershipType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "owningMembershipId", table = "EndFeatureMembership")
    public Membership getOwningMembership() {
        return owningMembership;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = MembershipImpl.class)
    public void setOwningMembership(Membership owningMembership) {
        this.owningMembership = owningMembership;
    }



    // @info.archinnov.achilles.annotations.Column("owningRelationship")
    private Relationship owningRelationship;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "RelationshipMetaDef", metaColumn = @javax.persistence.Column(name = "owningRelationshipType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "owningRelationshipId", table = "EndFeatureMembership")
    public Relationship getOwningRelationship() {
        return owningRelationship;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = RelationshipImpl.class)
    public void setOwningRelationship(Relationship owningRelationship) {
        this.owningRelationship = owningRelationship;
    }



    // @info.archinnov.achilles.annotations.Column("identifier")
    private java.util.UUID identifier;

    @JsonGetter
    @javax.persistence.Column(name = "identifier", table = "EndFeatureMembership")
    public java.util.UUID getIdentifier() {
        return identifier;
    }

    @JsonSetter
    public void setIdentifier(java.util.UUID identifier) {
        this.identifier = identifier;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("owningNamespace")
    private Package owningNamespace;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @Any(metaDef = "PackageMetaDef", metaColumn = @javax.persistence.Column(name = "owningNamespaceType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "owningNamespaceId", table = "EndFeatureMembership")
    public Package getOwningNamespace() {
        return owningNamespace;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = PackageImpl.class)
    public void setOwningNamespace(Package owningNamespace) {
        this.owningNamespace = owningNamespace;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("name")
    private String name;

    @JsonGetter
    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    // @javax.persistence.Transient
    @javax.persistence.Column(name = "name", table = "EndFeatureMembership")
    public String getName() {
        return name;
    }

    @JsonSetter
    public void setName(String name) {
        this.name = name;
    }



    // @info.archinnov.achilles.annotations.Column("ownedRelationship")
    private Collection<Relationship> ownedRelationship;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @ManyToAny(metaDef = "RelationshipMetaDef", metaColumn = @javax.persistence.Column(name = "ownedRelationshipType"), fetch = FetchType.LAZY)
    @JoinTable(name = "EndFeatureMembership_ownedRelationship",
            joinColumns = @JoinColumn(name = "EndFeatureMembershipId"),
            inverseJoinColumns = @JoinColumn(name = "ownedRelationshipId"))
    public Collection<Relationship> getOwnedRelationship() {
        if (ownedRelationship == null) {
            ownedRelationship = new ArrayList<>();
        }
        return ownedRelationship;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = RelationshipImpl.class)
    public void setOwnedRelationship(Collection<Relationship> ownedRelationship) {
        this.ownedRelationship = ownedRelationship;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("owner")
    private Element owner;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @Any(metaDef = "ElementMetaDef", metaColumn = @javax.persistence.Column(name = "ownerType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "ownerId", table = "EndFeatureMembership")
    public Element getOwner() {
        return owner;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = ElementImpl.class)
    public void setOwner(Element owner) {
        this.owner = owner;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedElement")
    private Collection<Element> ownedElement;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ElementMetaDef", metaColumn = @javax.persistence.Column(name = "ownedElementType"), fetch = FetchType.LAZY)
    @JoinTable(name = "EndFeatureMembership_ownedElement",
            joinColumns = @JoinColumn(name = "EndFeatureMembershipId"),
            inverseJoinColumns = @JoinColumn(name = "ownedElementId"))
    public Collection<Element> getOwnedElement() {
        if (ownedElement == null) {
            ownedElement = new ArrayList<>();
        }
        return ownedElement;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ElementImpl.class)
    public void setOwnedElement(Collection<Element> ownedElement) {
        this.ownedElement = ownedElement;
    }



}
