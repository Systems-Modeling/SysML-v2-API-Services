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

@Entity(name = "CaseDefinitionImpl")
@SecondaryTable(name = "CaseDefinition")
@org.hibernate.annotations.Table(appliesTo = "CaseDefinition", fetch = FetchMode.SELECT, optional = false)
// @info.archinnov.achilles.annotations.Table(table = "CaseDefinition")
@DiscriminatorValue(value = "CaseDefinition")
@JsonTypeName(value = "CaseDefinition")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
public class CaseDefinitionImpl extends MofObjectImpl implements CaseDefinition {
    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("action")
    private Collection<ActionUsage> action;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ActionUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "CaseDefinition_action",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public Collection<ActionUsage> getAction() {
        if (action == null) {
            action = new ArrayList<>();
        }
        return action;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ActionUsageImpl.class)
    public void setAction(Collection<ActionUsage> action) {
        this.action = action;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("calculationUsage")
    private Collection<CalculationUsage> calculationUsage;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "CalculationUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "CaseDefinition_calculationUsage",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public Collection<CalculationUsage> getCalculationUsage() {
        if (calculationUsage == null) {
            calculationUsage = new ArrayList<>();
        }
        return calculationUsage;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = CalculationUsageImpl.class)
    public void setCalculationUsage(Collection<CalculationUsage> calculationUsage) {
        this.calculationUsage = calculationUsage;
    }



    // @info.archinnov.achilles.annotations.Column("conjugator")
    private Conjugation conjugator;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "ConjugationMetaDef", metaColumn = @javax.persistence.Column(name = "conjugatorType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "conjugatorId", table = "CaseDefinition")
    public Conjugation getConjugator() {
        return conjugator;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = ConjugationImpl.class)
    public void setConjugator(Conjugation conjugator) {
        this.conjugator = conjugator;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("endFeature")
    private Collection<Feature> endFeature;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "FeatureMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "CaseDefinition_endFeature",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public Collection<Feature> getEndFeature() {
        if (endFeature == null) {
            endFeature = new ArrayList<>();
        }
        return endFeature;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = FeatureImpl.class)
    public void setEndFeature(Collection<Feature> endFeature) {
        this.endFeature = endFeature;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("expression")
    private Collection<Expression> expression;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ExpressionMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "CaseDefinition_expression",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public Collection<Expression> getExpression() {
        if (expression == null) {
            expression = new ArrayList<>();
        }
        return expression;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ExpressionImpl.class)
    public void setExpression(Collection<Expression> expression) {
        this.expression = expression;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("feature")
    private Collection<Feature> feature;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "FeatureMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "CaseDefinition_feature",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public Collection<Feature> getFeature() {
        if (feature == null) {
            feature = new ArrayList<>();
        }
        return feature;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = FeatureImpl.class)
    public void setFeature(Collection<Feature> feature) {
        this.feature = feature;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("featureMembership")
    private List<FeatureMembership> featureMembership;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "FeatureMembershipMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "CaseDefinition_featureMembership",
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



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("flow")
    private Collection<Usage> flow;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "UsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "CaseDefinition_flow",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public Collection<Usage> getFlow() {
        if (flow == null) {
            flow = new ArrayList<>();
        }
        return flow;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = UsageImpl.class)
    public void setFlow(Collection<Usage> flow) {
        this.flow = flow;
    }



    // @info.archinnov.achilles.annotations.Column("identifier")
    private java.util.UUID identifier;

    @JsonGetter
    @javax.persistence.Column(name = "identifier", table = "CaseDefinition")
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
    @JoinTable(name = "CaseDefinition_importedMembership",
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
    private Collection<Feature> inheritedFeature;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "FeatureMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "CaseDefinition_inheritedFeature",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public Collection<Feature> getInheritedFeature() {
        if (inheritedFeature == null) {
            inheritedFeature = new ArrayList<>();
        }
        return inheritedFeature;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = FeatureImpl.class)
    public void setInheritedFeature(Collection<Feature> inheritedFeature) {
        this.inheritedFeature = inheritedFeature;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("inheritedMembership")
    private List<Membership> inheritedMembership;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "MembershipMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "CaseDefinition_inheritedMembership",
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
    private Collection<Feature> input;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "FeatureMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "CaseDefinition_input",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public Collection<Feature> getInput() {
        if (input == null) {
            input = new ArrayList<>();
        }
        return input;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = FeatureImpl.class)
    public void setInput(Collection<Feature> input) {
        this.input = input;
    }



    // @info.archinnov.achilles.annotations.Column("isAbstract")
    private Boolean isAbstract;

    @JsonGetter
    @javax.persistence.Column(name = "isAbstract", table = "CaseDefinition")
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
    @javax.persistence.Column(name = "isConjugated", table = "CaseDefinition")
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
    @javax.persistence.Column(name = "isSufficient", table = "CaseDefinition")
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
    @javax.persistence.Column(name = "isVariation", table = "CaseDefinition")
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
    @JoinTable(name = "CaseDefinition_member",
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
    @JoinTable(name = "CaseDefinition_membership",
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
    @JoinColumn(name = "multiplicityId", table = "CaseDefinition")
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
    @javax.persistence.Column(name = "name", table = "CaseDefinition")
    public String getName() {
        return name;
    }

    @JsonSetter
    public void setName(String name) {
        this.name = name;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("objectiveRequirement")
    private RequirementUsage objectiveRequirement;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @Any(metaDef = "RequirementUsageMetaDef", metaColumn = @javax.persistence.Column(name = "objectiveRequirementType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "objectiveRequirementId", table = "CaseDefinition")
    public RequirementUsage getObjectiveRequirement() {
        return objectiveRequirement;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = RequirementUsageImpl.class)
    public void setObjectiveRequirement(RequirementUsage objectiveRequirement) {
        this.objectiveRequirement = objectiveRequirement;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("output")
    private Collection<Feature> output;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "FeatureMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "CaseDefinition_output",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public Collection<Feature> getOutput() {
        if (output == null) {
            output = new ArrayList<>();
        }
        return output;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = FeatureImpl.class)
    public void setOutput(Collection<Feature> output) {
        this.output = output;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedAction")
    private Collection<ActionUsage> ownedAction;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ActionUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "CaseDefinition_ownedAction",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public Collection<ActionUsage> getOwnedAction() {
        if (ownedAction == null) {
            ownedAction = new ArrayList<>();
        }
        return ownedAction;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ActionUsageImpl.class)
    public void setOwnedAction(Collection<ActionUsage> ownedAction) {
        this.ownedAction = ownedAction;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedAnalysisCase")
    private Collection<AnalysisCaseUsage> ownedAnalysisCase;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "AnalysisCaseUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "CaseDefinition_ownedAnalysisCase",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public Collection<AnalysisCaseUsage> getOwnedAnalysisCase() {
        if (ownedAnalysisCase == null) {
            ownedAnalysisCase = new ArrayList<>();
        }
        return ownedAnalysisCase;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = AnalysisCaseUsageImpl.class)
    public void setOwnedAnalysisCase(Collection<AnalysisCaseUsage> ownedAnalysisCase) {
        this.ownedAnalysisCase = ownedAnalysisCase;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedAttribute")
    private Collection<AttributeUsage> ownedAttribute;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "AttributeUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "CaseDefinition_ownedAttribute",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public Collection<AttributeUsage> getOwnedAttribute() {
        if (ownedAttribute == null) {
            ownedAttribute = new ArrayList<>();
        }
        return ownedAttribute;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = AttributeUsageImpl.class)
    public void setOwnedAttribute(Collection<AttributeUsage> ownedAttribute) {
        this.ownedAttribute = ownedAttribute;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedCalculation")
    private Collection<CalculationUsage> ownedCalculation;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "CalculationUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "CaseDefinition_ownedCalculation",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public Collection<CalculationUsage> getOwnedCalculation() {
        if (ownedCalculation == null) {
            ownedCalculation = new ArrayList<>();
        }
        return ownedCalculation;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = CalculationUsageImpl.class)
    public void setOwnedCalculation(Collection<CalculationUsage> ownedCalculation) {
        this.ownedCalculation = ownedCalculation;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedCase")
    private Collection<CaseUsage> ownedCase;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "CaseUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "CaseDefinition_ownedCase",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public Collection<CaseUsage> getOwnedCase() {
        if (ownedCase == null) {
            ownedCase = new ArrayList<>();
        }
        return ownedCase;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = CaseUsageImpl.class)
    public void setOwnedCase(Collection<CaseUsage> ownedCase) {
        this.ownedCase = ownedCase;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedConjugator")
    private Conjugation ownedConjugator;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @Any(metaDef = "ConjugationMetaDef", metaColumn = @javax.persistence.Column(name = "ownedConjugatorType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "ownedConjugatorId", table = "CaseDefinition")
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
    private Collection<ConnectionUsage> ownedConnection;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ConnectionUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "CaseDefinition_ownedConnection",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public Collection<ConnectionUsage> getOwnedConnection() {
        if (ownedConnection == null) {
            ownedConnection = new ArrayList<>();
        }
        return ownedConnection;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ConnectionUsageImpl.class)
    public void setOwnedConnection(Collection<ConnectionUsage> ownedConnection) {
        this.ownedConnection = ownedConnection;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedConstraint")
    private Collection<ConstraintUsage> ownedConstraint;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ConstraintUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "CaseDefinition_ownedConstraint",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public Collection<ConstraintUsage> getOwnedConstraint() {
        if (ownedConstraint == null) {
            ownedConstraint = new ArrayList<>();
        }
        return ownedConstraint;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ConstraintUsageImpl.class)
    public void setOwnedConstraint(Collection<ConstraintUsage> ownedConstraint) {
        this.ownedConstraint = ownedConstraint;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedElement")
    private Collection<Element> ownedElement;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ElementMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "CaseDefinition_ownedElement",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
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



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedEndFeature")
    private Collection<Feature> ownedEndFeature;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "FeatureMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "CaseDefinition_ownedEndFeature",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public Collection<Feature> getOwnedEndFeature() {
        if (ownedEndFeature == null) {
            ownedEndFeature = new ArrayList<>();
        }
        return ownedEndFeature;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = FeatureImpl.class)
    public void setOwnedEndFeature(Collection<Feature> ownedEndFeature) {
        this.ownedEndFeature = ownedEndFeature;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedFeature")
    private Collection<Feature> ownedFeature;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "FeatureMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "CaseDefinition_ownedFeature",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public Collection<Feature> getOwnedFeature() {
        if (ownedFeature == null) {
            ownedFeature = new ArrayList<>();
        }
        return ownedFeature;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = FeatureImpl.class)
    public void setOwnedFeature(Collection<Feature> ownedFeature) {
        this.ownedFeature = ownedFeature;
    }



    // @info.archinnov.achilles.annotations.Column("ownedFeatureMembership")
    private List<FeatureMembership> ownedFeatureMembership;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @ManyToAny(metaDef = "FeatureMembershipMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "CaseDefinition_ownedFeatureMembership",
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
    // @info.archinnov.achilles.annotations.Column("ownedGeneralization")
    private List<Generalization> ownedGeneralization;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "GeneralizationMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "CaseDefinition_ownedGeneralization",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public List<Generalization> getOwnedGeneralization() {
        if (ownedGeneralization == null) {
            ownedGeneralization = new ArrayList<>();
        }
        return ownedGeneralization;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = GeneralizationImpl.class)
    public void setOwnedGeneralization(List<Generalization> ownedGeneralization) {
        this.ownedGeneralization = ownedGeneralization;
    }



    // @info.archinnov.achilles.annotations.Column("ownedImport")
    private List<Import> ownedImport;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @ManyToAny(metaDef = "ImportMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "CaseDefinition_ownedImport",
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
    // @info.archinnov.achilles.annotations.Column("ownedIndividual")
    private Collection<IndividualUsage> ownedIndividual;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "IndividualUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "CaseDefinition_ownedIndividual",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public Collection<IndividualUsage> getOwnedIndividual() {
        if (ownedIndividual == null) {
            ownedIndividual = new ArrayList<>();
        }
        return ownedIndividual;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = IndividualUsageImpl.class)
    public void setOwnedIndividual(Collection<IndividualUsage> ownedIndividual) {
        this.ownedIndividual = ownedIndividual;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedInterface")
    private Collection<InterfaceUsage> ownedInterface;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "InterfaceUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "CaseDefinition_ownedInterface",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public Collection<InterfaceUsage> getOwnedInterface() {
        if (ownedInterface == null) {
            ownedInterface = new ArrayList<>();
        }
        return ownedInterface;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = InterfaceUsageImpl.class)
    public void setOwnedInterface(Collection<InterfaceUsage> ownedInterface) {
        this.ownedInterface = ownedInterface;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedItem")
    private Collection<ItemUsage> ownedItem;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ItemUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "CaseDefinition_ownedItem",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public Collection<ItemUsage> getOwnedItem() {
        if (ownedItem == null) {
            ownedItem = new ArrayList<>();
        }
        return ownedItem;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ItemUsageImpl.class)
    public void setOwnedItem(Collection<ItemUsage> ownedItem) {
        this.ownedItem = ownedItem;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedMember")
    private List<Element> ownedMember;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ElementMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "CaseDefinition_ownedMember",
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



    // @info.archinnov.achilles.annotations.Column("ownedMembership")
    private List<Membership> ownedMembership;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @ManyToAny(metaDef = "MembershipMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "CaseDefinition_ownedMembership",
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
    // @info.archinnov.achilles.annotations.Column("ownedPart")
    private Collection<PartUsage> ownedPart;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "PartUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "CaseDefinition_ownedPart",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public Collection<PartUsage> getOwnedPart() {
        if (ownedPart == null) {
            ownedPart = new ArrayList<>();
        }
        return ownedPart;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = PartUsageImpl.class)
    public void setOwnedPart(Collection<PartUsage> ownedPart) {
        this.ownedPart = ownedPart;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedPort")
    private Collection<PortUsage> ownedPort;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "PortUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "CaseDefinition_ownedPort",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public Collection<PortUsage> getOwnedPort() {
        if (ownedPort == null) {
            ownedPort = new ArrayList<>();
        }
        return ownedPort;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = PortUsageImpl.class)
    public void setOwnedPort(Collection<PortUsage> ownedPort) {
        this.ownedPort = ownedPort;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedReference")
    private Collection<ReferenceUsage> ownedReference;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ReferenceUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "CaseDefinition_ownedReference",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public Collection<ReferenceUsage> getOwnedReference() {
        if (ownedReference == null) {
            ownedReference = new ArrayList<>();
        }
        return ownedReference;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ReferenceUsageImpl.class)
    public void setOwnedReference(Collection<ReferenceUsage> ownedReference) {
        this.ownedReference = ownedReference;
    }



    // @info.archinnov.achilles.annotations.Column("ownedRelationship")
    private Collection<Relationship> ownedRelationship;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @ManyToAny(metaDef = "RelationshipMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "CaseDefinition_ownedRelationship",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
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
    // @info.archinnov.achilles.annotations.Column("ownedRequirement")
    private Collection<RequirementUsage> ownedRequirement;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "RequirementUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "CaseDefinition_ownedRequirement",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public Collection<RequirementUsage> getOwnedRequirement() {
        if (ownedRequirement == null) {
            ownedRequirement = new ArrayList<>();
        }
        return ownedRequirement;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = RequirementUsageImpl.class)
    public void setOwnedRequirement(Collection<RequirementUsage> ownedRequirement) {
        this.ownedRequirement = ownedRequirement;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedState")
    private Collection<StateUsage> ownedState;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "StateUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "CaseDefinition_ownedState",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public Collection<StateUsage> getOwnedState() {
        if (ownedState == null) {
            ownedState = new ArrayList<>();
        }
        return ownedState;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = StateUsageImpl.class)
    public void setOwnedState(Collection<StateUsage> ownedState) {
        this.ownedState = ownedState;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedSuperclassing")
    private Collection<Superclassing> ownedSuperclassing;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "SuperclassingMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "CaseDefinition_ownedSuperclassing",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public Collection<Superclassing> getOwnedSuperclassing() {
        if (ownedSuperclassing == null) {
            ownedSuperclassing = new ArrayList<>();
        }
        return ownedSuperclassing;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = SuperclassingImpl.class)
    public void setOwnedSuperclassing(Collection<Superclassing> ownedSuperclassing) {
        this.ownedSuperclassing = ownedSuperclassing;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("ownedTransition")
    private Collection<TransitionUsage> ownedTransition;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "TransitionUsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "CaseDefinition_ownedTransition",
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
    private Collection<Usage> ownedUsage;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "UsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "CaseDefinition_ownedUsage",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public Collection<Usage> getOwnedUsage() {
        if (ownedUsage == null) {
            ownedUsage = new ArrayList<>();
        }
        return ownedUsage;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = UsageImpl.class)
    public void setOwnedUsage(Collection<Usage> ownedUsage) {
        this.ownedUsage = ownedUsage;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("owner")
    private Element owner;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @Any(metaDef = "ElementMetaDef", metaColumn = @javax.persistence.Column(name = "ownerType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "ownerId", table = "CaseDefinition")
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
    @JoinColumn(name = "owningMembershipId", table = "CaseDefinition")
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
    // @javax.persistence.Transient
    @Any(metaDef = "PackageMetaDef", metaColumn = @javax.persistence.Column(name = "owningNamespaceType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "owningNamespaceId", table = "CaseDefinition")
    public Package getOwningNamespace() {
        return owningNamespace;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = PackageImpl.class)
    public void setOwningNamespace(Package owningNamespace) {
        this.owningNamespace = owningNamespace;
    }



    // @info.archinnov.achilles.annotations.Column("owningRelationship")
    private Relationship owningRelationship;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "RelationshipMetaDef", metaColumn = @javax.persistence.Column(name = "owningRelationshipType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "owningRelationshipId", table = "CaseDefinition")
    public Relationship getOwningRelationship() {
        return owningRelationship;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = RelationshipImpl.class)
    public void setOwningRelationship(Relationship owningRelationship) {
        this.owningRelationship = owningRelationship;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("parameter")
    private Collection<Parameter> parameter;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "ParameterMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "CaseDefinition_parameter",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public Collection<Parameter> getParameter() {
        if (parameter == null) {
            parameter = new ArrayList<>();
        }
        return parameter;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ParameterImpl.class)
    public void setParameter(Collection<Parameter> parameter) {
        this.parameter = parameter;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("result")
    private Parameter result;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @Any(metaDef = "ParameterMetaDef", metaColumn = @javax.persistence.Column(name = "resultType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "resultId", table = "CaseDefinition")
    public Parameter getResult() {
        return result;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = ParameterImpl.class)
    public void setResult(Parameter result) {
        this.result = result;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("step")
    private Collection<Step> step;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "StepMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "CaseDefinition_step",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public Collection<Step> getStep() {
        if (step == null) {
            step = new ArrayList<>();
        }
        return step;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = StepImpl.class)
    public void setStep(Collection<Step> step) {
        this.step = step;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("subjectParameter")
    private Parameter subjectParameter;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @Any(metaDef = "ParameterMetaDef", metaColumn = @javax.persistence.Column(name = "subjectParameterType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "subjectParameterId", table = "CaseDefinition")
    public Parameter getSubjectParameter() {
        return subjectParameter;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = ParameterImpl.class)
    public void setSubjectParameter(Parameter subjectParameter) {
        this.subjectParameter = subjectParameter;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("usage")
    private Collection<Usage> usage;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "UsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "CaseDefinition_usage",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    public Collection<Usage> getUsage() {
        if (usage == null) {
            usage = new ArrayList<>();
        }
        return usage;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = UsageImpl.class)
    public void setUsage(Collection<Usage> usage) {
        this.usage = usage;
    }



    // @info.archinnov.achilles.annotations.Transient
    // @info.archinnov.achilles.annotations.Column("variant")
    private Collection<Usage> variant;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    // @javax.persistence.Transient
    @ManyToAny(metaDef = "UsageMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "CaseDefinition_variant",
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



    // @info.archinnov.achilles.annotations.Column("variantMembership")
    private Collection<VariantMembership> variantMembership;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @ManyToAny(metaDef = "VariantMembershipMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "CaseDefinition_variantMembership",
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
