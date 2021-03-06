package org.omg.sysml.metamodel.impl;

import java.util.UUID;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.omg.sysml.metamodel.ActionUsage;
import org.omg.sysml.metamodel.AllocationUsage;
import org.omg.sysml.metamodel.AnalysisCaseUsage;
import org.omg.sysml.metamodel.Annotation;
import org.omg.sysml.metamodel.AttributeUsage;
import org.omg.sysml.metamodel.CalculationUsage;
import org.omg.sysml.metamodel.CaseUsage;
import org.omg.sysml.metamodel.Comment;
import org.omg.sysml.metamodel.ConcernUsage;
import org.omg.sysml.metamodel.ConnectionUsage;
import org.omg.sysml.metamodel.ConstraintUsage;
import org.omg.sysml.metamodel.Documentation;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.EnumerationUsage;
import org.omg.sysml.metamodel.Expression;
import org.omg.sysml.metamodel.Feature;
import org.omg.sysml.metamodel.FeatureMembership;
import org.omg.sysml.metamodel.Generalization;
import org.omg.sysml.metamodel.Import;
import org.omg.sysml.metamodel.InterfaceUsage;
import org.omg.sysml.metamodel.ItemUsage;
import org.omg.sysml.metamodel.Membership;
import org.omg.sysml.metamodel.OccurrenceUsage;
import org.omg.sysml.metamodel.PartUsage;
import org.omg.sysml.metamodel.PortUsage;
import org.omg.sysml.metamodel.ReferenceUsage;
import org.omg.sysml.metamodel.Relationship;
import org.omg.sysml.metamodel.RenderingUsage;
import org.omg.sysml.metamodel.RequirementUsage;
import org.omg.sysml.metamodel.StakeholderUsage;
import org.omg.sysml.metamodel.StateUsage;
import org.omg.sysml.metamodel.Step;
import org.omg.sysml.metamodel.Superclassing;
import org.omg.sysml.metamodel.TextualRepresentation;
import org.omg.sysml.metamodel.TransitionUsage;
import org.omg.sysml.metamodel.Usage;
import org.omg.sysml.metamodel.VariantMembership;
import org.omg.sysml.metamodel.VerificationCaseUsage;
import org.omg.sysml.metamodel.ViewUsage;
import org.omg.sysml.metamodel.ViewpointUsage;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ConcernDefinitionImpl.class)
public abstract class ConcernDefinitionImpl_ extends org.omg.sysml.metamodel.impl.MofObjectImpl_ {

	public static volatile CollectionAttribute<ConcernDefinitionImpl, PortUsage> ownedPort;
	public static volatile ListAttribute<ConcernDefinitionImpl, Generalization> ownedGeneralization;
	public static volatile SingularAttribute<ConcernDefinitionImpl, Boolean> isConjugated;
	public static volatile CollectionAttribute<ConcernDefinitionImpl, EnumerationUsage> ownedEnumeration;
	public static volatile CollectionAttribute<ConcernDefinitionImpl, VerificationCaseUsage> ownedVerificationCase;
	public static volatile CollectionAttribute<ConcernDefinitionImpl, ConstraintUsage> requiredConstraint;
	public static volatile CollectionAttribute<ConcernDefinitionImpl, Usage> usage;
	public static volatile CollectionAttribute<ConcernDefinitionImpl, ViewpointUsage> ownedViewpoint;
	public static volatile CollectionAttribute<ConcernDefinitionImpl, AnalysisCaseUsage> ownedAnalysisCase;
	public static volatile CollectionAttribute<ConcernDefinitionImpl, RenderingUsage> ownedRendering;
	public static volatile CollectionAttribute<ConcernDefinitionImpl, ConstraintUsage> ownedConstraint;
	public static volatile ListAttribute<ConcernDefinitionImpl, Feature> output;
	public static volatile CollectionAttribute<ConcernDefinitionImpl, Usage> variant;
	public static volatile CollectionAttribute<ConcernDefinitionImpl, ItemUsage> ownedItem;
	public static volatile CollectionAttribute<ConcernDefinitionImpl, String> text;
	public static volatile SingularAttribute<ConcernDefinitionImpl, Boolean> isSufficient;
	public static volatile ListAttribute<ConcernDefinitionImpl, Comment> documentationComment;
	public static volatile SingularAttribute<ConcernDefinitionImpl, UUID> identifier;
	public static volatile ListAttribute<ConcernDefinitionImpl, Annotation> ownedAnnotation;
	public static volatile CollectionAttribute<ConcernDefinitionImpl, ViewUsage> ownedView;
	public static volatile ListAttribute<ConcernDefinitionImpl, Feature> ownedFeature;
	public static volatile CollectionAttribute<ConcernDefinitionImpl, Usage> flowFeature;
	public static volatile SingularAttribute<ConcernDefinitionImpl, String> qualifiedName;
	public static volatile ListAttribute<ConcernDefinitionImpl, Documentation> documentation;
	public static volatile ListAttribute<ConcernDefinitionImpl, Feature> endFeature;
	public static volatile CollectionAttribute<ConcernDefinitionImpl, AllocationUsage> ownedAllocation;
	public static volatile CollectionAttribute<ConcernDefinitionImpl, InterfaceUsage> ownedInterface;
	public static volatile CollectionAttribute<ConcernDefinitionImpl, ConcernUsage> addressedConcern;
	public static volatile SingularAttribute<ConcernDefinitionImpl, String> reqId;
	public static volatile ListAttribute<ConcernDefinitionImpl, Feature> input;
	public static volatile CollectionAttribute<ConcernDefinitionImpl, ActionUsage> ownedAction;
	public static volatile CollectionAttribute<ConcernDefinitionImpl, ConstraintUsage> assumedConstraint;
	public static volatile CollectionAttribute<ConcernDefinitionImpl, CaseUsage> ownedCase;
	public static volatile CollectionAttribute<ConcernDefinitionImpl, CalculationUsage> ownedCalculation;
	public static volatile CollectionAttribute<ConcernDefinitionImpl, OccurrenceUsage> ownedOccurrence;
	public static volatile SingularAttribute<ConcernDefinitionImpl, String> name;
	public static volatile CollectionAttribute<ConcernDefinitionImpl, ReferenceUsage> ownedReference;
	public static volatile CollectionAttribute<ConcernDefinitionImpl, Usage> ownedUsage;
	public static volatile ListAttribute<ConcernDefinitionImpl, Element> ownedMember;
	public static volatile ListAttribute<ConcernDefinitionImpl, Membership> ownedMembership;
	public static volatile CollectionAttribute<ConcernDefinitionImpl, StateUsage> ownedState;
	public static volatile SingularAttribute<ConcernDefinitionImpl, Boolean> isIndividual;
	public static volatile ListAttribute<ConcernDefinitionImpl, Membership> membership;
	public static volatile CollectionAttribute<ConcernDefinitionImpl, Superclassing> ownedSuperclassing;
	public static volatile SingularAttribute<ConcernDefinitionImpl, Boolean> isVariation;
	public static volatile ListAttribute<ConcernDefinitionImpl, Feature> feature;
	public static volatile ListAttribute<ConcernDefinitionImpl, Membership> inheritedMembership;
	public static volatile ListAttribute<ConcernDefinitionImpl, Feature> parameter;
	public static volatile ListAttribute<ConcernDefinitionImpl, Element> member;
	public static volatile ListAttribute<ConcernDefinitionImpl, Feature> ownedEndFeature;
	public static volatile ListAttribute<ConcernDefinitionImpl, FeatureMembership> ownedFeatureMembership;
	public static volatile ListAttribute<ConcernDefinitionImpl, Feature> inheritedFeature;
	public static volatile CollectionAttribute<ConcernDefinitionImpl, TransitionUsage> ownedTransition;
	public static volatile SingularAttribute<ConcernDefinitionImpl, String> effectiveName;
	public static volatile ListAttribute<ConcernDefinitionImpl, Membership> importedMembership;
	public static volatile ListAttribute<ConcernDefinitionImpl, Element> ownedElement;
	public static volatile CollectionAttribute<ConcernDefinitionImpl, Expression> expression;
	public static volatile SingularAttribute<ConcernDefinitionImpl, Boolean> isModelLevelEvaluable;
	public static volatile ListAttribute<ConcernDefinitionImpl, Relationship> ownedRelationship;
	public static volatile CollectionAttribute<ConcernDefinitionImpl, ConnectionUsage> ownedConnection;
	public static volatile ListAttribute<ConcernDefinitionImpl, FeatureMembership> featureMembership;
	public static volatile ListAttribute<ConcernDefinitionImpl, Import> ownedImport;
	public static volatile CollectionAttribute<ConcernDefinitionImpl, RequirementUsage> ownedRequirement;
	public static volatile SingularAttribute<ConcernDefinitionImpl, Boolean> isAbstract;
	public static volatile CollectionAttribute<ConcernDefinitionImpl, TextualRepresentation> ownedTextualRepresentation;
	public static volatile CollectionAttribute<ConcernDefinitionImpl, VariantMembership> variantMembership;
	public static volatile SingularAttribute<ConcernDefinitionImpl, String> humanId;
	public static volatile CollectionAttribute<ConcernDefinitionImpl, StakeholderUsage> ownedStakeholder;
	public static volatile ListAttribute<ConcernDefinitionImpl, String> aliasId;
	public static volatile CollectionAttribute<ConcernDefinitionImpl, ConcernUsage> ownedConcern;
	public static volatile CollectionAttribute<ConcernDefinitionImpl, Step> step;
	public static volatile CollectionAttribute<ConcernDefinitionImpl, AttributeUsage> ownedAttribute;
	public static volatile CollectionAttribute<ConcernDefinitionImpl, PartUsage> ownedPart;

	public static final String OWNED_PORT = "ownedPort";
	public static final String OWNED_GENERALIZATION = "ownedGeneralization";
	public static final String IS_CONJUGATED = "isConjugated";
	public static final String OWNED_ENUMERATION = "ownedEnumeration";
	public static final String OWNED_VERIFICATION_CASE = "ownedVerificationCase";
	public static final String REQUIRED_CONSTRAINT = "requiredConstraint";
	public static final String USAGE = "usage";
	public static final String OWNED_VIEWPOINT = "ownedViewpoint";
	public static final String OWNED_ANALYSIS_CASE = "ownedAnalysisCase";
	public static final String OWNED_RENDERING = "ownedRendering";
	public static final String OWNED_CONSTRAINT = "ownedConstraint";
	public static final String OUTPUT = "output";
	public static final String VARIANT = "variant";
	public static final String OWNED_ITEM = "ownedItem";
	public static final String TEXT = "text";
	public static final String IS_SUFFICIENT = "isSufficient";
	public static final String DOCUMENTATION_COMMENT = "documentationComment";
	public static final String IDENTIFIER = "identifier";
	public static final String OWNED_ANNOTATION = "ownedAnnotation";
	public static final String OWNED_VIEW = "ownedView";
	public static final String OWNED_FEATURE = "ownedFeature";
	public static final String FLOW_FEATURE = "flowFeature";
	public static final String QUALIFIED_NAME = "qualifiedName";
	public static final String DOCUMENTATION = "documentation";
	public static final String END_FEATURE = "endFeature";
	public static final String OWNED_ALLOCATION = "ownedAllocation";
	public static final String OWNED_INTERFACE = "ownedInterface";
	public static final String ADDRESSED_CONCERN = "addressedConcern";
	public static final String REQ_ID = "reqId";
	public static final String INPUT = "input";
	public static final String OWNED_ACTION = "ownedAction";
	public static final String ASSUMED_CONSTRAINT = "assumedConstraint";
	public static final String OWNED_CASE = "ownedCase";
	public static final String OWNED_CALCULATION = "ownedCalculation";
	public static final String OWNED_OCCURRENCE = "ownedOccurrence";
	public static final String NAME = "name";
	public static final String OWNED_REFERENCE = "ownedReference";
	public static final String OWNED_USAGE = "ownedUsage";
	public static final String OWNED_MEMBER = "ownedMember";
	public static final String OWNED_MEMBERSHIP = "ownedMembership";
	public static final String OWNED_STATE = "ownedState";
	public static final String IS_INDIVIDUAL = "isIndividual";
	public static final String MEMBERSHIP = "membership";
	public static final String OWNED_SUPERCLASSING = "ownedSuperclassing";
	public static final String IS_VARIATION = "isVariation";
	public static final String FEATURE = "feature";
	public static final String INHERITED_MEMBERSHIP = "inheritedMembership";
	public static final String PARAMETER = "parameter";
	public static final String MEMBER = "member";
	public static final String OWNED_END_FEATURE = "ownedEndFeature";
	public static final String OWNED_FEATURE_MEMBERSHIP = "ownedFeatureMembership";
	public static final String INHERITED_FEATURE = "inheritedFeature";
	public static final String OWNED_TRANSITION = "ownedTransition";
	public static final String EFFECTIVE_NAME = "effectiveName";
	public static final String IMPORTED_MEMBERSHIP = "importedMembership";
	public static final String OWNED_ELEMENT = "ownedElement";
	public static final String EXPRESSION = "expression";
	public static final String IS_MODEL_LEVEL_EVALUABLE = "isModelLevelEvaluable";
	public static final String OWNED_RELATIONSHIP = "ownedRelationship";
	public static final String OWNED_CONNECTION = "ownedConnection";
	public static final String FEATURE_MEMBERSHIP = "featureMembership";
	public static final String OWNED_IMPORT = "ownedImport";
	public static final String OWNED_REQUIREMENT = "ownedRequirement";
	public static final String IS_ABSTRACT = "isAbstract";
	public static final String OWNED_TEXTUAL_REPRESENTATION = "ownedTextualRepresentation";
	public static final String VARIANT_MEMBERSHIP = "variantMembership";
	public static final String HUMAN_ID = "humanId";
	public static final String OWNED_STAKEHOLDER = "ownedStakeholder";
	public static final String ALIAS_ID = "aliasId";
	public static final String OWNED_CONCERN = "ownedConcern";
	public static final String STEP = "step";
	public static final String OWNED_ATTRIBUTE = "ownedAttribute";
	public static final String OWNED_PART = "ownedPart";

}

