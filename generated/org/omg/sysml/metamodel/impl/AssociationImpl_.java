package org.omg.sysml.metamodel.impl;

import java.util.UUID;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.EndFeatureMembership;
import org.omg.sysml.metamodel.Relationship;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AssociationImpl.class)
public abstract class AssociationImpl_ extends org.omg.sysml.metamodel.impl.MofObjectImpl_ {

	public static volatile ListAttribute<AssociationImpl, EndFeatureMembership> ownedEndFeatureMembership;
	public static volatile SingularAttribute<AssociationImpl, UUID> identifier;
	public static volatile CollectionAttribute<AssociationImpl, Relationship> ownedRelationship;
	public static volatile CollectionAttribute<AssociationImpl, Element> ownedRelatedElement;
	public static volatile CollectionAttribute<AssociationImpl, Element> source;
	public static volatile SingularAttribute<AssociationImpl, Boolean> isAbstract;
	public static volatile CollectionAttribute<AssociationImpl, Element> target;

	public static final String OWNED_END_FEATURE_MEMBERSHIP = "ownedEndFeatureMembership";
	public static final String IDENTIFIER = "identifier";
	public static final String OWNED_RELATIONSHIP = "ownedRelationship";
	public static final String OWNED_RELATED_ELEMENT = "ownedRelatedElement";
	public static final String SOURCE = "source";
	public static final String IS_ABSTRACT = "isAbstract";
	public static final String TARGET = "target";

}

