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

@Entity(name = "DefinitionImpl")
@SecondaryTable(name = "Definition")
@org.hibernate.annotations.Table(appliesTo = "Definition", fetch = FetchMode.SELECT, optional = false)
// @info.archinnov.achilles.annotations.Table(table = "Definition")
@DiscriminatorValue(value = "Definition")
@JsonTypeName(value = "Definition")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
public abstract class DefinitionImpl extends MofObjectImpl implements Definition {
    // @info.archinnov.achilles.annotations.Column("aliasId")
    private List<String> aliasId;

    @JsonGetter
    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "Definition_aliasId",
            joinColumns = @JoinColumn(name = "DefinitionId"))
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
    // @info.archinnov.achilles.annotations.Column("directedFeature")
    private List<Feature> directedFeature;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "FeatureMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_directedFeature",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<Feature> getDirectedFeature() {
        if (directedFeature == null) {
            directedFeature = new ArrayList<>();
        }
        return directedFeature;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = FeatureImpl.class)
    public void setDirectedFeature(List<Feature> directedFeature) {
        this.directedFeature = directedFeature;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("directedUsage")
    private List<Usage> directedUsage;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "UsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_directedUsage",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<Usage> getDirectedUsage() {
        if (directedUsage == null) {
            directedUsage = new ArrayList<>();
        }
        return directedUsage;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = UsageImpl.class)
    public void setDirectedUsage(List<Usage> directedUsage) {
        this.directedUsage = directedUsage;
    }



    // @info.archinnov.achilles.annotations.Column("disjoiningTypeDisjoining")
    private Collection<Disjoining> disjoiningTypeDisjoining;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @ManyToAny(metaDef = "DisjoiningMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_disjoiningTypeDisjoining",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public Collection<Disjoining> getDisjoiningTypeDisjoining() {
        if (disjoiningTypeDisjoining == null) {
            disjoiningTypeDisjoining = new ArrayList<>();
        }
        return disjoiningTypeDisjoining;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = DisjoiningImpl.class)
    public void setDisjoiningTypeDisjoining(Collection<Disjoining> disjoiningTypeDisjoining) {
        this.disjoiningTypeDisjoining = disjoiningTypeDisjoining;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("disjointType")
    private Collection<Type> disjointType;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "TypeMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_disjointType",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public Collection<Type> getDisjointType() {
        if (disjointType == null) {
            disjointType = new ArrayList<>();
        }
        return disjointType;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = TypeImpl.class)
    public void setDisjointType(Collection<Type> disjointType) {
        this.disjointType = disjointType;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("documentation")
    private List<Documentation> documentation;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "DocumentationMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_documentation",
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
    @JoinTable(name = "Definition_documentationComment",
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
    // @info.archinnov.achilles.annotations.Column("effectiveName")
    private String effectiveName;

    @JsonGetter
    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    // @javax.persistence.Transient
    @javax.persistence.Column(name = "effectiveName", table = "Definition")
    public String getEffectiveName() {
        return effectiveName;
    }

    @JsonSetter
    public void setEffectiveName(String effectiveName) {
        this.effectiveName = effectiveName;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("endFeature")
    private List<Feature> endFeature;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "FeatureMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_endFeature",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<Feature> getEndFeature() {
        if (endFeature == null) {
            endFeature = new ArrayList<>();
        }
        return endFeature;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = FeatureImpl.class)
    public void setEndFeature(List<Feature> endFeature) {
        this.endFeature = endFeature;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("feature")
    private List<Feature> feature;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "FeatureMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_feature",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<Feature> getFeature() {
        if (feature == null) {
            feature = new ArrayList<>();
        }
        return feature;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = FeatureImpl.class)
    public void setFeature(List<Feature> feature) {
        this.feature = feature;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("featureMembership")
    private List<FeatureMembership> featureMembership;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "FeatureMembershipMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_featureMembership",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<FeatureMembership> getFeatureMembership() {
        if (featureMembership == null) {
            featureMembership = new ArrayList<>();
        }
        return featureMembership;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = FeatureMembershipImpl.class)
    public void setFeatureMembership(List<FeatureMembership> featureMembership) {
        this.featureMembership = featureMembership;
    }



    // @info.archinnov.achilles.annotations.Column("humanId")
    private String humanId;

    @JsonGetter
    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    @javax.persistence.Column(name = "humanId", table = "Definition")
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
    @javax.persistence.Column(name = "identifier", table = "Definition")
    public java.util.UUID getIdentifier() {
        return identifier;
    }

    @JsonSetter
    public void setIdentifier(java.util.UUID identifier) {
        this.identifier = identifier;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("importedMembership")
    private List<Membership> importedMembership;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "MembershipMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_importedMembership",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<Membership> getImportedMembership() {
        if (importedMembership == null) {
            importedMembership = new ArrayList<>();
        }
        return importedMembership;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = MembershipImpl.class)
    public void setImportedMembership(List<Membership> importedMembership) {
        this.importedMembership = importedMembership;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("inheritedFeature")
    private List<Feature> inheritedFeature;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "FeatureMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_inheritedFeature",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<Feature> getInheritedFeature() {
        if (inheritedFeature == null) {
            inheritedFeature = new ArrayList<>();
        }
        return inheritedFeature;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = FeatureImpl.class)
    public void setInheritedFeature(List<Feature> inheritedFeature) {
        this.inheritedFeature = inheritedFeature;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("inheritedMembership")
    private List<Membership> inheritedMembership;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "MembershipMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_inheritedMembership",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<Membership> getInheritedMembership() {
        if (inheritedMembership == null) {
            inheritedMembership = new ArrayList<>();
        }
        return inheritedMembership;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = MembershipImpl.class)
    public void setInheritedMembership(List<Membership> inheritedMembership) {
        this.inheritedMembership = inheritedMembership;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("input")
    private List<Feature> input;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "FeatureMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_input",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<Feature> getInput() {
        if (input == null) {
            input = new ArrayList<>();
        }
        return input;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = FeatureImpl.class)
    public void setInput(List<Feature> input) {
        this.input = input;
    }



    // @info.archinnov.achilles.annotations.Column("isAbstract")
    private Boolean isAbstract;

    @JsonGetter
    @javax.persistence.Column(name = "isAbstract", table = "Definition")
    public Boolean getIsAbstract() {
        return isAbstract;
    }

    @JsonSetter
    public void setIsAbstract(Boolean isAbstract) {
        this.isAbstract = isAbstract;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("isConjugated")
    private Boolean isConjugated;

    @JsonGetter
    // @javax.persistence.Transient
    @javax.persistence.Column(name = "isConjugated", table = "Definition")
    public Boolean getIsConjugated() {
        return isConjugated;
    }

    @JsonSetter
    public void setIsConjugated(Boolean isConjugated) {
        this.isConjugated = isConjugated;
    }



    // @info.archinnov.achilles.annotations.Column("isSufficient")
    private Boolean isSufficient;

    @JsonGetter
    @javax.persistence.Column(name = "isSufficient", table = "Definition")
    public Boolean getIsSufficient() {
        return isSufficient;
    }

    @JsonSetter
    public void setIsSufficient(Boolean isSufficient) {
        this.isSufficient = isSufficient;
    }



    // @info.archinnov.achilles.annotations.Column("isVariation")
    private Boolean isVariation;

    @JsonGetter
    @javax.persistence.Column(name = "isVariation", table = "Definition")
    public Boolean getIsVariation() {
        return isVariation;
    }

    @JsonSetter
    public void setIsVariation(Boolean isVariation) {
        this.isVariation = isVariation;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("member")
    private List<Element> member;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ElementMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_member",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<Element> getMember() {
        if (member == null) {
            member = new ArrayList<>();
        }
        return member;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ElementImpl.class)
    public void setMember(List<Element> member) {
        this.member = member;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("membership")
    private List<Membership> membership;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "MembershipMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_membership",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<Membership> getMembership() {
        if (membership == null) {
            membership = new ArrayList<>();
        }
        return membership;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = MembershipImpl.class)
    public void setMembership(List<Membership> membership) {
        this.membership = membership;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("multiplicity")
    private Multiplicity multiplicity;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @Any(metaDef = "MultiplicityMetaDef", metaColumn = @javax.persistence.Column(name = "multiplicityType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "multiplicityId", table = "Definition")
    public Multiplicity getMultiplicity() {
        return multiplicity;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = MultiplicityImpl.class)
    public void setMultiplicity(Multiplicity multiplicity) {
        this.multiplicity = multiplicity;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("name")
    private String name;

    @JsonGetter
    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    // @javax.persistence.Transient
    @javax.persistence.Column(name = "name", table = "Definition")
    public String getName() {
        return name;
    }

    @JsonSetter
    public void setName(String name) {
        this.name = name;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("output")
    private List<Feature> output;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "FeatureMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_output",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<Feature> getOutput() {
        if (output == null) {
            output = new ArrayList<>();
        }
        return output;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = FeatureImpl.class)
    public void setOutput(List<Feature> output) {
        this.output = output;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedAction")
    private List<ActionUsage> ownedAction;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ActionUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_ownedAction",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<ActionUsage> getOwnedAction() {
        if (ownedAction == null) {
            ownedAction = new ArrayList<>();
        }
        return ownedAction;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ActionUsageImpl.class)
    public void setOwnedAction(List<ActionUsage> ownedAction) {
        this.ownedAction = ownedAction;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedAllocation")
    private List<AllocationUsage> ownedAllocation;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "AllocationUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_ownedAllocation",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<AllocationUsage> getOwnedAllocation() {
        if (ownedAllocation == null) {
            ownedAllocation = new ArrayList<>();
        }
        return ownedAllocation;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = AllocationUsageImpl.class)
    public void setOwnedAllocation(List<AllocationUsage> ownedAllocation) {
        this.ownedAllocation = ownedAllocation;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedAnalysisCase")
    private List<AnalysisCaseUsage> ownedAnalysisCase;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "AnalysisCaseUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_ownedAnalysisCase",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<AnalysisCaseUsage> getOwnedAnalysisCase() {
        if (ownedAnalysisCase == null) {
            ownedAnalysisCase = new ArrayList<>();
        }
        return ownedAnalysisCase;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = AnalysisCaseUsageImpl.class)
    public void setOwnedAnalysisCase(List<AnalysisCaseUsage> ownedAnalysisCase) {
        this.ownedAnalysisCase = ownedAnalysisCase;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedAnnotation")
    private List<Annotation> ownedAnnotation;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "AnnotationMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_ownedAnnotation",
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
    // @info.archinnov.achilles.annotations.Column("ownedAttribute")
    private List<AttributeUsage> ownedAttribute;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "AttributeUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_ownedAttribute",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<AttributeUsage> getOwnedAttribute() {
        if (ownedAttribute == null) {
            ownedAttribute = new ArrayList<>();
        }
        return ownedAttribute;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = AttributeUsageImpl.class)
    public void setOwnedAttribute(List<AttributeUsage> ownedAttribute) {
        this.ownedAttribute = ownedAttribute;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedCalculation")
    private List<CalculationUsage> ownedCalculation;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "CalculationUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_ownedCalculation",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<CalculationUsage> getOwnedCalculation() {
        if (ownedCalculation == null) {
            ownedCalculation = new ArrayList<>();
        }
        return ownedCalculation;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = CalculationUsageImpl.class)
    public void setOwnedCalculation(List<CalculationUsage> ownedCalculation) {
        this.ownedCalculation = ownedCalculation;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedCase")
    private List<CaseUsage> ownedCase;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "CaseUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_ownedCase",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<CaseUsage> getOwnedCase() {
        if (ownedCase == null) {
            ownedCase = new ArrayList<>();
        }
        return ownedCase;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = CaseUsageImpl.class)
    public void setOwnedCase(List<CaseUsage> ownedCase) {
        this.ownedCase = ownedCase;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedConcern")
    private Collection<ConcernUsage> ownedConcern;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ConcernUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_ownedConcern",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public Collection<ConcernUsage> getOwnedConcern() {
        if (ownedConcern == null) {
            ownedConcern = new ArrayList<>();
        }
        return ownedConcern;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ConcernUsageImpl.class)
    public void setOwnedConcern(Collection<ConcernUsage> ownedConcern) {
        this.ownedConcern = ownedConcern;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedConjugator")
    private Conjugation ownedConjugator;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @Any(metaDef = "ConjugationMetaDef", metaColumn = @javax.persistence.Column(name = "ownedConjugatorType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "ownedConjugatorId", table = "Definition")
    public Conjugation getOwnedConjugator() {
        return ownedConjugator;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = ConjugationImpl.class)
    public void setOwnedConjugator(Conjugation ownedConjugator) {
        this.ownedConjugator = ownedConjugator;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedConnection")
    private List<ConnectorAsUsage> ownedConnection;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ConnectorAsUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_ownedConnection",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<ConnectorAsUsage> getOwnedConnection() {
        if (ownedConnection == null) {
            ownedConnection = new ArrayList<>();
        }
        return ownedConnection;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ConnectorAsUsageImpl.class)
    public void setOwnedConnection(List<ConnectorAsUsage> ownedConnection) {
        this.ownedConnection = ownedConnection;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedConstraint")
    private List<ConstraintUsage> ownedConstraint;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ConstraintUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_ownedConstraint",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<ConstraintUsage> getOwnedConstraint() {
        if (ownedConstraint == null) {
            ownedConstraint = new ArrayList<>();
        }
        return ownedConstraint;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ConstraintUsageImpl.class)
    public void setOwnedConstraint(List<ConstraintUsage> ownedConstraint) {
        this.ownedConstraint = ownedConstraint;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedElement")
    private List<Element> ownedElement;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ElementMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_ownedElement",
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



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedEndFeature")
    private List<Feature> ownedEndFeature;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "FeatureMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_ownedEndFeature",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<Feature> getOwnedEndFeature() {
        if (ownedEndFeature == null) {
            ownedEndFeature = new ArrayList<>();
        }
        return ownedEndFeature;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = FeatureImpl.class)
    public void setOwnedEndFeature(List<Feature> ownedEndFeature) {
        this.ownedEndFeature = ownedEndFeature;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedEnumeration")
    private List<EnumerationUsage> ownedEnumeration;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "EnumerationUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_ownedEnumeration",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<EnumerationUsage> getOwnedEnumeration() {
        if (ownedEnumeration == null) {
            ownedEnumeration = new ArrayList<>();
        }
        return ownedEnumeration;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = EnumerationUsageImpl.class)
    public void setOwnedEnumeration(List<EnumerationUsage> ownedEnumeration) {
        this.ownedEnumeration = ownedEnumeration;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedFeature")
    private List<Feature> ownedFeature;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "FeatureMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_ownedFeature",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<Feature> getOwnedFeature() {
        if (ownedFeature == null) {
            ownedFeature = new ArrayList<>();
        }
        return ownedFeature;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = FeatureImpl.class)
    public void setOwnedFeature(List<Feature> ownedFeature) {
        this.ownedFeature = ownedFeature;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedFeatureMembership")
    private List<FeatureMembership> ownedFeatureMembership;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "FeatureMembershipMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_ownedFeatureMembership",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<FeatureMembership> getOwnedFeatureMembership() {
        if (ownedFeatureMembership == null) {
            ownedFeatureMembership = new ArrayList<>();
        }
        return ownedFeatureMembership;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = FeatureMembershipImpl.class)
    public void setOwnedFeatureMembership(List<FeatureMembership> ownedFeatureMembership) {
        this.ownedFeatureMembership = ownedFeatureMembership;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedFlow")
    private Collection<FlowConnectionUsage> ownedFlow;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "FlowConnectionUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_ownedFlow",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public Collection<FlowConnectionUsage> getOwnedFlow() {
        if (ownedFlow == null) {
            ownedFlow = new ArrayList<>();
        }
        return ownedFlow;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = FlowConnectionUsageImpl.class)
    public void setOwnedFlow(Collection<FlowConnectionUsage> ownedFlow) {
        this.ownedFlow = ownedFlow;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedImport")
    private List<Import> ownedImport;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ImportMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_ownedImport",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<Import> getOwnedImport() {
        if (ownedImport == null) {
            ownedImport = new ArrayList<>();
        }
        return ownedImport;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ImportImpl.class)
    public void setOwnedImport(List<Import> ownedImport) {
        this.ownedImport = ownedImport;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedInterface")
    private List<InterfaceUsage> ownedInterface;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "InterfaceUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_ownedInterface",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<InterfaceUsage> getOwnedInterface() {
        if (ownedInterface == null) {
            ownedInterface = new ArrayList<>();
        }
        return ownedInterface;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = InterfaceUsageImpl.class)
    public void setOwnedInterface(List<InterfaceUsage> ownedInterface) {
        this.ownedInterface = ownedInterface;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedItem")
    private List<ItemUsage> ownedItem;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ItemUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_ownedItem",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<ItemUsage> getOwnedItem() {
        if (ownedItem == null) {
            ownedItem = new ArrayList<>();
        }
        return ownedItem;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ItemUsageImpl.class)
    public void setOwnedItem(List<ItemUsage> ownedItem) {
        this.ownedItem = ownedItem;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedMember")
    private List<Element> ownedMember;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ElementMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_ownedMember",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<Element> getOwnedMember() {
        if (ownedMember == null) {
            ownedMember = new ArrayList<>();
        }
        return ownedMember;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ElementImpl.class)
    public void setOwnedMember(List<Element> ownedMember) {
        this.ownedMember = ownedMember;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedMembership")
    private List<Membership> ownedMembership;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "MembershipMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_ownedMembership",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<Membership> getOwnedMembership() {
        if (ownedMembership == null) {
            ownedMembership = new ArrayList<>();
        }
        return ownedMembership;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = MembershipImpl.class)
    public void setOwnedMembership(List<Membership> ownedMembership) {
        this.ownedMembership = ownedMembership;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedOccurrence")
    private List<OccurrenceUsage> ownedOccurrence;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "OccurrenceUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_ownedOccurrence",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<OccurrenceUsage> getOwnedOccurrence() {
        if (ownedOccurrence == null) {
            ownedOccurrence = new ArrayList<>();
        }
        return ownedOccurrence;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = OccurrenceUsageImpl.class)
    public void setOwnedOccurrence(List<OccurrenceUsage> ownedOccurrence) {
        this.ownedOccurrence = ownedOccurrence;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedPart")
    private List<PartUsage> ownedPart;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "PartUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_ownedPart",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<PartUsage> getOwnedPart() {
        if (ownedPart == null) {
            ownedPart = new ArrayList<>();
        }
        return ownedPart;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = PartUsageImpl.class)
    public void setOwnedPart(List<PartUsage> ownedPart) {
        this.ownedPart = ownedPart;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedPort")
    private List<PortUsage> ownedPort;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "PortUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_ownedPort",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<PortUsage> getOwnedPort() {
        if (ownedPort == null) {
            ownedPort = new ArrayList<>();
        }
        return ownedPort;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = PortUsageImpl.class)
    public void setOwnedPort(List<PortUsage> ownedPort) {
        this.ownedPort = ownedPort;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedReference")
    private List<ReferenceUsage> ownedReference;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ReferenceUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_ownedReference",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<ReferenceUsage> getOwnedReference() {
        if (ownedReference == null) {
            ownedReference = new ArrayList<>();
        }
        return ownedReference;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ReferenceUsageImpl.class)
    public void setOwnedReference(List<ReferenceUsage> ownedReference) {
        this.ownedReference = ownedReference;
    }



    // @info.archinnov.achilles.annotations.Column("ownedRelationship")
    private List<Relationship> ownedRelationship;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @ManyToAny(metaDef = "RelationshipMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_ownedRelationship",
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



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedRendering")
    private List<RenderingUsage> ownedRendering;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "RenderingUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_ownedRendering",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<RenderingUsage> getOwnedRendering() {
        if (ownedRendering == null) {
            ownedRendering = new ArrayList<>();
        }
        return ownedRendering;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = RenderingUsageImpl.class)
    public void setOwnedRendering(List<RenderingUsage> ownedRendering) {
        this.ownedRendering = ownedRendering;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedRequirement")
    private List<RequirementUsage> ownedRequirement;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "RequirementUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_ownedRequirement",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<RequirementUsage> getOwnedRequirement() {
        if (ownedRequirement == null) {
            ownedRequirement = new ArrayList<>();
        }
        return ownedRequirement;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = RequirementUsageImpl.class)
    public void setOwnedRequirement(List<RequirementUsage> ownedRequirement) {
        this.ownedRequirement = ownedRequirement;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedSpecialization")
    private List<Specialization> ownedSpecialization;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "SpecializationMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_ownedSpecialization",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<Specialization> getOwnedSpecialization() {
        if (ownedSpecialization == null) {
            ownedSpecialization = new ArrayList<>();
        }
        return ownedSpecialization;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = SpecializationImpl.class)
    public void setOwnedSpecialization(List<Specialization> ownedSpecialization) {
        this.ownedSpecialization = ownedSpecialization;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedState")
    private List<StateUsage> ownedState;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "StateUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_ownedState",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<StateUsage> getOwnedState() {
        if (ownedState == null) {
            ownedState = new ArrayList<>();
        }
        return ownedState;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = StateUsageImpl.class)
    public void setOwnedState(List<StateUsage> ownedState) {
        this.ownedState = ownedState;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedSubclassification")
    private Collection<Subclassification> ownedSubclassification;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "SubclassificationMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_ownedSubclassification",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public Collection<Subclassification> getOwnedSubclassification() {
        if (ownedSubclassification == null) {
            ownedSubclassification = new ArrayList<>();
        }
        return ownedSubclassification;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = SubclassificationImpl.class)
    public void setOwnedSubclassification(Collection<Subclassification> ownedSubclassification) {
        this.ownedSubclassification = ownedSubclassification;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedTextualRepresentation")
    private Collection<TextualRepresentation> ownedTextualRepresentation;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "TextualRepresentationMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_ownedTextualRepresentation",
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
    // @info.archinnov.achilles.annotations.Column("ownedTransition")
    private Collection<TransitionUsage> ownedTransition;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "TransitionUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_ownedTransition",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public Collection<TransitionUsage> getOwnedTransition() {
        if (ownedTransition == null) {
            ownedTransition = new ArrayList<>();
        }
        return ownedTransition;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = TransitionUsageImpl.class)
    public void setOwnedTransition(Collection<TransitionUsage> ownedTransition) {
        this.ownedTransition = ownedTransition;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedUsage")
    private List<Usage> ownedUsage;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "UsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_ownedUsage",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<Usage> getOwnedUsage() {
        if (ownedUsage == null) {
            ownedUsage = new ArrayList<>();
        }
        return ownedUsage;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = UsageImpl.class)
    public void setOwnedUsage(List<Usage> ownedUsage) {
        this.ownedUsage = ownedUsage;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedUseCase")
    private List<UseCaseUsage> ownedUseCase;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "UseCaseUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_ownedUseCase",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<UseCaseUsage> getOwnedUseCase() {
        if (ownedUseCase == null) {
            ownedUseCase = new ArrayList<>();
        }
        return ownedUseCase;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = UseCaseUsageImpl.class)
    public void setOwnedUseCase(List<UseCaseUsage> ownedUseCase) {
        this.ownedUseCase = ownedUseCase;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedVerificationCase")
    private List<VerificationCaseUsage> ownedVerificationCase;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "VerificationCaseUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_ownedVerificationCase",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<VerificationCaseUsage> getOwnedVerificationCase() {
        if (ownedVerificationCase == null) {
            ownedVerificationCase = new ArrayList<>();
        }
        return ownedVerificationCase;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = VerificationCaseUsageImpl.class)
    public void setOwnedVerificationCase(List<VerificationCaseUsage> ownedVerificationCase) {
        this.ownedVerificationCase = ownedVerificationCase;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedView")
    private List<ViewUsage> ownedView;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ViewUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_ownedView",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<ViewUsage> getOwnedView() {
        if (ownedView == null) {
            ownedView = new ArrayList<>();
        }
        return ownedView;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ViewUsageImpl.class)
    public void setOwnedView(List<ViewUsage> ownedView) {
        this.ownedView = ownedView;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedViewpoint")
    private List<ViewpointUsage> ownedViewpoint;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ViewpointUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_ownedViewpoint",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<ViewpointUsage> getOwnedViewpoint() {
        if (ownedViewpoint == null) {
            ownedViewpoint = new ArrayList<>();
        }
        return ownedViewpoint;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ViewpointUsageImpl.class)
    public void setOwnedViewpoint(List<ViewpointUsage> ownedViewpoint) {
        this.ownedViewpoint = ownedViewpoint;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("owner")
    private Element owner;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @Any(metaDef = "ElementMetaDef", metaColumn = @javax.persistence.Column(name = "ownerType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "ownerId", table = "Definition")
    public Element getOwner() {
        return owner;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = ElementImpl.class)
    public void setOwner(Element owner) {
        this.owner = owner;
    }



    // @info.archinnov.achilles.annotations.Column("owningMembership")
    private Membership owningMembership;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "MembershipMetaDef", metaColumn = @javax.persistence.Column(name = "owningMembershipType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "owningMembershipId", table = "Definition")
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
    @JoinColumn(name = "owningNamespaceId", table = "Definition")
    public Namespace getOwningNamespace() {
        return owningNamespace;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = NamespaceImpl.class)
    public void setOwningNamespace(Namespace owningNamespace) {
        this.owningNamespace = owningNamespace;
    }



    // @info.archinnov.achilles.annotations.Column("owningRelationship")
    private Relationship owningRelationship;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "RelationshipMetaDef", metaColumn = @javax.persistence.Column(name = "owningRelationshipType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "owningRelationshipId", table = "Definition")
    public Relationship getOwningRelationship() {
        return owningRelationship;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = RelationshipImpl.class)
    public void setOwningRelationship(Relationship owningRelationship) {
        this.owningRelationship = owningRelationship;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("qualifiedName")
    private String qualifiedName;

    @JsonGetter
    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    // @javax.persistence.Transient
    @javax.persistence.Column(name = "qualifiedName", table = "Definition")
    public String getQualifiedName() {
        return qualifiedName;
    }

    @JsonSetter
    public void setQualifiedName(String qualifiedName) {
        this.qualifiedName = qualifiedName;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("usage")
    private List<Usage> usage;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "UsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_usage",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<Usage> getUsage() {
        if (usage == null) {
            usage = new ArrayList<>();
        }
        return usage;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = UsageImpl.class)
    public void setUsage(List<Usage> usage) {
        this.usage = usage;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("variant")
    private Collection<Usage> variant;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "UsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_variant",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public Collection<Usage> getVariant() {
        if (variant == null) {
            variant = new ArrayList<>();
        }
        return variant;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = UsageImpl.class)
    public void setVariant(Collection<Usage> variant) {
        this.variant = variant;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("variantMembership")
    private Collection<VariantMembership> variantMembership;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "VariantMembershipMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Definition_variantMembership",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public Collection<VariantMembership> getVariantMembership() {
        if (variantMembership == null) {
            variantMembership = new ArrayList<>();
        }
        return variantMembership;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = VariantMembershipImpl.class)
    public void setVariantMembership(Collection<VariantMembership> variantMembership) {
        this.variantMembership = variantMembership;
    }



}
