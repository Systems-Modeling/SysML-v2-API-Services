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
@StaticMetamodel(LifeClassImpl.class)
public abstract class LifeClassImpl_ extends org.omg.sysml.metamodel.impl.MofObjectImpl_ {

	public static volatile ListAttribute<LifeClassImpl, Generalization> ownedGeneralization;
	public static volatile SingularAttribute<LifeClassImpl, Boolean> isConjugated;
	public static volatile ListAttribute<LifeClassImpl, Membership> membership;
	public static volatile CollectionAttribute<LifeClassImpl, Superclassing> ownedSuperclassing;
	public static volatile CollectionAttribute<LifeClassImpl, Feature> output;
	public static volatile CollectionAttribute<LifeClassImpl, Feature> feature;
	public static volatile ListAttribute<LifeClassImpl, Membership> inheritedMembership;
	public static volatile ListAttribute<LifeClassImpl, Element> member;
	public static volatile CollectionAttribute<LifeClassImpl, Feature> ownedEndFeature;
	public static volatile ListAttribute<LifeClassImpl, FeatureMembership> ownedFeatureMembership;
	public static volatile CollectionAttribute<LifeClassImpl, Feature> inheritedFeature;
	public static volatile SingularAttribute<LifeClassImpl, Boolean> isSufficient;
	public static volatile SingularAttribute<LifeClassImpl, UUID> identifier;
	public static volatile ListAttribute<LifeClassImpl, Membership> importedMembership;
	public static volatile CollectionAttribute<LifeClassImpl, Element> ownedElement;
	public static volatile CollectionAttribute<LifeClassImpl, Feature> ownedFeature;
	public static volatile CollectionAttribute<LifeClassImpl, Relationship> ownedRelationship;
	public static volatile ListAttribute<LifeClassImpl, FeatureMembership> featureMembership;
	public static volatile CollectionAttribute<LifeClassImpl, Feature> endFeature;
	public static volatile ListAttribute<LifeClassImpl, Import> ownedImport;
	public static volatile SingularAttribute<LifeClassImpl, Boolean> isAbstract;
	public static volatile CollectionAttribute<LifeClassImpl, Feature> input;
	public static volatile SingularAttribute<LifeClassImpl, String> name;
	public static volatile ListAttribute<LifeClassImpl, Element> ownedMember;
	public static volatile ListAttribute<LifeClassImpl, Membership> ownedMembership;

	public static final String OWNED_GENERALIZATION = "ownedGeneralization";
	public static final String IS_CONJUGATED = "isConjugated";
	public static final String MEMBERSHIP = "membership";
	public static final String OWNED_SUPERCLASSING = "ownedSuperclassing";
	public static final String OUTPUT = "output";
	public static final String FEATURE = "feature";
	public static final String INHERITED_MEMBERSHIP = "inheritedMembership";
	public static final String MEMBER = "member";
	public static final String OWNED_END_FEATURE = "ownedEndFeature";
	public static final String OWNED_FEATURE_MEMBERSHIP = "ownedFeatureMembership";
	public static final String INHERITED_FEATURE = "inheritedFeature";
	public static final String IS_SUFFICIENT = "isSufficient";
	public static final String IDENTIFIER = "identifier";
	public static final String IMPORTED_MEMBERSHIP = "importedMembership";
	public static final String OWNED_ELEMENT = "ownedElement";
	public static final String OWNED_FEATURE = "ownedFeature";
	public static final String OWNED_RELATIONSHIP = "ownedRelationship";
	public static final String FEATURE_MEMBERSHIP = "featureMembership";
	public static final String END_FEATURE = "endFeature";
	public static final String OWNED_IMPORT = "ownedImport";
	public static final String IS_ABSTRACT = "isAbstract";
	public static final String INPUT = "input";
	public static final String NAME = "name";
	public static final String OWNED_MEMBER = "ownedMember";
	public static final String OWNED_MEMBERSHIP = "ownedMembership";

}

