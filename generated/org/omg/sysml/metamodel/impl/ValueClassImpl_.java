package org.omg.sysml.metamodel.impl;

import java.util.UUID;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.Feature;
import org.omg.sysml.metamodel.FeatureMembership;
import org.omg.sysml.metamodel.Generalization;
import org.omg.sysml.metamodel.Import;
import org.omg.sysml.metamodel.Membership;
import org.omg.sysml.metamodel.Relationship;
import org.omg.sysml.metamodel.Superclassing;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ValueClassImpl.class)
public abstract class ValueClassImpl_ extends org.omg.sysml.metamodel.impl.MofObjectImpl_ {

	public static volatile SingularAttribute<ValueClassImpl, UUID> identifier;
	public static volatile ListAttribute<ValueClassImpl, Generalization> ownedGeneralization;
	public static volatile ListAttribute<ValueClassImpl, Membership> importedMembership;
	public static volatile CollectionAttribute<ValueClassImpl, Element> ownedElement;
	public static volatile CollectionAttribute<ValueClassImpl, Feature> ownedFeature;
	public static volatile CollectionAttribute<ValueClassImpl, Relationship> ownedRelationship;
	public static volatile ListAttribute<ValueClassImpl, Import> ownedImport;
	public static volatile CollectionAttribute<ValueClassImpl, Superclassing> ownedSuperclassing;
	public static volatile ListAttribute<ValueClassImpl, Membership> membership;
	public static volatile SingularAttribute<ValueClassImpl, Boolean> isAbstract;
	public static volatile CollectionAttribute<ValueClassImpl, Feature> output;
	public static volatile CollectionAttribute<ValueClassImpl, Feature> input;
	public static volatile CollectionAttribute<ValueClassImpl, Feature> feature;
	public static volatile ListAttribute<ValueClassImpl, Membership> inheritedMembership;
	public static volatile SingularAttribute<ValueClassImpl, String> name;
	public static volatile ListAttribute<ValueClassImpl, Element> member;
	public static volatile ListAttribute<ValueClassImpl, FeatureMembership> ownedFeatureMembership;
	public static volatile ListAttribute<ValueClassImpl, Element> ownedMember;
	public static volatile ListAttribute<ValueClassImpl, Membership> ownedMembership;

	public static final String IDENTIFIER = "identifier";
	public static final String OWNED_GENERALIZATION = "ownedGeneralization";
	public static final String IMPORTED_MEMBERSHIP = "importedMembership";
	public static final String OWNED_ELEMENT = "ownedElement";
	public static final String OWNED_FEATURE = "ownedFeature";
	public static final String OWNED_RELATIONSHIP = "ownedRelationship";
	public static final String OWNED_IMPORT = "ownedImport";
	public static final String OWNED_SUPERCLASSING = "ownedSuperclassing";
	public static final String MEMBERSHIP = "membership";
	public static final String IS_ABSTRACT = "isAbstract";
	public static final String OUTPUT = "output";
	public static final String INPUT = "input";
	public static final String FEATURE = "feature";
	public static final String INHERITED_MEMBERSHIP = "inheritedMembership";
	public static final String NAME = "name";
	public static final String MEMBER = "member";
	public static final String OWNED_FEATURE_MEMBERSHIP = "ownedFeatureMembership";
	public static final String OWNED_MEMBER = "ownedMember";
	public static final String OWNED_MEMBERSHIP = "ownedMembership";

}

