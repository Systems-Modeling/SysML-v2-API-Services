package org.omg.sysml.metamodel.impl;

import java.util.UUID;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.Relationship;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MultiplicityImpl.class)
public abstract class MultiplicityImpl_ extends org.omg.sysml.metamodel.impl.MofObjectImpl_ {

	public static volatile SingularAttribute<MultiplicityImpl, UUID> identifier;
	public static volatile CollectionAttribute<MultiplicityImpl, Relationship> ownedRelationship;
	public static volatile CollectionAttribute<MultiplicityImpl, Element> ownedRelatedElement;
	public static volatile CollectionAttribute<MultiplicityImpl, Element> source;
	public static volatile CollectionAttribute<MultiplicityImpl, Element> target;

	public static final String IDENTIFIER = "identifier";
	public static final String OWNED_RELATIONSHIP = "ownedRelationship";
	public static final String OWNED_RELATED_ELEMENT = "ownedRelatedElement";
	public static final String SOURCE = "source";
	public static final String TARGET = "target";

}

