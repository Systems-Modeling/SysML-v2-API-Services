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
@StaticMetamodel(BindingConnectorImpl.class)
public abstract class BindingConnectorImpl_ extends org.omg.sysml.metamodel.impl.MofObjectImpl_ {

	public static volatile SingularAttribute<BindingConnectorImpl, UUID> identifier;
	public static volatile SingularAttribute<BindingConnectorImpl, Boolean> isDirected;
	public static volatile CollectionAttribute<BindingConnectorImpl, Relationship> ownedRelationship;
	public static volatile SingularAttribute<BindingConnectorImpl, Boolean> isUnique;
	public static volatile CollectionAttribute<BindingConnectorImpl, FeatureTyping> typing;
	public static volatile CollectionAttribute<BindingConnectorImpl, Element> ownedRelatedElement;
	public static volatile CollectionAttribute<BindingConnectorImpl, Element> source;
	public static volatile SingularAttribute<BindingConnectorImpl, Boolean> isAbstract;
	public static volatile SingularAttribute<BindingConnectorImpl, Boolean> isOrdered;
	public static volatile CollectionAttribute<BindingConnectorImpl, Element> target;

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

