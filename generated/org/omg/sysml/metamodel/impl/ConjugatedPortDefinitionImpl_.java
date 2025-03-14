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
@StaticMetamodel(ConjugatedPortDefinitionImpl.class)
public abstract class ConjugatedPortDefinitionImpl_ extends org.omg.sysml.lifecycle.impl.DataImpl_ {

	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, Usage> directedUsage;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, PortUsage> ownedPort;
	public static volatile SingularAttribute<ConjugatedPortDefinitionImpl, String> declaredShortName;
	public static volatile SingularAttribute<ConjugatedPortDefinitionImpl, Boolean> isConjugated;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, EnumerationUsage> ownedEnumeration;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, VerificationCaseUsage> ownedVerificationCase;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, Usage> usage;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, ViewpointUsage> ownedViewpoint;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, AnalysisCaseUsage> ownedAnalysisCase;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, RenderingUsage> ownedRendering;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, ConstraintUsage> ownedConstraint;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, Feature> output;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, MetadataUsage> ownedMetadata;
	public static volatile CollectionAttribute<ConjugatedPortDefinitionImpl, Usage> variant;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, ItemUsage> ownedItem;
	public static volatile SingularAttribute<ConjugatedPortDefinitionImpl, Boolean> isSufficient;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, Differencing> ownedDifferencing;
	public static volatile SingularAttribute<ConjugatedPortDefinitionImpl, UUID> elementId;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, TextualRepresentation> textualRepresentation;
	public static volatile SingularAttribute<ConjugatedPortDefinitionImpl, Boolean> isLibraryElement;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, Annotation> ownedAnnotation;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, ViewUsage> ownedView;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, Feature> ownedFeature;
	public static volatile SingularAttribute<ConjugatedPortDefinitionImpl, String> qualifiedName;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, Documentation> documentation;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, Type> intersectingType;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, Feature> endFeature;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, Feature> directedFeature;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, AllocationUsage> ownedAllocation;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, InterfaceUsage> ownedInterface;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, Feature> input;
	public static volatile CollectionAttribute<ConjugatedPortDefinitionImpl, FlowUsage> ownedFlow;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, ActionUsage> ownedAction;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, CaseUsage> ownedCase;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, CalculationUsage> ownedCalculation;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, OccurrenceUsage> ownedOccurrence;
	public static volatile SingularAttribute<ConjugatedPortDefinitionImpl, String> name;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, ReferenceUsage> ownedReference;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, Usage> ownedUsage;
	public static volatile SingularAttribute<ConjugatedPortDefinitionImpl, String> shortName;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, Element> ownedMember;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, Membership> ownedMembership;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, UseCaseUsage> ownedUseCase;
	public static volatile CollectionAttribute<ConjugatedPortDefinitionImpl, Disjoining> ownedDisjoining;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, StateUsage> ownedState;
	public static volatile SingularAttribute<ConjugatedPortDefinitionImpl, Boolean> isIndividual;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, Membership> membership;
	public static volatile SingularAttribute<ConjugatedPortDefinitionImpl, Boolean> isImpliedIncluded;
	public static volatile SingularAttribute<ConjugatedPortDefinitionImpl, Boolean> isVariation;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, Feature> feature;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, Membership> inheritedMembership;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, Element> member;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, Feature> ownedEndFeature;
	public static volatile SingularAttribute<ConjugatedPortDefinitionImpl, String> declaredName;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, FeatureMembership> ownedFeatureMembership;
	public static volatile CollectionAttribute<ConjugatedPortDefinitionImpl, Subclassification> ownedSubclassification;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, Feature> inheritedFeature;
	public static volatile CollectionAttribute<ConjugatedPortDefinitionImpl, TransitionUsage> ownedTransition;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, Membership> importedMembership;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, Element> ownedElement;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, String> aliasIds;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, Relationship> ownedRelationship;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, ConnectorAsUsage> ownedConnection;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, FeatureMembership> featureMembership;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, Import> ownedImport;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, RequirementUsage> ownedRequirement;
	public static volatile SingularAttribute<ConjugatedPortDefinitionImpl, Boolean> isAbstract;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, Type> differencingType;
	public static volatile CollectionAttribute<ConjugatedPortDefinitionImpl, VariantMembership> variantMembership;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, Unioning> ownedUnioning;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, Type> unioningType;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, Specialization> ownedSpecialization;
	public static volatile CollectionAttribute<ConjugatedPortDefinitionImpl, ConcernUsage> ownedConcern;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, AttributeUsage> ownedAttribute;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, Intersecting> ownedIntersecting;
	public static volatile ListAttribute<ConjugatedPortDefinitionImpl, PartUsage> ownedPart;

	public static final String DIRECTED_USAGE = "directedUsage";
	public static final String OWNED_PORT = "ownedPort";
	public static final String DECLARED_SHORT_NAME = "declaredShortName";
	public static final String IS_CONJUGATED = "isConjugated";
	public static final String OWNED_ENUMERATION = "ownedEnumeration";
	public static final String OWNED_VERIFICATION_CASE = "ownedVerificationCase";
	public static final String USAGE = "usage";
	public static final String OWNED_VIEWPOINT = "ownedViewpoint";
	public static final String OWNED_ANALYSIS_CASE = "ownedAnalysisCase";
	public static final String OWNED_RENDERING = "ownedRendering";
	public static final String OWNED_CONSTRAINT = "ownedConstraint";
	public static final String OUTPUT = "output";
	public static final String OWNED_METADATA = "ownedMetadata";
	public static final String VARIANT = "variant";
	public static final String OWNED_ITEM = "ownedItem";
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
	public static final String INPUT = "input";
	public static final String OWNED_FLOW = "ownedFlow";
	public static final String OWNED_ACTION = "ownedAction";
	public static final String OWNED_CASE = "ownedCase";
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
	public static final String MEMBER = "member";
	public static final String OWNED_END_FEATURE = "ownedEndFeature";
	public static final String DECLARED_NAME = "declaredName";
	public static final String OWNED_FEATURE_MEMBERSHIP = "ownedFeatureMembership";
	public static final String OWNED_SUBCLASSIFICATION = "ownedSubclassification";
	public static final String INHERITED_FEATURE = "inheritedFeature";
	public static final String OWNED_TRANSITION = "ownedTransition";
	public static final String IMPORTED_MEMBERSHIP = "importedMembership";
	public static final String OWNED_ELEMENT = "ownedElement";
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
	public static final String OWNED_ATTRIBUTE = "ownedAttribute";
	public static final String OWNED_INTERSECTING = "ownedIntersecting";
	public static final String OWNED_PART = "ownedPart";

}

