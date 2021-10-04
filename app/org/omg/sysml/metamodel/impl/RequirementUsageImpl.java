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
import org.hibernate.annotations.ManyToAny;
import org.omg.sysml.metamodel.Class;
import org.omg.sysml.metamodel.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity(name = "RequirementUsageImpl")
@SecondaryTable(name = "RequirementUsage")
@org.hibernate.annotations.Table(appliesTo = "RequirementUsage", fetch = FetchMode.SELECT, optional = false)
// @info.archinnov.achilles.annotations.Table(table = "RequirementUsage")
@DiscriminatorValue(value = "RequirementUsage")
@JsonTypeName(value = "RequirementUsage")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
public class RequirementUsageImpl extends SysMLTypeImpl implements RequirementUsage {
    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("actorParameter")
    private List<PartUsage> actorParameter;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "PartUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_actorParameter",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<PartUsage> getActorParameter() {
        if (actorParameter == null) {
            actorParameter = new ArrayList<>();
        }
        return actorParameter;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = PartUsageImpl.class)
    public void setActorParameter(List<PartUsage> actorParameter) {
        this.actorParameter = actorParameter;
    }



    // @info.archinnov.achilles.annotations.Column("aliasId")
    private List<String> aliasId;

    @JsonGetter
    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "RequirementUsage_aliasId",
            joinColumns = @JoinColumn(name = "RequirementUsageId"))
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
    // @info.archinnov.achilles.annotations.Column("assumedConstraint")
    private List<ConstraintUsage> assumedConstraint;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ConstraintUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_assumedConstraint",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<ConstraintUsage> getAssumedConstraint() {
        if (assumedConstraint == null) {
            assumedConstraint = new ArrayList<>();
        }
        return assumedConstraint;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = ConstraintUsageImpl.class)
    public void setAssumedConstraint(List<ConstraintUsage> assumedConstraint) {
        this.assumedConstraint = assumedConstraint;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("behavior")
    private List<Behavior> behavior;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "BehaviorMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_behavior",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<Behavior> getBehavior() {
        if (behavior == null) {
            behavior = new ArrayList<>();
        }
        return behavior;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = BehaviorImpl.class)
    public void setBehavior(List<Behavior> behavior) {
        this.behavior = behavior;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("chainingFeature")
    private List<Feature> chainingFeature;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "FeatureMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_chainingFeature",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<Feature> getChainingFeature() {
        if (chainingFeature == null) {
            chainingFeature = new ArrayList<>();
        }
        return chainingFeature;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = FeatureImpl.class)
    public void setChainingFeature(List<Feature> chainingFeature) {
        this.chainingFeature = chainingFeature;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("constraintDefinition")
    private Predicate constraintDefinition;

    @JsonGetter
    @JsonSerialize(using = DataSerializer.class)
    // @javax.persistence.Transient
    @Any(metaDef = "PredicateMetaDef", metaColumn = @javax.persistence.Column(name = "constraintDefinitionType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "constraintDefinitionId", table = "RequirementUsage")
    public Predicate getConstraintDefinition() {
        return constraintDefinition;
    }

    @JsonSetter
    @JsonDeserialize(using = DataDeserializer.class, as = PredicateImpl.class)
    public void setConstraintDefinition(Predicate constraintDefinition) {
        this.constraintDefinition = constraintDefinition;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("definition")
    private List<Classifier> definition;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ClassifierMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_definition",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<Classifier> getDefinition() {
        if (definition == null) {
            definition = new ArrayList<>();
        }
        return definition;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = ClassifierImpl.class)
    public void setDefinition(List<Classifier> definition) {
        this.definition = definition;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("directedFeature")
    private List<Feature> directedFeature;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "FeatureMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_directedFeature",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
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
    @ManyToAny(metaDef = "UsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_directedUsage",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
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



    // @info.archinnov.achilles.annotations.Column("direction")
    // @info.archinnov.achilles.annotations.Enumerated(info.archinnov.achilles.annotations.Enumerated.Encoding.NAME)
    private FeatureDirectionKind direction;

    @JsonGetter
    @javax.persistence.Enumerated(EnumType.STRING)
    @javax.persistence.Column(name = "direction", table = "RequirementUsage")
    public FeatureDirectionKind getDirection() {
        return direction;
    }

    @JsonSetter
    public void setDirection(FeatureDirectionKind direction) {
        this.direction = direction;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("disjointType")
    private Collection<Type> disjointType;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "TypeMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_disjointType",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public Collection<Type> getDisjointType() {
        if (disjointType == null) {
            disjointType = new ArrayList<>();
        }
        return disjointType;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = TypeImpl.class)
    public void setDisjointType(Collection<Type> disjointType) {
        this.disjointType = disjointType;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("documentation")
    private List<Documentation> documentation;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "DocumentationMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_documentation",
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
    // @info.archinnov.achilles.annotations.Column("documentationComment")
    private List<Comment> documentationComment;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "CommentMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_documentationComment",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<Comment> getDocumentationComment() {
        if (documentationComment == null) {
            documentationComment = new ArrayList<>();
        }
        return documentationComment;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = CommentImpl.class)
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
    @javax.persistence.Column(name = "effectiveName", table = "RequirementUsage")
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
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "FeatureMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_endFeature",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
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
    // @info.archinnov.achilles.annotations.Column("endOwningType")
    private Type endOwningType;

    @JsonGetter
    @JsonSerialize(using = DataSerializer.class)
    // @javax.persistence.Transient
    @Any(metaDef = "TypeMetaDef", metaColumn = @javax.persistence.Column(name = "endOwningTypeType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "endOwningTypeId", table = "RequirementUsage")
    public Type getEndOwningType() {
        return endOwningType;
    }

    @JsonSetter
    @JsonDeserialize(using = DataDeserializer.class, as = TypeImpl.class)
    public void setEndOwningType(Type endOwningType) {
        this.endOwningType = endOwningType;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("feature")
    private List<Feature> feature;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "FeatureMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_feature",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
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
    @ManyToAny(metaDef = "FeatureMembershipMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_featureMembership",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
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
    // @info.archinnov.achilles.annotations.Column("featuringType")
    private List<Type> featuringType;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "TypeMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_featuringType",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<Type> getFeaturingType() {
        if (featuringType == null) {
            featuringType = new ArrayList<>();
        }
        return featuringType;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = TypeImpl.class)
    public void setFeaturingType(List<Type> featuringType) {
        this.featuringType = featuringType;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("framedConcern")
    private List<ConcernUsage> framedConcern;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ConcernUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_framedConcern",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<ConcernUsage> getFramedConcern() {
        if (framedConcern == null) {
            framedConcern = new ArrayList<>();
        }
        return framedConcern;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = ConcernUsageImpl.class)
    public void setFramedConcern(List<ConcernUsage> framedConcern) {
        this.framedConcern = framedConcern;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("function")
    private Function function;

    @JsonGetter
    @JsonSerialize(using = DataSerializer.class)
    // @javax.persistence.Transient
    @Any(metaDef = "FunctionMetaDef", metaColumn = @javax.persistence.Column(name = "functionType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "functionId", table = "RequirementUsage")
    public Function getFunction() {
        return function;
    }

    @JsonSetter
    @JsonDeserialize(using = DataDeserializer.class, as = FunctionImpl.class)
    public void setFunction(Function function) {
        this.function = function;
    }



    // @info.archinnov.achilles.annotations.Column("humanId")
    private String humanId;

    @JsonGetter
    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    @javax.persistence.Column(name = "humanId", table = "RequirementUsage")
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
    @javax.persistence.Column(name = "identifier", table = "RequirementUsage")
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
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "MembershipMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_importedMembership",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
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
    // @info.archinnov.achilles.annotations.Column("individualDefinition")
    private OccurrenceDefinition individualDefinition;

    @JsonGetter
    @JsonSerialize(using = DataSerializer.class)
    // @javax.persistence.Transient
    @Any(metaDef = "OccurrenceDefinitionMetaDef", metaColumn = @javax.persistence.Column(name = "individualDefinitionType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "individualDefinitionId", table = "RequirementUsage")
    public OccurrenceDefinition getIndividualDefinition() {
        return individualDefinition;
    }

    @JsonSetter
    @JsonDeserialize(using = DataDeserializer.class, as = OccurrenceDefinitionImpl.class)
    public void setIndividualDefinition(OccurrenceDefinition individualDefinition) {
        this.individualDefinition = individualDefinition;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("inheritedFeature")
    private List<Feature> inheritedFeature;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "FeatureMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_inheritedFeature",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
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
    @ManyToAny(metaDef = "MembershipMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_inheritedMembership",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
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
    @ManyToAny(metaDef = "FeatureMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_input",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
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
    @javax.persistence.Column(name = "isAbstract", table = "RequirementUsage")
    public Boolean getIsAbstract() {
        return isAbstract;
    }

    @JsonSetter
    public void setIsAbstract(Boolean isAbstract) {
        this.isAbstract = isAbstract;
    }



    // @info.archinnov.achilles.annotations.Column("isComposite")
    private Boolean isComposite;

    @JsonGetter
    @javax.persistence.Column(name = "isComposite", table = "RequirementUsage")
    public Boolean getIsComposite() {
        return isComposite;
    }

    @JsonSetter
    public void setIsComposite(Boolean isComposite) {
        this.isComposite = isComposite;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("isConjugated")
    private Boolean isConjugated;

    @JsonGetter
    // @javax.persistence.Transient
    @javax.persistence.Column(name = "isConjugated", table = "RequirementUsage")
    public Boolean getIsConjugated() {
        return isConjugated;
    }

    @JsonSetter
    public void setIsConjugated(Boolean isConjugated) {
        this.isConjugated = isConjugated;
    }



    // @info.archinnov.achilles.annotations.Column("isDerived")
    private Boolean isDerived;

    @JsonGetter
    @javax.persistence.Column(name = "isDerived", table = "RequirementUsage")
    public Boolean getIsDerived() {
        return isDerived;
    }

    @JsonSetter
    public void setIsDerived(Boolean isDerived) {
        this.isDerived = isDerived;
    }



    // @info.archinnov.achilles.annotations.Column("isEnd")
    private Boolean isEnd;

    @JsonGetter
    @javax.persistence.Column(name = "isEnd", table = "RequirementUsage")
    public Boolean getIsEnd() {
        return isEnd;
    }

    @JsonSetter
    public void setIsEnd(Boolean isEnd) {
        this.isEnd = isEnd;
    }



    // @info.archinnov.achilles.annotations.Column("isIndividual")
    private Boolean isIndividual;

    @JsonGetter
    @javax.persistence.Column(name = "isIndividual", table = "RequirementUsage")
    public Boolean getIsIndividual() {
        return isIndividual;
    }

    @JsonSetter
    public void setIsIndividual(Boolean isIndividual) {
        this.isIndividual = isIndividual;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("isModelLevelEvaluable")
    private Boolean isModelLevelEvaluable;

    @JsonGetter
    // @javax.persistence.Transient
    @javax.persistence.Column(name = "isModelLevelEvaluable", table = "RequirementUsage")
    public Boolean getIsModelLevelEvaluable() {
        return isModelLevelEvaluable;
    }

    @JsonSetter
    public void setIsModelLevelEvaluable(Boolean isModelLevelEvaluable) {
        this.isModelLevelEvaluable = isModelLevelEvaluable;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("isNonunique")
    private Boolean isNonunique;

    @JsonGetter
    // @javax.persistence.Transient
    @javax.persistence.Column(name = "isNonunique", table = "RequirementUsage")
    public Boolean getIsNonunique() {
        return isNonunique;
    }

    @JsonSetter
    public void setIsNonunique(Boolean isNonunique) {
        this.isNonunique = isNonunique;
    }



    // @info.archinnov.achilles.annotations.Column("isOrdered")
    private Boolean isOrdered;

    @JsonGetter
    @javax.persistence.Column(name = "isOrdered", table = "RequirementUsage")
    public Boolean getIsOrdered() {
        return isOrdered;
    }

    @JsonSetter
    public void setIsOrdered(Boolean isOrdered) {
        this.isOrdered = isOrdered;
    }



    // @info.archinnov.achilles.annotations.Column("isPortion")
    private Boolean isPortion;

    @JsonGetter
    @javax.persistence.Column(name = "isPortion", table = "RequirementUsage")
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
    @javax.persistence.Column(name = "isReadOnly", table = "RequirementUsage")
    public Boolean getIsReadOnly() {
        return isReadOnly;
    }

    @JsonSetter
    public void setIsReadOnly(Boolean isReadOnly) {
        this.isReadOnly = isReadOnly;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("isReference")
    private Boolean isReference;

    @JsonGetter
    // @javax.persistence.Transient
    @javax.persistence.Column(name = "isReference", table = "RequirementUsage")
    public Boolean getIsReference() {
        return isReference;
    }

    @JsonSetter
    public void setIsReference(Boolean isReference) {
        this.isReference = isReference;
    }



    // @info.archinnov.achilles.annotations.Column("isSufficient")
    private Boolean isSufficient;

    @JsonGetter
    @javax.persistence.Column(name = "isSufficient", table = "RequirementUsage")
    public Boolean getIsSufficient() {
        return isSufficient;
    }

    @JsonSetter
    public void setIsSufficient(Boolean isSufficient) {
        this.isSufficient = isSufficient;
    }



    // @info.archinnov.achilles.annotations.Column("isUnique")
    private Boolean isUnique;

    @JsonGetter
    @javax.persistence.Column(name = "isUnique", table = "RequirementUsage")
    public Boolean getIsUnique() {
        return isUnique;
    }

    @JsonSetter
    public void setIsUnique(Boolean isUnique) {
        this.isUnique = isUnique;
    }



    // @info.archinnov.achilles.annotations.Column("isVariation")
    private Boolean isVariation;

    @JsonGetter
    @javax.persistence.Column(name = "isVariation", table = "RequirementUsage")
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
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ElementMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_member",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
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
    @ManyToAny(metaDef = "MembershipMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_membership",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
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
    @Any(metaDef = "MultiplicityMetaDef", metaColumn = @javax.persistence.Column(name = "multiplicityType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "multiplicityId", table = "RequirementUsage")
    public Multiplicity getMultiplicity() {
        return multiplicity;
    }

    @JsonSetter
    @JsonDeserialize(using = DataDeserializer.class, as = MultiplicityImpl.class)
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
    @javax.persistence.Column(name = "name", table = "RequirementUsage")
    public String getName() {
        return name;
    }

    @JsonSetter
    public void setName(String name) {
        this.name = name;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("nestedAction")
    private List<ActionUsage> nestedAction;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ActionUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_nestedAction",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<ActionUsage> getNestedAction() {
        if (nestedAction == null) {
            nestedAction = new ArrayList<>();
        }
        return nestedAction;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = ActionUsageImpl.class)
    public void setNestedAction(List<ActionUsage> nestedAction) {
        this.nestedAction = nestedAction;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("nestedAllocation")
    private List<AllocationUsage> nestedAllocation;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "AllocationUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_nestedAllocation",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<AllocationUsage> getNestedAllocation() {
        if (nestedAllocation == null) {
            nestedAllocation = new ArrayList<>();
        }
        return nestedAllocation;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = AllocationUsageImpl.class)
    public void setNestedAllocation(List<AllocationUsage> nestedAllocation) {
        this.nestedAllocation = nestedAllocation;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("nestedAnalysisCase")
    private List<AnalysisCaseUsage> nestedAnalysisCase;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "AnalysisCaseUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_nestedAnalysisCase",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<AnalysisCaseUsage> getNestedAnalysisCase() {
        if (nestedAnalysisCase == null) {
            nestedAnalysisCase = new ArrayList<>();
        }
        return nestedAnalysisCase;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = AnalysisCaseUsageImpl.class)
    public void setNestedAnalysisCase(List<AnalysisCaseUsage> nestedAnalysisCase) {
        this.nestedAnalysisCase = nestedAnalysisCase;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("nestedAttribute")
    private List<AttributeUsage> nestedAttribute;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "AttributeUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_nestedAttribute",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<AttributeUsage> getNestedAttribute() {
        if (nestedAttribute == null) {
            nestedAttribute = new ArrayList<>();
        }
        return nestedAttribute;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = AttributeUsageImpl.class)
    public void setNestedAttribute(List<AttributeUsage> nestedAttribute) {
        this.nestedAttribute = nestedAttribute;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("nestedCalculation")
    private List<CalculationUsage> nestedCalculation;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "CalculationUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_nestedCalculation",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<CalculationUsage> getNestedCalculation() {
        if (nestedCalculation == null) {
            nestedCalculation = new ArrayList<>();
        }
        return nestedCalculation;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = CalculationUsageImpl.class)
    public void setNestedCalculation(List<CalculationUsage> nestedCalculation) {
        this.nestedCalculation = nestedCalculation;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("nestedCase")
    private List<CaseUsage> nestedCase;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "CaseUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_nestedCase",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<CaseUsage> getNestedCase() {
        if (nestedCase == null) {
            nestedCase = new ArrayList<>();
        }
        return nestedCase;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = CaseUsageImpl.class)
    public void setNestedCase(List<CaseUsage> nestedCase) {
        this.nestedCase = nestedCase;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("nestedConcern")
    private Collection<ConcernUsage> nestedConcern;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ConcernUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_nestedConcern",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public Collection<ConcernUsage> getNestedConcern() {
        if (nestedConcern == null) {
            nestedConcern = new ArrayList<>();
        }
        return nestedConcern;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = ConcernUsageImpl.class)
    public void setNestedConcern(Collection<ConcernUsage> nestedConcern) {
        this.nestedConcern = nestedConcern;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("nestedConnection")
    private List<ConnectorAsUsage> nestedConnection;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ConnectorAsUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_nestedConnection",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<ConnectorAsUsage> getNestedConnection() {
        if (nestedConnection == null) {
            nestedConnection = new ArrayList<>();
        }
        return nestedConnection;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = ConnectorAsUsageImpl.class)
    public void setNestedConnection(List<ConnectorAsUsage> nestedConnection) {
        this.nestedConnection = nestedConnection;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("nestedConstraint")
    private List<ConstraintUsage> nestedConstraint;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ConstraintUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_nestedConstraint",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<ConstraintUsage> getNestedConstraint() {
        if (nestedConstraint == null) {
            nestedConstraint = new ArrayList<>();
        }
        return nestedConstraint;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = ConstraintUsageImpl.class)
    public void setNestedConstraint(List<ConstraintUsage> nestedConstraint) {
        this.nestedConstraint = nestedConstraint;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("nestedEnumeration")
    private List<EnumerationUsage> nestedEnumeration;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "EnumerationUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_nestedEnumeration",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<EnumerationUsage> getNestedEnumeration() {
        if (nestedEnumeration == null) {
            nestedEnumeration = new ArrayList<>();
        }
        return nestedEnumeration;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = EnumerationUsageImpl.class)
    public void setNestedEnumeration(List<EnumerationUsage> nestedEnumeration) {
        this.nestedEnumeration = nestedEnumeration;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("nestedFlow")
    private Collection<FlowConnectionUsage> nestedFlow;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "FlowConnectionUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_nestedFlow",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public Collection<FlowConnectionUsage> getNestedFlow() {
        if (nestedFlow == null) {
            nestedFlow = new ArrayList<>();
        }
        return nestedFlow;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = FlowConnectionUsageImpl.class)
    public void setNestedFlow(Collection<FlowConnectionUsage> nestedFlow) {
        this.nestedFlow = nestedFlow;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("nestedInterface")
    private List<InterfaceUsage> nestedInterface;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "InterfaceUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_nestedInterface",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<InterfaceUsage> getNestedInterface() {
        if (nestedInterface == null) {
            nestedInterface = new ArrayList<>();
        }
        return nestedInterface;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = InterfaceUsageImpl.class)
    public void setNestedInterface(List<InterfaceUsage> nestedInterface) {
        this.nestedInterface = nestedInterface;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("nestedItem")
    private List<ItemUsage> nestedItem;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ItemUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_nestedItem",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<ItemUsage> getNestedItem() {
        if (nestedItem == null) {
            nestedItem = new ArrayList<>();
        }
        return nestedItem;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = ItemUsageImpl.class)
    public void setNestedItem(List<ItemUsage> nestedItem) {
        this.nestedItem = nestedItem;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("nestedOccurrence")
    private List<OccurrenceUsage> nestedOccurrence;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "OccurrenceUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_nestedOccurrence",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<OccurrenceUsage> getNestedOccurrence() {
        if (nestedOccurrence == null) {
            nestedOccurrence = new ArrayList<>();
        }
        return nestedOccurrence;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = OccurrenceUsageImpl.class)
    public void setNestedOccurrence(List<OccurrenceUsage> nestedOccurrence) {
        this.nestedOccurrence = nestedOccurrence;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("nestedPart")
    private List<PartUsage> nestedPart;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "PartUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_nestedPart",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<PartUsage> getNestedPart() {
        if (nestedPart == null) {
            nestedPart = new ArrayList<>();
        }
        return nestedPart;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = PartUsageImpl.class)
    public void setNestedPart(List<PartUsage> nestedPart) {
        this.nestedPart = nestedPart;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("nestedPort")
    private List<PortUsage> nestedPort;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "PortUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_nestedPort",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<PortUsage> getNestedPort() {
        if (nestedPort == null) {
            nestedPort = new ArrayList<>();
        }
        return nestedPort;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = PortUsageImpl.class)
    public void setNestedPort(List<PortUsage> nestedPort) {
        this.nestedPort = nestedPort;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("nestedReference")
    private List<ReferenceUsage> nestedReference;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ReferenceUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_nestedReference",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<ReferenceUsage> getNestedReference() {
        if (nestedReference == null) {
            nestedReference = new ArrayList<>();
        }
        return nestedReference;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = ReferenceUsageImpl.class)
    public void setNestedReference(List<ReferenceUsage> nestedReference) {
        this.nestedReference = nestedReference;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("nestedRendering")
    private List<RenderingUsage> nestedRendering;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "RenderingUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_nestedRendering",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<RenderingUsage> getNestedRendering() {
        if (nestedRendering == null) {
            nestedRendering = new ArrayList<>();
        }
        return nestedRendering;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = RenderingUsageImpl.class)
    public void setNestedRendering(List<RenderingUsage> nestedRendering) {
        this.nestedRendering = nestedRendering;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("nestedRequirement")
    private List<RequirementUsage> nestedRequirement;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "RequirementUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_nestedRequirement",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<RequirementUsage> getNestedRequirement() {
        if (nestedRequirement == null) {
            nestedRequirement = new ArrayList<>();
        }
        return nestedRequirement;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = RequirementUsageImpl.class)
    public void setNestedRequirement(List<RequirementUsage> nestedRequirement) {
        this.nestedRequirement = nestedRequirement;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("nestedState")
    private List<StateUsage> nestedState;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "StateUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_nestedState",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<StateUsage> getNestedState() {
        if (nestedState == null) {
            nestedState = new ArrayList<>();
        }
        return nestedState;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = StateUsageImpl.class)
    public void setNestedState(List<StateUsage> nestedState) {
        this.nestedState = nestedState;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("nestedTransition")
    private Collection<TransitionUsage> nestedTransition;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "TransitionUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_nestedTransition",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public Collection<TransitionUsage> getNestedTransition() {
        if (nestedTransition == null) {
            nestedTransition = new ArrayList<>();
        }
        return nestedTransition;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = TransitionUsageImpl.class)
    public void setNestedTransition(Collection<TransitionUsage> nestedTransition) {
        this.nestedTransition = nestedTransition;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("nestedUsage")
    private List<Usage> nestedUsage;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "UsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_nestedUsage",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<Usage> getNestedUsage() {
        if (nestedUsage == null) {
            nestedUsage = new ArrayList<>();
        }
        return nestedUsage;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = UsageImpl.class)
    public void setNestedUsage(List<Usage> nestedUsage) {
        this.nestedUsage = nestedUsage;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("nestedUseCase")
    private List<UseCaseUsage> nestedUseCase;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "UseCaseUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_nestedUseCase",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<UseCaseUsage> getNestedUseCase() {
        if (nestedUseCase == null) {
            nestedUseCase = new ArrayList<>();
        }
        return nestedUseCase;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = UseCaseUsageImpl.class)
    public void setNestedUseCase(List<UseCaseUsage> nestedUseCase) {
        this.nestedUseCase = nestedUseCase;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("nestedVerificationCase")
    private List<VerificationCaseUsage> nestedVerificationCase;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "VerificationCaseUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_nestedVerificationCase",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<VerificationCaseUsage> getNestedVerificationCase() {
        if (nestedVerificationCase == null) {
            nestedVerificationCase = new ArrayList<>();
        }
        return nestedVerificationCase;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = VerificationCaseUsageImpl.class)
    public void setNestedVerificationCase(List<VerificationCaseUsage> nestedVerificationCase) {
        this.nestedVerificationCase = nestedVerificationCase;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("nestedView")
    private List<ViewUsage> nestedView;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ViewUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_nestedView",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<ViewUsage> getNestedView() {
        if (nestedView == null) {
            nestedView = new ArrayList<>();
        }
        return nestedView;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = ViewUsageImpl.class)
    public void setNestedView(List<ViewUsage> nestedView) {
        this.nestedView = nestedView;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("nestedViewpoint")
    private List<ViewpointUsage> nestedViewpoint;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ViewpointUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_nestedViewpoint",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<ViewpointUsage> getNestedViewpoint() {
        if (nestedViewpoint == null) {
            nestedViewpoint = new ArrayList<>();
        }
        return nestedViewpoint;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = ViewpointUsageImpl.class)
    public void setNestedViewpoint(List<ViewpointUsage> nestedViewpoint) {
        this.nestedViewpoint = nestedViewpoint;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("occurrenceDefinition")
    private List<Class> occurrenceDefinition;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ClassMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_occurrenceDefinition",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<Class> getOccurrenceDefinition() {
        if (occurrenceDefinition == null) {
            occurrenceDefinition = new ArrayList<>();
        }
        return occurrenceDefinition;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = ClassImpl.class)
    public void setOccurrenceDefinition(List<Class> occurrenceDefinition) {
        this.occurrenceDefinition = occurrenceDefinition;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("output")
    private List<Feature> output;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "FeatureMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_output",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
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
    // @info.archinnov.achilles.annotations.Column("ownedAnnotation")
    private List<Annotation> ownedAnnotation;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "AnnotationMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_ownedAnnotation",
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
    // @info.archinnov.achilles.annotations.Column("ownedConjugator")
    private Conjugation ownedConjugator;

    @JsonGetter
    @JsonSerialize(using = DataSerializer.class)
    // @javax.persistence.Transient
    @Any(metaDef = "ConjugationMetaDef", metaColumn = @javax.persistence.Column(name = "ownedConjugatorType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "ownedConjugatorId", table = "RequirementUsage")
    public Conjugation getOwnedConjugator() {
        return ownedConjugator;
    }

    @JsonSetter
    @JsonDeserialize(using = DataDeserializer.class, as = ConjugationImpl.class)
    public void setOwnedConjugator(Conjugation ownedConjugator) {
        this.ownedConjugator = ownedConjugator;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedDisjoining")
    private Collection<Disjoining> ownedDisjoining;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "DisjoiningMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_ownedDisjoining",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
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
    @ManyToAny(metaDef = "ElementMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_ownedElement",
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



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedEndFeature")
    private List<Feature> ownedEndFeature;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "FeatureMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_ownedEndFeature",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
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
    // @info.archinnov.achilles.annotations.Column("ownedFeature")
    private List<Feature> ownedFeature;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "FeatureMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_ownedFeature",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
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
    // @info.archinnov.achilles.annotations.Column("ownedFeatureChaining")
    private List<FeatureChaining> ownedFeatureChaining;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "FeatureChainingMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_ownedFeatureChaining",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<FeatureChaining> getOwnedFeatureChaining() {
        if (ownedFeatureChaining == null) {
            ownedFeatureChaining = new ArrayList<>();
        }
        return ownedFeatureChaining;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = FeatureChainingImpl.class)
    public void setOwnedFeatureChaining(List<FeatureChaining> ownedFeatureChaining) {
        this.ownedFeatureChaining = ownedFeatureChaining;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedFeatureMembership")
    private List<FeatureMembership> ownedFeatureMembership;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "FeatureMembershipMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_ownedFeatureMembership",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
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
    // @info.archinnov.achilles.annotations.Column("ownedImport")
    private List<Import> ownedImport;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ImportMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_ownedImport",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
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
    // @info.archinnov.achilles.annotations.Column("ownedMember")
    private List<Element> ownedMember;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ElementMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_ownedMember",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
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
    @ManyToAny(metaDef = "MembershipMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_ownedMembership",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
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
    // @info.archinnov.achilles.annotations.Column("ownedRedefinition")
    private Collection<Redefinition> ownedRedefinition;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "RedefinitionMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_ownedRedefinition",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public Collection<Redefinition> getOwnedRedefinition() {
        if (ownedRedefinition == null) {
            ownedRedefinition = new ArrayList<>();
        }
        return ownedRedefinition;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = RedefinitionImpl.class)
    public void setOwnedRedefinition(Collection<Redefinition> ownedRedefinition) {
        this.ownedRedefinition = ownedRedefinition;
    }



    // @info.archinnov.achilles.annotations.Column("ownedRelationship")
    private List<Relationship> ownedRelationship;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    @ManyToAny(metaDef = "RelationshipMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_ownedRelationship",
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
    // @info.archinnov.achilles.annotations.Column("ownedSpecialization")
    private List<Specialization> ownedSpecialization;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "SpecializationMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_ownedSpecialization",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
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
    // @info.archinnov.achilles.annotations.Column("ownedSubsetting")
    private Collection<Subsetting> ownedSubsetting;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "SubsettingMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_ownedSubsetting",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public Collection<Subsetting> getOwnedSubsetting() {
        if (ownedSubsetting == null) {
            ownedSubsetting = new ArrayList<>();
        }
        return ownedSubsetting;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = SubsettingImpl.class)
    public void setOwnedSubsetting(Collection<Subsetting> ownedSubsetting) {
        this.ownedSubsetting = ownedSubsetting;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedTextualRepresentation")
    private Collection<TextualRepresentation> ownedTextualRepresentation;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "TextualRepresentationMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_ownedTextualRepresentation",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public Collection<TextualRepresentation> getOwnedTextualRepresentation() {
        if (ownedTextualRepresentation == null) {
            ownedTextualRepresentation = new ArrayList<>();
        }
        return ownedTextualRepresentation;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = TextualRepresentationImpl.class)
    public void setOwnedTextualRepresentation(Collection<TextualRepresentation> ownedTextualRepresentation) {
        this.ownedTextualRepresentation = ownedTextualRepresentation;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedTypeFeaturing")
    private List<TypeFeaturing> ownedTypeFeaturing;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "TypeFeaturingMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_ownedTypeFeaturing",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<TypeFeaturing> getOwnedTypeFeaturing() {
        if (ownedTypeFeaturing == null) {
            ownedTypeFeaturing = new ArrayList<>();
        }
        return ownedTypeFeaturing;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = TypeFeaturingImpl.class)
    public void setOwnedTypeFeaturing(List<TypeFeaturing> ownedTypeFeaturing) {
        this.ownedTypeFeaturing = ownedTypeFeaturing;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedTyping")
    private List<FeatureTyping> ownedTyping;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "FeatureTypingMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_ownedTyping",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<FeatureTyping> getOwnedTyping() {
        if (ownedTyping == null) {
            ownedTyping = new ArrayList<>();
        }
        return ownedTyping;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = FeatureTypingImpl.class)
    public void setOwnedTyping(List<FeatureTyping> ownedTyping) {
        this.ownedTyping = ownedTyping;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("owner")
    private Element owner;

    @JsonGetter
    @JsonSerialize(using = DataSerializer.class)
    // @javax.persistence.Transient
    @Any(metaDef = "ElementMetaDef", metaColumn = @javax.persistence.Column(name = "ownerType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "ownerId", table = "RequirementUsage")
    public Element getOwner() {
        return owner;
    }

    @JsonSetter
    @JsonDeserialize(using = DataDeserializer.class, as = ElementImpl.class)
    public void setOwner(Element owner) {
        this.owner = owner;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("owningDefinition")
    private Definition owningDefinition;

    @JsonGetter
    @JsonSerialize(using = DataSerializer.class)
    // @javax.persistence.Transient
    @Any(metaDef = "DefinitionMetaDef", metaColumn = @javax.persistence.Column(name = "owningDefinitionType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "owningDefinitionId", table = "RequirementUsage")
    public Definition getOwningDefinition() {
        return owningDefinition;
    }

    @JsonSetter
    @JsonDeserialize(using = DataDeserializer.class, as = DefinitionImpl.class)
    public void setOwningDefinition(Definition owningDefinition) {
        this.owningDefinition = owningDefinition;
    }



    // @info.archinnov.achilles.annotations.Column("owningFeatureMembership")
    private FeatureMembership owningFeatureMembership;

    @JsonGetter
    @JsonSerialize(using = DataSerializer.class)
    @Any(metaDef = "FeatureMembershipMetaDef", metaColumn = @javax.persistence.Column(name = "owningFeatureMembershipType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "owningFeatureMembershipId", table = "RequirementUsage")
    public FeatureMembership getOwningFeatureMembership() {
        return owningFeatureMembership;
    }

    @JsonSetter
    @JsonDeserialize(using = DataDeserializer.class, as = FeatureMembershipImpl.class)
    public void setOwningFeatureMembership(FeatureMembership owningFeatureMembership) {
        this.owningFeatureMembership = owningFeatureMembership;
    }



    // @info.archinnov.achilles.annotations.Column("owningMembership")
    private Membership owningMembership;

    @JsonGetter
    @JsonSerialize(using = DataSerializer.class)
    @Any(metaDef = "MembershipMetaDef", metaColumn = @javax.persistence.Column(name = "owningMembershipType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "owningMembershipId", table = "RequirementUsage")
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
    @JoinColumn(name = "owningNamespaceId", table = "RequirementUsage")
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
    @Any(metaDef = "RelationshipMetaDef", metaColumn = @javax.persistence.Column(name = "owningRelationshipType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "owningRelationshipId", table = "RequirementUsage")
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
    @JoinColumn(name = "owningTypeId", table = "RequirementUsage")
    public Type getOwningType() {
        return owningType;
    }

    @JsonSetter
    @JsonDeserialize(using = DataDeserializer.class, as = TypeImpl.class)
    public void setOwningType(Type owningType) {
        this.owningType = owningType;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("owningUsage")
    private Usage owningUsage;

    @JsonGetter
    @JsonSerialize(using = DataSerializer.class)
    // @javax.persistence.Transient
    @Any(metaDef = "UsageMetaDef", metaColumn = @javax.persistence.Column(name = "owningUsageType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "owningUsageId", table = "RequirementUsage")
    public Usage getOwningUsage() {
        return owningUsage;
    }

    @JsonSetter
    @JsonDeserialize(using = DataDeserializer.class, as = UsageImpl.class)
    public void setOwningUsage(Usage owningUsage) {
        this.owningUsage = owningUsage;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("parameter")
    private List<Feature> parameter;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "FeatureMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_parameter",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
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



    // @info.archinnov.achilles.annotations.Column("portionKind")
    // @info.archinnov.achilles.annotations.Enumerated(info.archinnov.achilles.annotations.Enumerated.Encoding.NAME)
    private PortionKind portionKind;

    @JsonGetter
    @javax.persistence.Enumerated(EnumType.STRING)
    @javax.persistence.Column(name = "portionKind", table = "RequirementUsage")
    public PortionKind getPortionKind() {
        return portionKind;
    }

    @JsonSetter
    public void setPortionKind(PortionKind portionKind) {
        this.portionKind = portionKind;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("portioningFeature")
    private PortioningFeature portioningFeature;

    @JsonGetter
    @JsonSerialize(using = DataSerializer.class)
    // @javax.persistence.Transient
    @Any(metaDef = "PortioningFeatureMetaDef", metaColumn = @javax.persistence.Column(name = "portioningFeatureType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "portioningFeatureId", table = "RequirementUsage")
    public PortioningFeature getPortioningFeature() {
        return portioningFeature;
    }

    @JsonSetter
    @JsonDeserialize(using = DataDeserializer.class, as = PortioningFeatureImpl.class)
    public void setPortioningFeature(PortioningFeature portioningFeature) {
        this.portioningFeature = portioningFeature;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("predicate")
    private Predicate predicate;

    @JsonGetter
    @JsonSerialize(using = DataSerializer.class)
    // @javax.persistence.Transient
    @Any(metaDef = "PredicateMetaDef", metaColumn = @javax.persistence.Column(name = "predicateType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "predicateId", table = "RequirementUsage")
    public Predicate getPredicate() {
        return predicate;
    }

    @JsonSetter
    @JsonDeserialize(using = DataDeserializer.class, as = PredicateImpl.class)
    public void setPredicate(Predicate predicate) {
        this.predicate = predicate;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("qualifiedName")
    private String qualifiedName;

    @JsonGetter
    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    // @javax.persistence.Transient
    @javax.persistence.Column(name = "qualifiedName", table = "RequirementUsage")
    public String getQualifiedName() {
        return qualifiedName;
    }

    @JsonSetter
    public void setQualifiedName(String qualifiedName) {
        this.qualifiedName = qualifiedName;
    }



    // @info.archinnov.achilles.annotations.Column("reqId")
    private String reqId;

    @JsonGetter
    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    @javax.persistence.Column(name = "reqId", table = "RequirementUsage")
    public String getReqId() {
        return reqId;
    }

    @JsonSetter
    public void setReqId(String reqId) {
        this.reqId = reqId;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("requiredConstraint")
    private List<ConstraintUsage> requiredConstraint;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ConstraintUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_requiredConstraint",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<ConstraintUsage> getRequiredConstraint() {
        if (requiredConstraint == null) {
            requiredConstraint = new ArrayList<>();
        }
        return requiredConstraint;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = ConstraintUsageImpl.class)
    public void setRequiredConstraint(List<ConstraintUsage> requiredConstraint) {
        this.requiredConstraint = requiredConstraint;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("requirementDefinition")
    private RequirementDefinition requirementDefinition;

    @JsonGetter
    @JsonSerialize(using = DataSerializer.class)
    // @javax.persistence.Transient
    @Any(metaDef = "RequirementDefinitionMetaDef", metaColumn = @javax.persistence.Column(name = "requirementDefinitionType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "requirementDefinitionId", table = "RequirementUsage")
    public RequirementDefinition getRequirementDefinition() {
        return requirementDefinition;
    }

    @JsonSetter
    @JsonDeserialize(using = DataDeserializer.class, as = RequirementDefinitionImpl.class)
    public void setRequirementDefinition(RequirementDefinition requirementDefinition) {
        this.requirementDefinition = requirementDefinition;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("result")
    private Feature result;

    @JsonGetter
    @JsonSerialize(using = DataSerializer.class)
    // @javax.persistence.Transient
    @Any(metaDef = "FeatureMetaDef", metaColumn = @javax.persistence.Column(name = "resultType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "resultId", table = "RequirementUsage")
    public Feature getResult() {
        return result;
    }

    @JsonSetter
    @JsonDeserialize(using = DataDeserializer.class, as = FeatureImpl.class)
    public void setResult(Feature result) {
        this.result = result;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("stakeholderParameter")
    private List<PartUsage> stakeholderParameter;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "PartUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_stakeholderParameter",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<PartUsage> getStakeholderParameter() {
        if (stakeholderParameter == null) {
            stakeholderParameter = new ArrayList<>();
        }
        return stakeholderParameter;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = PartUsageImpl.class)
    public void setStakeholderParameter(List<PartUsage> stakeholderParameter) {
        this.stakeholderParameter = stakeholderParameter;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("subjectParameter")
    private Usage subjectParameter;

    @JsonGetter
    @JsonSerialize(using = DataSerializer.class)
    // @javax.persistence.Transient
    @Any(metaDef = "UsageMetaDef", metaColumn = @javax.persistence.Column(name = "subjectParameterType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "subjectParameterId", table = "RequirementUsage")
    public Usage getSubjectParameter() {
        return subjectParameter;
    }

    @JsonSetter
    @JsonDeserialize(using = DataDeserializer.class, as = UsageImpl.class)
    public void setSubjectParameter(Usage subjectParameter) {
        this.subjectParameter = subjectParameter;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("text")
    private Collection<String> text;

    @JsonGetter
    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    // @javax.persistence.Transient
    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "RequirementUsage_text",
            joinColumns = @JoinColumn(name = "RequirementUsageId"))
    public Collection<String> getText() {
        if (text == null) {
            text = new ArrayList<>();
        }
        return text;
    }

    @JsonSetter
    public void setText(Collection<String> text) {
        this.text = text;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("type")
    private List<Type> type;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "TypeMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_type",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<Type> getType() {
        if (type == null) {
            type = new ArrayList<>();
        }
        return type;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = DataDeserializer.class, contentAs = TypeImpl.class)
    public void setType(List<Type> type) {
        this.type = type;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("usage")
    private List<Usage> usage;

    @JsonGetter
    @JsonSerialize(contentUsing = DataSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "UsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_usage",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
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
    @ManyToAny(metaDef = "UsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_variant",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
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
    @ManyToAny(metaDef = "VariantMembershipMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "RequirementUsage_variantMembership",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
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
