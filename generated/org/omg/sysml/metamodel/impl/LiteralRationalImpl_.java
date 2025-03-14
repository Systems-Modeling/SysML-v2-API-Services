package org.omg.sysml.metamodel.impl;

import java.util.UUID;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.omg.sysml.metamodel.Annotation;
import org.omg.sysml.metamodel.Behavior;
import org.omg.sysml.metamodel.Differencing;
import org.omg.sysml.metamodel.Disjoining;
import org.omg.sysml.metamodel.Documentation;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.Feature;
import org.omg.sysml.metamodel.FeatureChaining;
import org.omg.sysml.metamodel.FeatureDirectionKind;
import org.omg.sysml.metamodel.FeatureInverting;
import org.omg.sysml.metamodel.FeatureMembership;
import org.omg.sysml.metamodel.FeatureTyping;
import org.omg.sysml.metamodel.Import;
import org.omg.sysml.metamodel.Intersecting;
import org.omg.sysml.metamodel.Membership;
import org.omg.sysml.metamodel.Redefinition;
import org.omg.sysml.metamodel.Relationship;
import org.omg.sysml.metamodel.Specialization;
import org.omg.sysml.metamodel.Subsetting;
import org.omg.sysml.metamodel.TextualRepresentation;
import org.omg.sysml.metamodel.Type;
import org.omg.sysml.metamodel.TypeFeaturing;
import org.omg.sysml.metamodel.Unioning;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(LiteralRationalImpl.class)
public abstract class LiteralRationalImpl_ extends org.omg.sysml.lifecycle.impl.DataImpl_ {

	public static volatile SingularAttribute<LiteralRationalImpl, String> declaredShortName;
	public static volatile ListAttribute<LiteralRationalImpl, Feature> chainingFeature;
	public static volatile ListAttribute<LiteralRationalImpl, TypeFeaturing> ownedTypeFeaturing;
	public static volatile SingularAttribute<LiteralRationalImpl, Boolean> isConjugated;
	public static volatile SingularAttribute<LiteralRationalImpl, Boolean> isUnique;
	public static volatile CollectionAttribute<LiteralRationalImpl, Subsetting> ownedSubsetting;
	public static volatile ListAttribute<LiteralRationalImpl, Type> type;
	public static volatile ListAttribute<LiteralRationalImpl, Feature> output;
	public static volatile SingularAttribute<LiteralRationalImpl, Boolean> isSufficient;
	public static volatile ListAttribute<LiteralRationalImpl, Differencing> ownedDifferencing;
	public static volatile SingularAttribute<LiteralRationalImpl, Boolean> isOrdered;
	public static volatile CollectionAttribute<LiteralRationalImpl, Redefinition> ownedRedefinition;
	public static volatile SingularAttribute<LiteralRationalImpl, UUID> elementId;
	public static volatile ListAttribute<LiteralRationalImpl, TextualRepresentation> textualRepresentation;
	public static volatile SingularAttribute<LiteralRationalImpl, Boolean> isLibraryElement;
	public static volatile SingularAttribute<LiteralRationalImpl, Boolean> isDerived;
	public static volatile ListAttribute<LiteralRationalImpl, Annotation> ownedAnnotation;
	public static volatile ListAttribute<LiteralRationalImpl, Feature> ownedFeature;
	public static volatile SingularAttribute<LiteralRationalImpl, String> qualifiedName;
	public static volatile ListAttribute<LiteralRationalImpl, Documentation> documentation;
	public static volatile ListAttribute<LiteralRationalImpl, Type> intersectingType;
	public static volatile ListAttribute<LiteralRationalImpl, Feature> endFeature;
	public static volatile ListAttribute<LiteralRationalImpl, Feature> directedFeature;
	public static volatile SingularAttribute<LiteralRationalImpl, Boolean> isEnd;
	public static volatile ListAttribute<LiteralRationalImpl, Type> featuringType;
	public static volatile ListAttribute<LiteralRationalImpl, Feature> input;
	public static volatile SingularAttribute<LiteralRationalImpl, Boolean> isConstant;
	public static volatile SingularAttribute<LiteralRationalImpl, Boolean> isComposite;
	public static volatile SingularAttribute<LiteralRationalImpl, String> name;
	public static volatile SingularAttribute<LiteralRationalImpl, String> shortName;
	public static volatile ListAttribute<LiteralRationalImpl, Element> ownedMember;
	public static volatile ListAttribute<LiteralRationalImpl, Membership> ownedMembership;
	public static volatile CollectionAttribute<LiteralRationalImpl, Disjoining> ownedDisjoining;
	public static volatile ListAttribute<LiteralRationalImpl, Membership> membership;
	public static volatile SingularAttribute<LiteralRationalImpl, Boolean> isPortion;
	public static volatile SingularAttribute<LiteralRationalImpl, Boolean> isImpliedIncluded;
	public static volatile ListAttribute<LiteralRationalImpl, FeatureChaining> ownedFeatureChaining;
	public static volatile ListAttribute<LiteralRationalImpl, FeatureTyping> ownedTyping;
	public static volatile ListAttribute<LiteralRationalImpl, Feature> feature;
	public static volatile ListAttribute<LiteralRationalImpl, Membership> inheritedMembership;
	public static volatile ListAttribute<LiteralRationalImpl, Feature> parameter;
	public static volatile ListAttribute<LiteralRationalImpl, Element> member;
	public static volatile ListAttribute<LiteralRationalImpl, Feature> ownedEndFeature;
	public static volatile SingularAttribute<LiteralRationalImpl, String> declaredName;
	public static volatile ListAttribute<LiteralRationalImpl, FeatureMembership> ownedFeatureMembership;
	public static volatile ListAttribute<LiteralRationalImpl, Feature> inheritedFeature;
	public static volatile ListAttribute<LiteralRationalImpl, Behavior> behavior;
	public static volatile SingularAttribute<LiteralRationalImpl, Double> value;
	public static volatile SingularAttribute<LiteralRationalImpl, FeatureDirectionKind> direction;
	public static volatile ListAttribute<LiteralRationalImpl, Membership> importedMembership;
	public static volatile ListAttribute<LiteralRationalImpl, Element> ownedElement;
	public static volatile SingularAttribute<LiteralRationalImpl, Boolean> isModelLevelEvaluable;
	public static volatile ListAttribute<LiteralRationalImpl, String> aliasIds;
	public static volatile ListAttribute<LiteralRationalImpl, Relationship> ownedRelationship;
	public static volatile ListAttribute<LiteralRationalImpl, FeatureMembership> featureMembership;
	public static volatile ListAttribute<LiteralRationalImpl, Import> ownedImport;
	public static volatile SingularAttribute<LiteralRationalImpl, Boolean> isAbstract;
	public static volatile ListAttribute<LiteralRationalImpl, Type> differencingType;
	public static volatile ListAttribute<LiteralRationalImpl, Unioning> ownedUnioning;
	public static volatile ListAttribute<LiteralRationalImpl, Type> unioningType;
	public static volatile ListAttribute<LiteralRationalImpl, Specialization> ownedSpecialization;
	public static volatile SingularAttribute<LiteralRationalImpl, Boolean> isVariable;
	public static volatile ListAttribute<LiteralRationalImpl, Intersecting> ownedIntersecting;
	public static volatile CollectionAttribute<LiteralRationalImpl, FeatureInverting> ownedFeatureInverting;

	public static final String DECLARED_SHORT_NAME = "declaredShortName";
	public static final String CHAINING_FEATURE = "chainingFeature";
	public static final String OWNED_TYPE_FEATURING = "ownedTypeFeaturing";
	public static final String IS_CONJUGATED = "isConjugated";
	public static final String IS_UNIQUE = "isUnique";
	public static final String OWNED_SUBSETTING = "ownedSubsetting";
	public static final String TYPE = "type";
	public static final String OUTPUT = "output";
	public static final String IS_SUFFICIENT = "isSufficient";
	public static final String OWNED_DIFFERENCING = "ownedDifferencing";
	public static final String IS_ORDERED = "isOrdered";
	public static final String OWNED_REDEFINITION = "ownedRedefinition";
	public static final String ELEMENT_ID = "elementId";
	public static final String TEXTUAL_REPRESENTATION = "textualRepresentation";
	public static final String IS_LIBRARY_ELEMENT = "isLibraryElement";
	public static final String IS_DERIVED = "isDerived";
	public static final String OWNED_ANNOTATION = "ownedAnnotation";
	public static final String OWNED_FEATURE = "ownedFeature";
	public static final String QUALIFIED_NAME = "qualifiedName";
	public static final String DOCUMENTATION = "documentation";
	public static final String INTERSECTING_TYPE = "intersectingType";
	public static final String END_FEATURE = "endFeature";
	public static final String DIRECTED_FEATURE = "directedFeature";
	public static final String IS_END = "isEnd";
	public static final String FEATURING_TYPE = "featuringType";
	public static final String INPUT = "input";
	public static final String IS_CONSTANT = "isConstant";
	public static final String IS_COMPOSITE = "isComposite";
	public static final String NAME = "name";
	public static final String SHORT_NAME = "shortName";
	public static final String OWNED_MEMBER = "ownedMember";
	public static final String OWNED_MEMBERSHIP = "ownedMembership";
	public static final String OWNED_DISJOINING = "ownedDisjoining";
	public static final String MEMBERSHIP = "membership";
	public static final String IS_PORTION = "isPortion";
	public static final String IS_IMPLIED_INCLUDED = "isImpliedIncluded";
	public static final String OWNED_FEATURE_CHAINING = "ownedFeatureChaining";
	public static final String OWNED_TYPING = "ownedTyping";
	public static final String FEATURE = "feature";
	public static final String INHERITED_MEMBERSHIP = "inheritedMembership";
	public static final String PARAMETER = "parameter";
	public static final String MEMBER = "member";
	public static final String OWNED_END_FEATURE = "ownedEndFeature";
	public static final String DECLARED_NAME = "declaredName";
	public static final String OWNED_FEATURE_MEMBERSHIP = "ownedFeatureMembership";
	public static final String INHERITED_FEATURE = "inheritedFeature";
	public static final String BEHAVIOR = "behavior";
	public static final String VALUE = "value";
	public static final String DIRECTION = "direction";
	public static final String IMPORTED_MEMBERSHIP = "importedMembership";
	public static final String OWNED_ELEMENT = "ownedElement";
	public static final String IS_MODEL_LEVEL_EVALUABLE = "isModelLevelEvaluable";
	public static final String ALIAS_IDS = "aliasIds";
	public static final String OWNED_RELATIONSHIP = "ownedRelationship";
	public static final String FEATURE_MEMBERSHIP = "featureMembership";
	public static final String OWNED_IMPORT = "ownedImport";
	public static final String IS_ABSTRACT = "isAbstract";
	public static final String DIFFERENCING_TYPE = "differencingType";
	public static final String OWNED_UNIONING = "ownedUnioning";
	public static final String UNIONING_TYPE = "unioningType";
	public static final String OWNED_SPECIALIZATION = "ownedSpecialization";
	public static final String IS_VARIABLE = "isVariable";
	public static final String OWNED_INTERSECTING = "ownedIntersecting";
	public static final String OWNED_FEATURE_INVERTING = "ownedFeatureInverting";

}

