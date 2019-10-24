package org.omg.sysml.metamodel.impl;

import java.util.UUID;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.omg.sysml.metamodel.Behavior;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.Feature;
import org.omg.sysml.metamodel.FeatureMembership;
import org.omg.sysml.metamodel.FeatureTyping;
import org.omg.sysml.metamodel.Generalization;
import org.omg.sysml.metamodel.Import;
import org.omg.sysml.metamodel.Membership;
import org.omg.sysml.metamodel.Redefinition;
import org.omg.sysml.metamodel.Relationship;
import org.omg.sysml.metamodel.Subsetting;
import org.omg.sysml.metamodel.Type;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(StepImpl.class)
public abstract class StepImpl_ extends org.omg.sysml.metamodel.impl.MofObjectImpl_ {

	public static volatile ListAttribute<StepImpl, Generalization> ownedGeneralization;
	public static volatile CollectionAttribute<StepImpl, Subsetting> ownedSubsetting;
	public static volatile SingularAttribute<StepImpl, Boolean> isUnique;
	public static volatile ListAttribute<StepImpl, Membership> membership;
	public static volatile CollectionAttribute<StepImpl, Type> type;
	public static volatile SingularAttribute<StepImpl, Boolean> isNonunique;
	public static volatile CollectionAttribute<StepImpl, Feature> output;
	public static volatile CollectionAttribute<StepImpl, Feature> feature;
	public static volatile ListAttribute<StepImpl, Membership> inheritedMembership;
	public static volatile CollectionAttribute<StepImpl, Feature> ownedEndFeature;
	public static volatile ListAttribute<StepImpl, Element> member;
	public static volatile ListAttribute<StepImpl, FeatureMembership> ownedFeatureMembership;
	public static volatile SingularAttribute<StepImpl, Boolean> isSufficient;
	public static volatile CollectionAttribute<StepImpl, Behavior> behavior;
	public static volatile SingularAttribute<StepImpl, Boolean> isOrdered;
	public static volatile CollectionAttribute<StepImpl, Redefinition> ownedRedefinition;
	public static volatile ListAttribute<StepImpl, Membership> importedMembership;
	public static volatile SingularAttribute<StepImpl, UUID> identifier;
	public static volatile CollectionAttribute<StepImpl, Element> ownedElement;
	public static volatile CollectionAttribute<StepImpl, Feature> ownedFeature;
	public static volatile CollectionAttribute<StepImpl, Relationship> ownedRelationship;
	public static volatile CollectionAttribute<StepImpl, Feature> endFeature;
	public static volatile CollectionAttribute<StepImpl, Type> referencedType;
	public static volatile ListAttribute<StepImpl, Import> ownedImport;
	public static volatile SingularAttribute<StepImpl, Boolean> isAbstract;
	public static volatile SingularAttribute<StepImpl, Boolean> isEnd;
	public static volatile CollectionAttribute<StepImpl, Feature> input;
	public static volatile SingularAttribute<StepImpl, Boolean> isComposite;
	public static volatile CollectionAttribute<StepImpl, Type> ownedType;
	public static volatile SingularAttribute<StepImpl, String> name;
	public static volatile CollectionAttribute<StepImpl, FeatureTyping> typing;
	public static volatile ListAttribute<StepImpl, Element> ownedMember;
	public static volatile ListAttribute<StepImpl, Membership> ownedMembership;

	public static final String OWNED_GENERALIZATION = "ownedGeneralization";
	public static final String OWNED_SUBSETTING = "ownedSubsetting";
	public static final String IS_UNIQUE = "isUnique";
	public static final String MEMBERSHIP = "membership";
	public static final String TYPE = "type";
	public static final String IS_NONUNIQUE = "isNonunique";
	public static final String OUTPUT = "output";
	public static final String FEATURE = "feature";
	public static final String INHERITED_MEMBERSHIP = "inheritedMembership";
	public static final String OWNED_END_FEATURE = "ownedEndFeature";
	public static final String MEMBER = "member";
	public static final String OWNED_FEATURE_MEMBERSHIP = "ownedFeatureMembership";
	public static final String IS_SUFFICIENT = "isSufficient";
	public static final String BEHAVIOR = "behavior";
	public static final String IS_ORDERED = "isOrdered";
	public static final String OWNED_REDEFINITION = "ownedRedefinition";
	public static final String IMPORTED_MEMBERSHIP = "importedMembership";
	public static final String IDENTIFIER = "identifier";
	public static final String OWNED_ELEMENT = "ownedElement";
	public static final String OWNED_FEATURE = "ownedFeature";
	public static final String OWNED_RELATIONSHIP = "ownedRelationship";
	public static final String END_FEATURE = "endFeature";
	public static final String REFERENCED_TYPE = "referencedType";
	public static final String OWNED_IMPORT = "ownedImport";
	public static final String IS_ABSTRACT = "isAbstract";
	public static final String IS_END = "isEnd";
	public static final String INPUT = "input";
	public static final String IS_COMPOSITE = "isComposite";
	public static final String OWNED_TYPE = "ownedType";
	public static final String NAME = "name";
	public static final String TYPING = "typing";
	public static final String OWNED_MEMBER = "ownedMember";
	public static final String OWNED_MEMBERSHIP = "ownedMembership";

}

