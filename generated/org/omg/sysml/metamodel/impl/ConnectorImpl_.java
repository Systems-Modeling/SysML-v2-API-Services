package org.omg.sysml.metamodel.impl;

import java.util.UUID;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.FeatureTyping;
import org.omg.sysml.metamodel.Relationship;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ConnectorImpl.class)
public abstract class ConnectorImpl_ extends org.omg.sysml.metamodel.impl.MofObjectImpl_ {

	public static volatile SingularAttribute<ConnectorImpl, UUID> identifier;
	public static volatile SingularAttribute<ConnectorImpl, Boolean> isDirected;
	public static volatile CollectionAttribute<ConnectorImpl, Relationship> ownedRelationship;
	public static volatile SingularAttribute<ConnectorImpl, Boolean> isUnique;
	public static volatile CollectionAttribute<ConnectorImpl, FeatureTyping> typing;
	public static volatile CollectionAttribute<ConnectorImpl, Element> ownedRelatedElement;
	public static volatile CollectionAttribute<ConnectorImpl, Element> source;
	public static volatile SingularAttribute<ConnectorImpl, Boolean> isAbstract;
	public static volatile SingularAttribute<ConnectorImpl, Boolean> isOrdered;
	public static volatile CollectionAttribute<ConnectorImpl, Element> target;

	public static final String IDENTIFIER = "identifier";
	public static final String IS_DIRECTED = "isDirected";
	public static final String OWNED_RELATIONSHIP = "ownedRelationship";
	public static final String IS_UNIQUE = "isUnique";
	public static final String TYPING = "typing";
	public static final String OWNED_RELATED_ELEMENT = "ownedRelatedElement";
	public static final String SOURCE = "source";
	public static final String IS_ABSTRACT = "isAbstract";
	public static final String IS_ORDERED = "isOrdered";
	public static final String TARGET = "target";

}

