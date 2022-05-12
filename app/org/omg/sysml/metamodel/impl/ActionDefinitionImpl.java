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

@Entity(name = "ActionDefinitionImpl")
@SecondaryTable(name = "ActionDefinition")
@org.hibernate.annotations.Table(appliesTo = "ActionDefinition", fetch = FetchMode.SELECT, optional = false)
// @info.archinnov.achilles.annotations.Table(table = "ActionDefinition")
@DiscriminatorValue(value = "ActionDefinition")
@JsonTypeName(value = "ActionDefinition")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
public class ActionDefinitionImpl extends SysMLTypeImpl implements ActionDefinition {
    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("action")
    private List<ActionUsage> action;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ActionUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_action",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public List<ActionUsage> getAction() {
        if (action == null) {
            action = new ArrayList<>();
        }
        return action;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = ActionUsageImpl.class)
    public void setAction(List<ActionUsage> action) {
        this.action = action;
    }



    // @info.archinnov.achilles.annotations.Column("aliasIds")
    private List<String> aliasIds;

    @JsonGetter
    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "ActionDefinition_aliasIds",
            joinColumns = @JoinColumn(name = "ActionDefinition_id"))
    public List<String> getAliasIds() {
        if (aliasIds == null) {
            aliasIds = new ArrayList<>();
        }
        return aliasIds;
    }

    @JsonSetter
    public void setAliasIds(List<String> aliasIds) {
        this.aliasIds = aliasIds;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("directedFeature")
    private List<Feature> directedFeature;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "FeatureMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_directedFeature",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public List<Feature> getDirectedFeature() {
        if (directedFeature == null) {
            directedFeature = new ArrayList<>();
        }
        return directedFeature;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = FeatureImpl.class)
    public void setDirectedFeature(List<Feature> directedFeature) {
        this.directedFeature = directedFeature;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("directedUsage")
    private List<Usage> directedUsage;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "UsageMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_directedUsage",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public List<Usage> getDirectedUsage() {
        if (directedUsage == null) {
            directedUsage = new ArrayList<>();
        }
        return directedUsage;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = UsageImpl.class)
    public void setDirectedUsage(List<Usage> directedUsage) {
        this.directedUsage = directedUsage;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("documentation")
    private List<Documentation> documentation;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "DocumentationMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_documentation",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
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
    @javax.persistence.Column(name = "effectiveName", table = "ActionDefinition")
    public String getEffectiveName() {
        return effectiveName;
    }

    @JsonSetter
    public void setEffectiveName(String effectiveName) {
        this.effectiveName = effectiveName;
    }



    // @info.archinnov.achilles.annotations.Column("elementId")
    private java.util.UUID elementId;

    @JsonGetter
    @javax.persistence.Column(name = "elementId", table = "ActionDefinition")
    public java.util.UUID getElementId() {
        return elementId;
    }

    @JsonSetter
    public void setElementId(java.util.UUID elementId) {
        this.elementId = elementId;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("endFeature")
    private List<Feature> endFeature;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "FeatureMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_endFeature",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public List<Feature> getEndFeature() {
        if (endFeature == null) {
            endFeature = new ArrayList<>();
        }
        return endFeature;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = FeatureImpl.class)
    public void setEndFeature(List<Feature> endFeature) {
        this.endFeature = endFeature;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("feature")
    private List<Feature> feature;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "FeatureMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_feature",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public List<Feature> getFeature() {
        if (feature == null) {
            feature = new ArrayList<>();
        }
        return feature;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = FeatureImpl.class)
    public void setFeature(List<Feature> feature) {
        this.feature = feature;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("featureMembership")
    private List<FeatureMembership> featureMembership;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "FeatureMembershipMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_featureMembership",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public List<FeatureMembership> getFeatureMembership() {
        if (featureMembership == null) {
            featureMembership = new ArrayList<>();
        }
        return featureMembership;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = FeatureMembershipImpl.class)
    public void setFeatureMembership(List<FeatureMembership> featureMembership) {
        this.featureMembership = featureMembership;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("importedMembership")
    private List<Membership> importedMembership;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "MembershipMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_importedMembership",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public List<Membership> getImportedMembership() {
        if (importedMembership == null) {
            importedMembership = new ArrayList<>();
        }
        return importedMembership;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = MembershipImpl.class)
    public void setImportedMembership(List<Membership> importedMembership) {
        this.importedMembership = importedMembership;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("inheritedFeature")
    private List<Feature> inheritedFeature;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "FeatureMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_inheritedFeature",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public List<Feature> getInheritedFeature() {
        if (inheritedFeature == null) {
            inheritedFeature = new ArrayList<>();
        }
        return inheritedFeature;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = FeatureImpl.class)
    public void setInheritedFeature(List<Feature> inheritedFeature) {
        this.inheritedFeature = inheritedFeature;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("inheritedMembership")
    private List<Membership> inheritedMembership;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "MembershipMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_inheritedMembership",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public List<Membership> getInheritedMembership() {
        if (inheritedMembership == null) {
            inheritedMembership = new ArrayList<>();
        }
        return inheritedMembership;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = MembershipImpl.class)
    public void setInheritedMembership(List<Membership> inheritedMembership) {
        this.inheritedMembership = inheritedMembership;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("input")
    private List<Feature> input;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "FeatureMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_input",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public List<Feature> getInput() {
        if (input == null) {
            input = new ArrayList<>();
        }
        return input;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = FeatureImpl.class)
    public void setInput(List<Feature> input) {
        this.input = input;
    }



    // @info.archinnov.achilles.annotations.Column("isAbstract")
    private Boolean isAbstract;

    @JsonGetter
    @javax.persistence.Column(name = "isAbstract", table = "ActionDefinition")
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
    @javax.persistence.Column(name = "isConjugated", table = "ActionDefinition")
    public Boolean getIsConjugated() {
        return isConjugated;
    }

    @JsonSetter
    public void setIsConjugated(Boolean isConjugated) {
        this.isConjugated = isConjugated;
    }



    // @info.archinnov.achilles.annotations.Column("isIndividual")
    private Boolean isIndividual;

    @JsonGetter
    @javax.persistence.Column(name = "isIndividual", table = "ActionDefinition")
    public Boolean getIsIndividual() {
        return isIndividual;
    }

    @JsonSetter
    public void setIsIndividual(Boolean isIndividual) {
        this.isIndividual = isIndividual;
    }



    // @info.archinnov.achilles.annotations.Column("isSufficient")
    private Boolean isSufficient;

    @JsonGetter
    @javax.persistence.Column(name = "isSufficient", table = "ActionDefinition")
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
    @javax.persistence.Column(name = "isVariation", table = "ActionDefinition")
    public Boolean getIsVariation() {
        return isVariation;
    }

    @JsonSetter
    public void setIsVariation(Boolean isVariation) {
        this.isVariation = isVariation;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("lifeClass")
    private LifeClass lifeClass;

    @JsonGetter
    @JsonSerialize(using = DataSerializer.class)
    // @javax.persistence.Transient
    @Any(metaDef = "LifeClassMetaDef", metaColumn = @javax.persistence.Column(name = "lifeClass_type"), fetch = FetchType.LAZY)
    @JoinColumn(name = "lifeClass_id", table = "ActionDefinition")
    public LifeClass getLifeClass() {
        return lifeClass;
    }

    @JsonSetter
    @JsonDeserialize(using = DataDeserializer.class, as = LifeClassImpl.class)
    public void setLifeClass(LifeClass lifeClass) {
        this.lifeClass = lifeClass;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("member")
    private List<Element> member;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ElementMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_member",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public List<Element> getMember() {
        if (member == null) {
            member = new ArrayList<>();
        }
        return member;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = ElementImpl.class)
    public void setMember(List<Element> member) {
        this.member = member;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("membership")
    private List<Membership> membership;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "MembershipMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_membership",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public List<Membership> getMembership() {
        if (membership == null) {
            membership = new ArrayList<>();
        }
        return membership;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = MembershipImpl.class)
    public void setMembership(List<Membership> membership) {
        this.membership = membership;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("multiplicity")
    private Multiplicity multiplicity;

    @JsonGetter
    @JsonSerialize(using = DataSerializer.class)
    // @javax.persistence.Transient
    @Any(metaDef = "MultiplicityMetaDef", metaColumn = @javax.persistence.Column(name = "multiplicity_type"), fetch = FetchType.LAZY)
    @JoinColumn(name = "multiplicity_id", table = "ActionDefinition")
    public Multiplicity getMultiplicity() {
        return multiplicity;
    }

    @JsonSetter
    @JsonDeserialize(using = DataDeserializer.class, as = MultiplicityImpl.class)
    public void setMultiplicity(Multiplicity multiplicity) {
        this.multiplicity = multiplicity;
    }



    // @info.archinnov.achilles.annotations.Column("name")
    private String name;

    @JsonGetter
    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    @javax.persistence.Column(name = "name", table = "ActionDefinition")
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
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "FeatureMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_output",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public List<Feature> getOutput() {
        if (output == null) {
            output = new ArrayList<>();
        }
        return output;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = FeatureImpl.class)
    public void setOutput(List<Feature> output) {
        this.output = output;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedAction")
    private List<ActionUsage> ownedAction;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ActionUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_ownedAction",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public List<ActionUsage> getOwnedAction() {
        if (ownedAction == null) {
            ownedAction = new ArrayList<>();
        }
        return ownedAction;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = ActionUsageImpl.class)
    public void setOwnedAction(List<ActionUsage> ownedAction) {
        this.ownedAction = ownedAction;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedAllocation")
    private List<AllocationUsage> ownedAllocation;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "AllocationUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_ownedAllocation",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public List<AllocationUsage> getOwnedAllocation() {
        if (ownedAllocation == null) {
            ownedAllocation = new ArrayList<>();
        }
        return ownedAllocation;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = AllocationUsageImpl.class)
    public void setOwnedAllocation(List<AllocationUsage> ownedAllocation) {
        this.ownedAllocation = ownedAllocation;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedAnalysisCase")
    private List<AnalysisCaseUsage> ownedAnalysisCase;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "AnalysisCaseUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_ownedAnalysisCase",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public List<AnalysisCaseUsage> getOwnedAnalysisCase() {
        if (ownedAnalysisCase == null) {
            ownedAnalysisCase = new ArrayList<>();
        }
        return ownedAnalysisCase;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = AnalysisCaseUsageImpl.class)
    public void setOwnedAnalysisCase(List<AnalysisCaseUsage> ownedAnalysisCase) {
        this.ownedAnalysisCase = ownedAnalysisCase;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedAnnotation")
    private List<Annotation> ownedAnnotation;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "AnnotationMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_ownedAnnotation",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
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
    // @info.archinnov.achilles.annotations.Column("ownedAttribute")
    private List<AttributeUsage> ownedAttribute;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "AttributeUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_ownedAttribute",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public List<AttributeUsage> getOwnedAttribute() {
        if (ownedAttribute == null) {
            ownedAttribute = new ArrayList<>();
        }
        return ownedAttribute;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = AttributeUsageImpl.class)
    public void setOwnedAttribute(List<AttributeUsage> ownedAttribute) {
        this.ownedAttribute = ownedAttribute;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedCalculation")
    private List<CalculationUsage> ownedCalculation;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "CalculationUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_ownedCalculation",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public List<CalculationUsage> getOwnedCalculation() {
        if (ownedCalculation == null) {
            ownedCalculation = new ArrayList<>();
        }
        return ownedCalculation;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = CalculationUsageImpl.class)
    public void setOwnedCalculation(List<CalculationUsage> ownedCalculation) {
        this.ownedCalculation = ownedCalculation;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedCase")
    private List<CaseUsage> ownedCase;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "CaseUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_ownedCase",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public List<CaseUsage> getOwnedCase() {
        if (ownedCase == null) {
            ownedCase = new ArrayList<>();
        }
        return ownedCase;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = CaseUsageImpl.class)
    public void setOwnedCase(List<CaseUsage> ownedCase) {
        this.ownedCase = ownedCase;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedConcern")
    private Collection<ConcernUsage> ownedConcern;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ConcernUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_ownedConcern",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public Collection<ConcernUsage> getOwnedConcern() {
        if (ownedConcern == null) {
            ownedConcern = new ArrayList<>();
        }
        return ownedConcern;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = ConcernUsageImpl.class)
    public void setOwnedConcern(Collection<ConcernUsage> ownedConcern) {
        this.ownedConcern = ownedConcern;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedConjugator")
    private Conjugation ownedConjugator;

    @JsonGetter
    @JsonSerialize(using = DataSerializer.class)
    // @javax.persistence.Transient
    @Any(metaDef = "ConjugationMetaDef", metaColumn = @javax.persistence.Column(name = "ownedConjugator_type"), fetch = FetchType.LAZY)
    @JoinColumn(name = "ownedConjugator_id", table = "ActionDefinition")
    public Conjugation getOwnedConjugator() {
        return ownedConjugator;
    }

    @JsonSetter
    @JsonDeserialize(using = DataDeserializer.class, as = ConjugationImpl.class)
    public void setOwnedConjugator(Conjugation ownedConjugator) {
        this.ownedConjugator = ownedConjugator;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedConnection")
    private List<ConnectorAsUsage> ownedConnection;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ConnectorAsUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_ownedConnection",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public List<ConnectorAsUsage> getOwnedConnection() {
        if (ownedConnection == null) {
            ownedConnection = new ArrayList<>();
        }
        return ownedConnection;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = ConnectorAsUsageImpl.class)
    public void setOwnedConnection(List<ConnectorAsUsage> ownedConnection) {
        this.ownedConnection = ownedConnection;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedConstraint")
    private List<ConstraintUsage> ownedConstraint;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ConstraintUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_ownedConstraint",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public List<ConstraintUsage> getOwnedConstraint() {
        if (ownedConstraint == null) {
            ownedConstraint = new ArrayList<>();
        }
        return ownedConstraint;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = ConstraintUsageImpl.class)
    public void setOwnedConstraint(List<ConstraintUsage> ownedConstraint) {
        this.ownedConstraint = ownedConstraint;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedDisjoining")
    private Collection<Disjoining> ownedDisjoining;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "DisjoiningMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_ownedDisjoining",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public Collection<Disjoining> getOwnedDisjoining() {
        if (ownedDisjoining == null) {
            ownedDisjoining = new ArrayList<>();
        }
        return ownedDisjoining;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = DisjoiningImpl.class)
    public void setOwnedDisjoining(Collection<Disjoining> ownedDisjoining) {
        this.ownedDisjoining = ownedDisjoining;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedElement")
    private List<Element> ownedElement;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ElementMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_ownedElement",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
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



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedEndFeature")
    private List<Feature> ownedEndFeature;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "FeatureMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_ownedEndFeature",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public List<Feature> getOwnedEndFeature() {
        if (ownedEndFeature == null) {
            ownedEndFeature = new ArrayList<>();
        }
        return ownedEndFeature;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = FeatureImpl.class)
    public void setOwnedEndFeature(List<Feature> ownedEndFeature) {
        this.ownedEndFeature = ownedEndFeature;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedEnumeration")
    private List<EnumerationUsage> ownedEnumeration;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "EnumerationUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_ownedEnumeration",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public List<EnumerationUsage> getOwnedEnumeration() {
        if (ownedEnumeration == null) {
            ownedEnumeration = new ArrayList<>();
        }
        return ownedEnumeration;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = EnumerationUsageImpl.class)
    public void setOwnedEnumeration(List<EnumerationUsage> ownedEnumeration) {
        this.ownedEnumeration = ownedEnumeration;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedFeature")
    private List<Feature> ownedFeature;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "FeatureMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_ownedFeature",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public List<Feature> getOwnedFeature() {
        if (ownedFeature == null) {
            ownedFeature = new ArrayList<>();
        }
        return ownedFeature;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = FeatureImpl.class)
    public void setOwnedFeature(List<Feature> ownedFeature) {
        this.ownedFeature = ownedFeature;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedFeatureMembership")
    private List<FeatureMembership> ownedFeatureMembership;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "FeatureMembershipMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_ownedFeatureMembership",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public List<FeatureMembership> getOwnedFeatureMembership() {
        if (ownedFeatureMembership == null) {
            ownedFeatureMembership = new ArrayList<>();
        }
        return ownedFeatureMembership;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = FeatureMembershipImpl.class)
    public void setOwnedFeatureMembership(List<FeatureMembership> ownedFeatureMembership) {
        this.ownedFeatureMembership = ownedFeatureMembership;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedFlow")
    private Collection<FlowConnectionUsage> ownedFlow;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "FlowConnectionUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_ownedFlow",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public Collection<FlowConnectionUsage> getOwnedFlow() {
        if (ownedFlow == null) {
            ownedFlow = new ArrayList<>();
        }
        return ownedFlow;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = FlowConnectionUsageImpl.class)
    public void setOwnedFlow(Collection<FlowConnectionUsage> ownedFlow) {
        this.ownedFlow = ownedFlow;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedImport")
    private List<Import> ownedImport;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ImportMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_ownedImport",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public List<Import> getOwnedImport() {
        if (ownedImport == null) {
            ownedImport = new ArrayList<>();
        }
        return ownedImport;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = ImportImpl.class)
    public void setOwnedImport(List<Import> ownedImport) {
        this.ownedImport = ownedImport;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedInterface")
    private List<InterfaceUsage> ownedInterface;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "InterfaceUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_ownedInterface",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public List<InterfaceUsage> getOwnedInterface() {
        if (ownedInterface == null) {
            ownedInterface = new ArrayList<>();
        }
        return ownedInterface;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = InterfaceUsageImpl.class)
    public void setOwnedInterface(List<InterfaceUsage> ownedInterface) {
        this.ownedInterface = ownedInterface;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedItem")
    private List<ItemUsage> ownedItem;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ItemUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_ownedItem",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public List<ItemUsage> getOwnedItem() {
        if (ownedItem == null) {
            ownedItem = new ArrayList<>();
        }
        return ownedItem;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = ItemUsageImpl.class)
    public void setOwnedItem(List<ItemUsage> ownedItem) {
        this.ownedItem = ownedItem;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedMember")
    private List<Element> ownedMember;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ElementMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_ownedMember",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public List<Element> getOwnedMember() {
        if (ownedMember == null) {
            ownedMember = new ArrayList<>();
        }
        return ownedMember;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = ElementImpl.class)
    public void setOwnedMember(List<Element> ownedMember) {
        this.ownedMember = ownedMember;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedMembership")
    private List<Membership> ownedMembership;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "MembershipMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_ownedMembership",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public List<Membership> getOwnedMembership() {
        if (ownedMembership == null) {
            ownedMembership = new ArrayList<>();
        }
        return ownedMembership;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = MembershipImpl.class)
    public void setOwnedMembership(List<Membership> ownedMembership) {
        this.ownedMembership = ownedMembership;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedOccurrence")
    private List<OccurrenceUsage> ownedOccurrence;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "OccurrenceUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_ownedOccurrence",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public List<OccurrenceUsage> getOwnedOccurrence() {
        if (ownedOccurrence == null) {
            ownedOccurrence = new ArrayList<>();
        }
        return ownedOccurrence;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = OccurrenceUsageImpl.class)
    public void setOwnedOccurrence(List<OccurrenceUsage> ownedOccurrence) {
        this.ownedOccurrence = ownedOccurrence;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedPart")
    private List<PartUsage> ownedPart;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "PartUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_ownedPart",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public List<PartUsage> getOwnedPart() {
        if (ownedPart == null) {
            ownedPart = new ArrayList<>();
        }
        return ownedPart;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = PartUsageImpl.class)
    public void setOwnedPart(List<PartUsage> ownedPart) {
        this.ownedPart = ownedPart;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedPort")
    private List<PortUsage> ownedPort;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "PortUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_ownedPort",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public List<PortUsage> getOwnedPort() {
        if (ownedPort == null) {
            ownedPort = new ArrayList<>();
        }
        return ownedPort;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = PortUsageImpl.class)
    public void setOwnedPort(List<PortUsage> ownedPort) {
        this.ownedPort = ownedPort;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedReference")
    private List<ReferenceUsage> ownedReference;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ReferenceUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_ownedReference",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public List<ReferenceUsage> getOwnedReference() {
        if (ownedReference == null) {
            ownedReference = new ArrayList<>();
        }
        return ownedReference;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = ReferenceUsageImpl.class)
    public void setOwnedReference(List<ReferenceUsage> ownedReference) {
        this.ownedReference = ownedReference;
    }



    // @info.archinnov.achilles.annotations.Column("ownedRelationship")
    private List<Relationship> ownedRelationship;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    @ManyToAny(metaDef = "RelationshipMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_ownedRelationship",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
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
    // @info.archinnov.achilles.annotations.Column("ownedRendering")
    private List<RenderingUsage> ownedRendering;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "RenderingUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_ownedRendering",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public List<RenderingUsage> getOwnedRendering() {
        if (ownedRendering == null) {
            ownedRendering = new ArrayList<>();
        }
        return ownedRendering;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = RenderingUsageImpl.class)
    public void setOwnedRendering(List<RenderingUsage> ownedRendering) {
        this.ownedRendering = ownedRendering;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedRequirement")
    private List<RequirementUsage> ownedRequirement;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "RequirementUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_ownedRequirement",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public List<RequirementUsage> getOwnedRequirement() {
        if (ownedRequirement == null) {
            ownedRequirement = new ArrayList<>();
        }
        return ownedRequirement;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = RequirementUsageImpl.class)
    public void setOwnedRequirement(List<RequirementUsage> ownedRequirement) {
        this.ownedRequirement = ownedRequirement;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedSpecialization")
    private List<Specialization> ownedSpecialization;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "SpecializationMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_ownedSpecialization",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public List<Specialization> getOwnedSpecialization() {
        if (ownedSpecialization == null) {
            ownedSpecialization = new ArrayList<>();
        }
        return ownedSpecialization;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = SpecializationImpl.class)
    public void setOwnedSpecialization(List<Specialization> ownedSpecialization) {
        this.ownedSpecialization = ownedSpecialization;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedState")
    private List<StateUsage> ownedState;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "StateUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_ownedState",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public List<StateUsage> getOwnedState() {
        if (ownedState == null) {
            ownedState = new ArrayList<>();
        }
        return ownedState;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = StateUsageImpl.class)
    public void setOwnedState(List<StateUsage> ownedState) {
        this.ownedState = ownedState;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedSubclassification")
    private Collection<Subclassification> ownedSubclassification;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "SubclassificationMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_ownedSubclassification",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public Collection<Subclassification> getOwnedSubclassification() {
        if (ownedSubclassification == null) {
            ownedSubclassification = new ArrayList<>();
        }
        return ownedSubclassification;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = SubclassificationImpl.class)
    public void setOwnedSubclassification(Collection<Subclassification> ownedSubclassification) {
        this.ownedSubclassification = ownedSubclassification;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedTransition")
    private Collection<TransitionUsage> ownedTransition;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "TransitionUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_ownedTransition",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public Collection<TransitionUsage> getOwnedTransition() {
        if (ownedTransition == null) {
            ownedTransition = new ArrayList<>();
        }
        return ownedTransition;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = TransitionUsageImpl.class)
    public void setOwnedTransition(Collection<TransitionUsage> ownedTransition) {
        this.ownedTransition = ownedTransition;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedUsage")
    private List<Usage> ownedUsage;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "UsageMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_ownedUsage",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public List<Usage> getOwnedUsage() {
        if (ownedUsage == null) {
            ownedUsage = new ArrayList<>();
        }
        return ownedUsage;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = UsageImpl.class)
    public void setOwnedUsage(List<Usage> ownedUsage) {
        this.ownedUsage = ownedUsage;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedUseCase")
    private List<UseCaseUsage> ownedUseCase;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "UseCaseUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_ownedUseCase",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public List<UseCaseUsage> getOwnedUseCase() {
        if (ownedUseCase == null) {
            ownedUseCase = new ArrayList<>();
        }
        return ownedUseCase;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = UseCaseUsageImpl.class)
    public void setOwnedUseCase(List<UseCaseUsage> ownedUseCase) {
        this.ownedUseCase = ownedUseCase;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedVerificationCase")
    private List<VerificationCaseUsage> ownedVerificationCase;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "VerificationCaseUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_ownedVerificationCase",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public List<VerificationCaseUsage> getOwnedVerificationCase() {
        if (ownedVerificationCase == null) {
            ownedVerificationCase = new ArrayList<>();
        }
        return ownedVerificationCase;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = VerificationCaseUsageImpl.class)
    public void setOwnedVerificationCase(List<VerificationCaseUsage> ownedVerificationCase) {
        this.ownedVerificationCase = ownedVerificationCase;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedView")
    private List<ViewUsage> ownedView;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ViewUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_ownedView",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public List<ViewUsage> getOwnedView() {
        if (ownedView == null) {
            ownedView = new ArrayList<>();
        }
        return ownedView;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = ViewUsageImpl.class)
    public void setOwnedView(List<ViewUsage> ownedView) {
        this.ownedView = ownedView;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedViewpoint")
    private List<ViewpointUsage> ownedViewpoint;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ViewpointUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_ownedViewpoint",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public List<ViewpointUsage> getOwnedViewpoint() {
        if (ownedViewpoint == null) {
            ownedViewpoint = new ArrayList<>();
        }
        return ownedViewpoint;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = ViewpointUsageImpl.class)
    public void setOwnedViewpoint(List<ViewpointUsage> ownedViewpoint) {
        this.ownedViewpoint = ownedViewpoint;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("owner")
    private Element owner;

    @JsonGetter
    @JsonSerialize(using = DataSerializer.class)
    // @javax.persistence.Transient
    @Any(metaDef = "ElementMetaDef", metaColumn = @javax.persistence.Column(name = "owner_type"), fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", table = "ActionDefinition")
    public Element getOwner() {
        return owner;
    }

    @JsonSetter
    @JsonDeserialize(using = DataDeserializer.class, as = ElementImpl.class)
    public void setOwner(Element owner) {
        this.owner = owner;
    }



    // @info.archinnov.achilles.annotations.Column("owningMembership")
    private OwningMembership owningMembership;

    @JsonGetter
    @JsonSerialize(using = DataSerializer.class)
    @Any(metaDef = "OwningMembershipMetaDef", metaColumn = @javax.persistence.Column(name = "owningMembership_type"), fetch = FetchType.LAZY)
    @JoinColumn(name = "owningMembership_id", table = "ActionDefinition")
    public OwningMembership getOwningMembership() {
        return owningMembership;
    }

    @JsonSetter
    @JsonDeserialize(using = DataDeserializer.class, as = OwningMembershipImpl.class)
    public void setOwningMembership(OwningMembership owningMembership) {
        this.owningMembership = owningMembership;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("owningNamespace")
    private Namespace owningNamespace;

    @JsonGetter
    @JsonSerialize(using = DataSerializer.class)
    // @javax.persistence.Transient
    @Any(metaDef = "NamespaceMetaDef", metaColumn = @javax.persistence.Column(name = "owningNamespace_type"), fetch = FetchType.LAZY)
    @JoinColumn(name = "owningNamespace_id", table = "ActionDefinition")
    public Namespace getOwningNamespace() {
        return owningNamespace;
    }

    @JsonSetter
    @JsonDeserialize(using = DataDeserializer.class, as = NamespaceImpl.class)
    public void setOwningNamespace(Namespace owningNamespace) {
        this.owningNamespace = owningNamespace;
    }



    // @info.archinnov.achilles.annotations.Column("owningRelationship")
    private Relationship owningRelationship;

    @JsonGetter
    @JsonSerialize(using = DataSerializer.class)
    @Any(metaDef = "RelationshipMetaDef", metaColumn = @javax.persistence.Column(name = "owningRelationship_type"), fetch = FetchType.LAZY)
    @JoinColumn(name = "owningRelationship_id", table = "ActionDefinition")
    public Relationship getOwningRelationship() {
        return owningRelationship;
    }

    @JsonSetter
    @JsonDeserialize(using = DataDeserializer.class, as = RelationshipImpl.class)
    public void setOwningRelationship(Relationship owningRelationship) {
        this.owningRelationship = owningRelationship;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("parameter")
    private List<Feature> parameter;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "FeatureMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_parameter",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public List<Feature> getParameter() {
        if (parameter == null) {
            parameter = new ArrayList<>();
        }
        return parameter;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = FeatureImpl.class)
    public void setParameter(List<Feature> parameter) {
        this.parameter = parameter;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("qualifiedName")
    private String qualifiedName;

    @JsonGetter
    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    // @javax.persistence.Transient
    @javax.persistence.Column(name = "qualifiedName", table = "ActionDefinition")
    public String getQualifiedName() {
        return qualifiedName;
    }

    @JsonSetter
    public void setQualifiedName(String qualifiedName) {
        this.qualifiedName = qualifiedName;
    }



    // @info.archinnov.achilles.annotations.Column("shortName")
    private String shortName;

    @JsonGetter
    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    @javax.persistence.Column(name = "shortName", table = "ActionDefinition")
    public String getShortName() {
        return shortName;
    }

    @JsonSetter
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("step")
    private Collection<Step> step;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "StepMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_step",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public Collection<Step> getStep() {
        if (step == null) {
            step = new ArrayList<>();
        }
        return step;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = StepImpl.class)
    public void setStep(Collection<Step> step) {
        this.step = step;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("textualRepresentation")
    private List<TextualRepresentation> textualRepresentation;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "TextualRepresentationMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_textualRepresentation",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
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



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("usage")
    private List<Usage> usage;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "UsageMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_usage",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public List<Usage> getUsage() {
        if (usage == null) {
            usage = new ArrayList<>();
        }
        return usage;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = UsageImpl.class)
    public void setUsage(List<Usage> usage) {
        this.usage = usage;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("variant")
    private Collection<Usage> variant;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "UsageMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_variant",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public Collection<Usage> getVariant() {
        if (variant == null) {
            variant = new ArrayList<>();
        }
        return variant;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = UsageImpl.class)
    public void setVariant(Collection<Usage> variant) {
        this.variant = variant;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("variantMembership")
    private Collection<VariantMembership> variantMembership;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "VariantMembershipMetaDef", metaColumn = @javax.persistence.Column(name = "attribute_type"), fetch = FetchType.LAZY)
    @JoinTable(name = "ActionDefinition_variantMembership",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    public Collection<VariantMembership> getVariantMembership() {
        if (variantMembership == null) {
            variantMembership = new ArrayList<>();
        }
        return variantMembership;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = VariantMembershipImpl.class)
    public void setVariantMembership(Collection<VariantMembership> variantMembership) {
        this.variantMembership = variantMembership;
    }



}
