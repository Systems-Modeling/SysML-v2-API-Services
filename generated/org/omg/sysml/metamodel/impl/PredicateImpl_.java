package org.omg.sysml.metamodel.impl;

import java.util.UUID;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.Expression;
import org.omg.sysml.metamodel.Feature;
import org.omg.sysml.metamodel.FeatureMembership;
import org.omg.sysml.metamodel.Generalization;
import org.omg.sysml.metamodel.Import;
import org.omg.sysml.metamodel.Membership;
import org.omg.sysml.metamodel.Parameter;
import org.omg.sysml.metamodel.Relationship;
import org.omg.sysml.metamodel.Step;
import org.omg.sysml.metamodel.Superclassing;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PredicateImpl.class)
public abstract class PredicateImpl_ extends org.omg.sysml.metamodel.impl.MofObjectImpl_ {

	public static volatile ListAttribute<PredicateImpl, Generalization> ownedGeneralization;
	public static volatile ListAttribute<PredicateImpl, Membership> importedMembership;
	public static volatile SingularAttribute<PredicateImpl, UUID> identifier;
	public static volatile CollectionAttribute<PredicateImpl, Element> ownedElement;
	public static volatile CollectionAttribute<PredicateImpl, Expression> expression;
	public static volatile CollectionAttribute<PredicateImpl, Feature> ownedFeature;
	public static volatile CollectionAttribute<PredicateImpl, Relationship> ownedRelationship;
	public static volatile ListAttribute<PredicateImpl, Import> ownedImport;
	public static volatile CollectionAttribute<PredicateImpl, Superclassing> ownedSuperclassing;
	public static volatile ListAttribute<PredicateImpl, Membership> membership;
	public static volatile SingularAttribute<PredicateImpl, Boolean> isAbstract;
	public static volatile CollectionAttribute<PredicateImpl, Feature> involvesFeature;
	public static volatile CollectionAttribute<PredicateImpl, Feature> output;
	public static volatile CollectionAttribute<PredicateImpl, Feature> input;
	public static volatile CollectionAttribute<PredicateImpl, Feature> feature;
	public static volatile CollectionAttribute<PredicateImpl, Parameter> parameter;
	public static volatile ListAttribute<PredicateImpl, Membership> inheritedMembership;
	public static volatile SingularAttribute<PredicateImpl, String> name;
	public static volatile ListAttribute<PredicateImpl, Element> member;
	public static volatile ListAttribute<PredicateImpl, FeatureMembership> ownedFeatureMembership;
	public static volatile CollectionAttribute<PredicateImpl, Step> step;
	public static volatile ListAttribute<PredicateImpl, Element> ownedMember;
	public static volatile ListAttribute<PredicateImpl, Membership> ownedMembership;

	public static final String OWNED_GENERALIZATION = "ownedGeneralization";
	public static final String IMPORTED_MEMBERSHIP = "importedMembership";
	public static final String IDENTIFIER = "identifier";
	public static final String OWNED_ELEMENT = "ownedElement";
	public static final String EXPRESSION = "expression";
	public static final String OWNED_FEATURE = "ownedFeature";
	public static final String OWNED_RELATIONSHIP = "ownedRelationship";
	public static final String OWNED_IMPORT = "ownedImport";
	public static final String OWNED_SUPERCLASSING = "ownedSuperclassing";
	public static final String MEMBERSHIP = "membership";
	public static final String IS_ABSTRACT = "isAbstract";
	public static final String INVOLVES_FEATURE = "involvesFeature";
	public static final String OUTPUT = "output";
	public static final String INPUT = "input";
	public static final String FEATURE = "feature";
	public static final String PARAMETER = "parameter";
	public static final String INHERITED_MEMBERSHIP = "inheritedMembership";
	public static final String NAME = "name";
	public static final String MEMBER = "member";
	public static final String OWNED_FEATURE_MEMBERSHIP = "ownedFeatureMembership";
	public static final String STEP = "step";
	public static final String OWNED_MEMBER = "ownedMember";
	public static final String OWNED_MEMBERSHIP = "ownedMembership";

}

