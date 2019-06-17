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

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CategoryImpl.class)
public abstract class CategoryImpl_ extends org.omg.sysml.metamodel.impl.MofObjectImpl_ {

	public static volatile SingularAttribute<CategoryImpl, UUID> identifier;
	public static volatile ListAttribute<CategoryImpl, Generalization> ownedGeneralization;
	public static volatile ListAttribute<CategoryImpl, Membership> importedMembership;
	public static volatile CollectionAttribute<CategoryImpl, Element> ownedElement;
	public static volatile CollectionAttribute<CategoryImpl, Feature> ownedFeature;
	public static volatile CollectionAttribute<CategoryImpl, Relationship> ownedRelationship;
	public static volatile ListAttribute<CategoryImpl, Import> ownedImport;
	public static volatile ListAttribute<CategoryImpl, Membership> membership;
	public static volatile SingularAttribute<CategoryImpl, Boolean> isAbstract;
	public static volatile CollectionAttribute<CategoryImpl, Feature> output;
	public static volatile CollectionAttribute<CategoryImpl, Feature> input;
	public static volatile CollectionAttribute<CategoryImpl, Feature> feature;
	public static volatile ListAttribute<CategoryImpl, Membership> inheritedMembership;
	public static volatile SingularAttribute<CategoryImpl, String> name;
	public static volatile ListAttribute<CategoryImpl, Element> member;
	public static volatile ListAttribute<CategoryImpl, FeatureMembership> ownedFeatureMembership;
	public static volatile ListAttribute<CategoryImpl, Element> ownedMember;
	public static volatile ListAttribute<CategoryImpl, Membership> ownedMembership;

	public static final String IDENTIFIER = "identifier";
	public static final String OWNED_GENERALIZATION = "ownedGeneralization";
	public static final String IMPORTED_MEMBERSHIP = "importedMembership";
	public static final String OWNED_ELEMENT = "ownedElement";
	public static final String OWNED_FEATURE = "ownedFeature";
	public static final String OWNED_RELATIONSHIP = "ownedRelationship";
	public static final String OWNED_IMPORT = "ownedImport";
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

