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
import org.omg.sysml.metamodel.ConcernUsage;
import org.omg.sysml.metamodel.ConnectorAsUsage;
import org.omg.sysml.metamodel.ConstraintUsage;
import org.omg.sysml.metamodel.Differencing;
import org.omg.sysml.metamodel.Disjoining;
import org.omg.sysml.metamodel.Documentation;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.EnumerationUsage;
import org.omg.sysml.metamodel.Expression;
import org.omg.sysml.metamodel.Feature;
import org.omg.sysml.metamodel.FeatureMembership;
import org.omg.sysml.metamodel.FlowUsage;
import org.omg.sysml.metamodel.Import;
import org.omg.sysml.metamodel.InterfaceUsage;
import org.omg.sysml.metamodel.Intersecting;
import org.omg.sysml.metamodel.ItemUsage;
import org.omg.sysml.metamodel.Membership;
import org.omg.sysml.metamodel.MetadataUsage;
import org.omg.sysml.metamodel.OccurrenceUsage;
import org.omg.sysml.metamodel.PartUsage;
import org.omg.sysml.metamodel.PortUsage;
import org.omg.sysml.metamodel.ReferenceUsage;
import org.omg.sysml.metamodel.Relationship;
import org.omg.sysml.metamodel.RenderingUsage;
import org.omg.sysml.metamodel.RequirementUsage;
import org.omg.sysml.metamodel.Specialization;
import org.omg.sysml.metamodel.StateUsage;
import org.omg.sysml.metamodel.Step;
import org.omg.sysml.metamodel.Subclassification;
import org.omg.sysml.metamodel.TextualRepresentation;
import org.omg.sysml.metamodel.TransitionUsage;
import org.omg.sysml.metamodel.Type;
import org.omg.sysml.metamodel.Unioning;
import org.omg.sysml.metamodel.Usage;
import org.omg.sysml.metamodel.UseCaseUsage;
import org.omg.sysml.metamodel.VariantMembership;
import org.omg.sysml.metamodel.VerificationCaseUsage;
import org.omg.sysml.metamodel.ViewUsage;
import org.omg.sysml.metamodel.ViewpointUsage;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ConcernDefinitionImpl.class)
public abstract class ConcernDefinitionImpl_ extends org.omg.sysml.lifecycle.impl.DataImpl_ {

	public static volatile ListAttribute<ConcernDefinitionImpl, Usage> directedUsage;
	public static volatile ListAttribute<ConcernDefinitionImpl, PortUsage> ownedPort;
	public static volatile SingularAttribute<ConcernDefinitionImpl, String> declaredShortName;
	public static volatile SingularAttribute<ConcernDefinitionImpl, Boolean> isConjugated;
	public static volatile ListAttribute<ConcernDefinitionImpl, EnumerationUsage> ownedEnumeration;
	public static volatile ListAttribute<ConcernDefinitionImpl, VerificationCaseUsage> ownedVerificationCase;
	public static volatile ListAttribute<ConcernDefinitionImpl, ConstraintUsage> requiredConstraint;
	public static volatile ListAttribute<ConcernDefinitionImpl, Usage> usage;
	public static volatile ListAttribute<ConcernDefinitionImpl, ViewpointUsage> ownedViewpoint;
	public static volatile ListAttribute<ConcernDefinitionImpl, AnalysisCaseUsage> ownedAnalysisCase;
	public static volatile ListAttribute<ConcernDefinitionImpl, RenderingUsage> ownedRendering;
	public static volatile ListAttribute<ConcernDefinitionImpl, ConstraintUsage> ownedConstraint;
	public static volatile ListAttribute<ConcernDefinitionImpl, Feature> output;
	public static volatile ListAttribute<ConcernDefinitionImpl, MetadataUsage> ownedMetadata;
	public static volatile ListAttribute<ConcernDefinitionImpl, ConcernUsage> framedConcern;
	public static volatile CollectionAttribute<ConcernDefinitionImpl, Usage> variant;
	public static volatile ListAttribute<ConcernDefinitionImpl, ItemUsage> ownedItem;
	public static volatile CollectionAttribute<ConcernDefinitionImpl, String> text;
	public static volatile SingularAttribute<ConcernDefinitionImpl, Boolean> isSufficient;
	public static volatile ListAttribute<ConcernDefinitionImpl, Differencing> ownedDifferencing;
	public static volatile SingularAttribute<ConcernDefinitionImpl, UUID> elementId;
	public static volatile ListAttribute<ConcernDefinitionImpl, TextualRepresentation> textualRepresentation;
	public static volatile SingularAttribute<ConcernDefinitionImpl, Boolean> isLibraryElement;
	public static volatile ListAttribute<ConcernDefinitionImpl, Annotation> ownedAnnotation;
	public static volatile ListAttribute<ConcernDefinitionImpl, ViewUsage> ownedView;
	public static volatile ListAttribute<ConcernDefinitionImpl, Feature> ownedFeature;
	public static volatile SingularAttribute<ConcernDefinitionImpl, String> qualifiedName;
	public static volatile ListAttribute<ConcernDefinitionImpl, Documentation> documentation;
	public static volatile ListAttribute<ConcernDefinitionImpl, Type> intersectingType;
	public static volatile ListAttribute<ConcernDefinitionImpl, Feature> endFeature;
	public static volatile ListAttribute<ConcernDefinitionImpl, Feature> directedFeature;
	public static volatile ListAttribute<ConcernDefinitionImpl, AllocationUsage> ownedAllocation;
	public static volatile ListAttribute<ConcernDefinitionImpl, InterfaceUsage> ownedInterface;
	public static volatile SingularAttribute<ConcernDefinitionImpl, String> reqId;
	public static volatile ListAttribute<ConcernDefinitionImpl, PartUsage> stakeholderParameter;
	public static volatile ListAttribute<ConcernDefinitionImpl, Feature> input;
	public static volatile CollectionAttribute<ConcernDefinitionImpl, FlowUsage> ownedFlow;
	public static volatile ListAttribute<ConcernDefinitionImpl, ActionUsage> ownedAction;
	public static volatile ListAttribute<ConcernDefinitionImpl, ConstraintUsage> assumedConstraint;
	public static volatile ListAttribute<ConcernDefinitionImpl, CaseUsage> ownedCase;
	public static volatile ListAttribute<ConcernDefinitionImpl, PartUsage> actorParameter;
	public static volatile ListAttribute<ConcernDefinitionImpl, CalculationUsage> ownedCalculation;
	public static volatile ListAttribute<ConcernDefinitionImpl, OccurrenceUsage> ownedOccurrence;
	public static volatile SingularAttribute<ConcernDefinitionImpl, String> name;
	public static volatile ListAttribute<ConcernDefinitionImpl, ReferenceUsage> ownedReference;
	public static volatile ListAttribute<ConcernDefinitionImpl, Usage> ownedUsage;
	public static volatile SingularAttribute<ConcernDefinitionImpl, String> shortName;
	public static volatile ListAttribute<ConcernDefinitionImpl, Element> ownedMember;
	public static volatile ListAttribute<ConcernDefinitionImpl, Membership> ownedMembership;
	public static volatile ListAttribute<ConcernDefinitionImpl, UseCaseUsage> ownedUseCase;
	public static volatile CollectionAttribute<ConcernDefinitionImpl, Disjoining> ownedDisjoining;
	public static volatile ListAttribute<ConcernDefinitionImpl, StateUsage> ownedState;
	public static volatile SingularAttribute<ConcernDefinitionImpl, Boolean> isIndividual;
	public static volatile ListAttribute<ConcernDefinitionImpl, Membership> membership;
	public static volatile SingularAttribute<ConcernDefinitionImpl, Boolean> isImpliedIncluded;
	public static volatile SingularAttribute<ConcernDefinitionImpl, Boolean> isVariation;
	public static volatile ListAttribute<ConcernDefinitionImpl, Feature> feature;
	public static volatile ListAttribute<ConcernDefinitionImpl, Membership> inheritedMembership;
	public static volatile ListAttribute<ConcernDefinitionImpl, Feature> parameter;
	public static volatile ListAttribute<ConcernDefinitionImpl, Element> member;
	public static volatile ListAttribute<ConcernDefinitionImpl, Feature> ownedEndFeature;
	public static volatile SingularAttribute<ConcernDefinitionImpl, String> declaredName;
	public static volatile ListAttribute<ConcernDefinitionImpl, FeatureMembership> ownedFeatureMembership;
	public static volatile CollectionAttribute<ConcernDefinitionImpl, Subclassification> ownedSubclassification;
	public static volatile ListAttribute<ConcernDefinitionImpl, Feature> inheritedFeature;
	public static volatile CollectionAttribute<ConcernDefinitionImpl, TransitionUsage> ownedTransition;
	public static volatile ListAttribute<ConcernDefinitionImpl, Membership> importedMembership;
	public static volatile ListAttribute<ConcernDefinitionImpl, Element> ownedElement;
	public static volatile CollectionAttribute<ConcernDefinitionImpl, Expression> expression;
	public static volatile SingularAttribute<ConcernDefinitionImpl, Boolean> isModelLevelEvaluable;
	public static volatile ListAttribute<ConcernDefinitionImpl, String> aliasIds;
	public static volatile ListAttribute<ConcernDefinitionImpl, Relationship> ownedRelationship;
	public static volatile ListAttribute<ConcernDefinitionImpl, ConnectorAsUsage> ownedConnection;
	public static volatile ListAttribute<ConcernDefinitionImpl, FeatureMembership> featureMembership;
	public static volatile ListAttribute<ConcernDefinitionImpl, Import> ownedImport;
	public static volatile ListAttribute<ConcernDefinitionImpl, RequirementUsage> ownedRequirement;
	public static volatile SingularAttribute<ConcernDefinitionImpl, Boolean> isAbstract;
	public static volatile ListAttribute<ConcernDefinitionImpl, Type> differencingType;
	public static volatile CollectionAttribute<ConcernDefinitionImpl, VariantMembership> variantMembership;
	public static volatile ListAttribute<ConcernDefinitionImpl, Unioning> ownedUnioning;
	public static volatile ListAttribute<ConcernDefinitionImpl, Type> unioningType;
	public static volatile ListAttribute<ConcernDefinitionImpl, Specialization> ownedSpecialization;
	public static volatile CollectionAttribute<ConcernDefinitionImpl, ConcernUsage> ownedConcern;
	public static volatile CollectionAttribute<ConcernDefinitionImpl, Step> step;
	public static volatile ListAttribute<ConcernDefinitionImpl, AttributeUsage> ownedAttribute;
	public static volatile ListAttribute<ConcernDefinitionImpl, Intersecting> ownedIntersecting;
	public static volatile ListAttribute<ConcernDefinitionImpl, PartUsage> ownedPart;

	public static final String DIRECTED_USAGE = "directedUsage";
	public static final String OWNED_PORT = "ownedPort";
	public static final String DECLARED_SHORT_NAME = "declaredShortName";
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
	public static final String OWNED_METADATA = "ownedMetadata";
	public static final String FRAMED_CONCERN = "framedConcern";
	public static final String VARIANT = "variant";
	public static final String OWNED_ITEM = "ownedItem";
	public static final String TEXT = "text";
	public static final String IS_SUFFICIENT = "isSufficient";
	public static final String OWNED_DIFFERENCING = "ownedDifferencing";
	public static final String ELEMENT_ID = "elementId";
	public static final String TEXTUAL_REPRESENTATION = "textualRepresentation";
	public static final String IS_LIBRARY_ELEMENT = "isLibraryElement";
	public static final String OWNED_ANNOTATION = "ownedAnnotation";
	public static final String OWNED_VIEW = "ownedView";
	public static final String OWNED_FEATURE = "ownedFeature";
	public static final String QUALIFIED_NAME = "qualifiedName";
	public static final String DOCUMENTATION = "documentation";
	public static final String INTERSECTING_TYPE = "intersectingType";
	public static final String END_FEATURE = "endFeature";
	public static final String DIRECTED_FEATURE = "directedFeature";
	public static final String OWNED_ALLOCATION = "ownedAllocation";
	public static final String OWNED_INTERFACE = "ownedInterface";
	public static final String REQ_ID = "reqId";
	public static final String STAKEHOLDER_PARAMETER = "stakeholderParameter";
	public static final String INPUT = "input";
	public static final String OWNED_FLOW = "ownedFlow";
	public static final String OWNED_ACTION = "ownedAction";
	public static final String ASSUMED_CONSTRAINT = "assumedConstraint";
	public static final String OWNED_CASE = "ownedCase";
	public static final String ACTOR_PARAMETER = "actorParameter";
	public static final String OWNED_CALCULATION = "ownedCalculation";
	public static final String OWNED_OCCURRENCE = "ownedOccurrence";
	public static final String NAME = "name";
	public static final String OWNED_REFERENCE = "ownedReference";
	public static final String OWNED_USAGE = "ownedUsage";
	public static final String SHORT_NAME = "shortName";
	public static final String OWNED_MEMBER = "ownedMember";
	public static final String OWNED_MEMBERSHIP = "ownedMembership";
	public static final String OWNED_USE_CASE = "ownedUseCase";
	public static final String OWNED_DISJOINING = "ownedDisjoining";
	public static final String OWNED_STATE = "ownedState";
	public static final String IS_INDIVIDUAL = "isIndividual";
	public static final String MEMBERSHIP = "membership";
	public static final String IS_IMPLIED_INCLUDED = "isImpliedIncluded";
	public static final String IS_VARIATION = "isVariation";
	public static final String FEATURE = "feature";
	public static final String INHERITED_MEMBERSHIP = "inheritedMembership";
	public static final String PARAMETER = "parameter";
	public static final String MEMBER = "member";
	public static final String OWNED_END_FEATURE = "ownedEndFeature";
	public static final String DECLARED_NAME = "declaredName";
	public static final String OWNED_FEATURE_MEMBERSHIP = "ownedFeatureMembership";
	public static final String OWNED_SUBCLASSIFICATION = "ownedSubclassification";
	public static final String INHERITED_FEATURE = "inheritedFeature";
	public static final String OWNED_TRANSITION = "ownedTransition";
	public static final String IMPORTED_MEMBERSHIP = "importedMembership";
	public static final String OWNED_ELEMENT = "ownedElement";
	public static final String EXPRESSION = "expression";
	public static final String IS_MODEL_LEVEL_EVALUABLE = "isModelLevelEvaluable";
	public static final String ALIAS_IDS = "aliasIds";
	public static final String OWNED_RELATIONSHIP = "ownedRelationship";
	public static final String OWNED_CONNECTION = "ownedConnection";
	public static final String FEATURE_MEMBERSHIP = "featureMembership";
	public static final String OWNED_IMPORT = "ownedImport";
	public static final String OWNED_REQUIREMENT = "ownedRequirement";
	public static final String IS_ABSTRACT = "isAbstract";
	public static final String DIFFERENCING_TYPE = "differencingType";
	public static final String VARIANT_MEMBERSHIP = "variantMembership";
	public static final String OWNED_UNIONING = "ownedUnioning";
	public static final String UNIONING_TYPE = "unioningType";
	public static final String OWNED_SPECIALIZATION = "ownedSpecialization";
	public static final String OWNED_CONCERN = "ownedConcern";
	public static final String STEP = "step";
	public static final String OWNED_ATTRIBUTE = "ownedAttribute";
	public static final String OWNED_INTERSECTING = "ownedIntersecting";
	public static final String OWNED_PART = "ownedPart";

}

