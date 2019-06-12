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
@Entity(name = "MembershipImpl")
@SecondaryTable(name = "Membership")
@org.hibernate.annotations.Table(appliesTo = "Membership", fetch = FetchMode.SELECT, optional = false)
// @info.archinnov.achilles.annotations.Table(table = "Membership")
@DiscriminatorValue(value = "Membership")
@JsonTypeName(value = "Membership")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
public class MembershipImpl extends MofObjectImpl implements Membership {
    // @info.archinnov.achilles.annotations.Column("owningRelatedElement")
    private Element owningRelatedElement;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "ElementMetaDef", metaColumn = @javax.persistence.Column(name = "owningRelatedElementType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "owningRelatedElementId", table = "Membership")
    public Element getOwningRelatedElement() {
        return owningRelatedElement;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = ElementImpl.class)
    public void setOwningRelatedElement(Element owningRelatedElement) {
        this.owningRelatedElement = owningRelatedElement;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("relatedElement")
    private Collection<Element> relatedElement;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @javax.persistence.Transient
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



    // @info.archinnov.achilles.annotations.Column("identifier")
    private java.util.UUID identifier;

    @JsonGetter
    public java.util.UUID getIdentifier() {
        return identifier;
    }

    @JsonSetter
    public void setIdentifier(java.util.UUID identifier) {
        this.identifier = identifier;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("membershipOwningPackage")
    private Package membershipOwningPackage;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @javax.persistence.Transient
    public Package getMembershipOwningPackage() {
        return membershipOwningPackage;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = PackageImpl.class)
    public void setMembershipOwningPackage(Package membershipOwningPackage) {
        this.membershipOwningPackage = membershipOwningPackage;
    }



    // @info.archinnov.achilles.annotations.Column("memberElement")
    private Element memberElement;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "ElementMetaDef", metaColumn = @javax.persistence.Column(name = "memberElementType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "memberElementId", table = "Membership")
    public Element getMemberElement() {
        return memberElement;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = ElementImpl.class)
    public void setMemberElement(Element memberElement) {
        this.memberElement = memberElement;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("owningMembership")
    private Membership owningMembership;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @javax.persistence.Transient
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
    private Package owningNamespace;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @javax.persistence.Transient
    public Package getOwningNamespace() {
        return owningNamespace;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = PackageImpl.class)
    public void setOwningNamespace(Package owningNamespace) {
        this.owningNamespace = owningNamespace;
    }



    // @info.archinnov.achilles.annotations.Column("aliases")
    private Collection<String> aliases;

    @JsonGetter
    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "Membership_aliases",
            joinColumns = @JoinColumn(name = "MembershipId"))
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



    // @info.archinnov.achilles.annotations.Column("ownedRelatedElement")
    private Collection<Element> ownedRelatedElement;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @ManyToAny(metaDef = "ElementMetaDef", metaColumn = @javax.persistence.Column(name = "ownedRelatedElementType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Membership_ownedRelatedElement",
            joinColumns = @JoinColumn(name = "MembershipId"),
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



    // @info.archinnov.achilles.annotations.Column("target")
    private Collection<Element> target;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @ManyToAny(metaDef = "ElementMetaDef", metaColumn = @javax.persistence.Column(name = "targetType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Membership_target",
            joinColumns = @JoinColumn(name = "MembershipId"),
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



    // @info.archinnov.achilles.annotations.Column("owningRelationship")
    private Relationship owningRelationship;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "RelationshipMetaDef", metaColumn = @javax.persistence.Column(name = "owningRelationshipType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "owningRelationshipId", table = "Membership")
    public Relationship getOwningRelationship() {
        return owningRelationship;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = RelationshipImpl.class)
    public void setOwningRelationship(Relationship owningRelationship) {
        this.owningRelationship = owningRelationship;
    }



    // @info.archinnov.achilles.annotations.Column("ownedRelationship")
    private Collection<Relationship> ownedRelationship;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @ManyToAny(metaDef = "RelationshipMetaDef", metaColumn = @javax.persistence.Column(name = "ownedRelationshipType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Membership_ownedRelationship",
            joinColumns = @JoinColumn(name = "MembershipId"),
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
    // @info.archinnov.achilles.annotations.Column("name")
    private String name;

    @JsonGetter
    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    @javax.persistence.Transient
    public String getName() {
        return name;
    }

    @JsonSetter
    public void setName(String name) {
        this.name = name;
    }



    // @info.archinnov.achilles.annotations.Column("visibility")
    // @info.archinnov.achilles.annotations.Enumerated(info.archinnov.achilles.annotations.Enumerated.Encoding.NAME)
    private VisibilityKind visibility;

    @JsonGetter
    @javax.persistence.Enumerated(EnumType.STRING)
    public VisibilityKind getVisibility() {
        return visibility;
    }

    @JsonSetter
    public void setVisibility(VisibilityKind visibility) {
        this.visibility = visibility;
    }



    // @info.archinnov.achilles.annotations.Column("source")
    private Collection<Element> source;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @ManyToAny(metaDef = "ElementMetaDef", metaColumn = @javax.persistence.Column(name = "sourceType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Membership_source",
            joinColumns = @JoinColumn(name = "MembershipId"),
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



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedElement")
    private Collection<Element> ownedElement;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @javax.persistence.Transient
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



    // @info.archinnov.achilles.annotations.Column("memberName")
    private String memberName;

    @JsonGetter
    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    @javax.persistence.Column(name = "memberName", table = "Membership")
    public String getMemberName() {
        return memberName;
    }

    @JsonSetter
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedMemberElement")
    private Element ownedMemberElement;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @javax.persistence.Transient
    public Element getOwnedMemberElement() {
        return ownedMemberElement;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = ElementImpl.class)
    public void setOwnedMemberElement(Element ownedMemberElement) {
        this.ownedMemberElement = ownedMemberElement;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("owner")
    private Element owner;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @javax.persistence.Transient
    public Element getOwner() {
        return owner;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = ElementImpl.class)
    public void setOwner(Element owner) {
        this.owner = owner;
    }



}
