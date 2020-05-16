package org.omg.sysml.metamodel.impl;

import java.util.UUID;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.Relationship;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ImportConditionImpl.class)
public abstract class ImportConditionImpl_ extends org.omg.sysml.metamodel.impl.MofObjectImpl_ {

	public static volatile SingularAttribute<ImportConditionImpl, UUID> identifier;
	public static volatile CollectionAttribute<ImportConditionImpl, Element> ownedElement;
	public static volatile CollectionAttribute<ImportConditionImpl, Relationship> ownedRelationship;
	public static volatile CollectionAttribute<ImportConditionImpl, Element> relatedElement;
	public static volatile SingularAttribute<ImportConditionImpl, String> name;
	public static volatile CollectionAttribute<ImportConditionImpl, Element> ownedRelatedElement;
	public static volatile CollectionAttribute<ImportConditionImpl, Element> source;
	public static volatile CollectionAttribute<ImportConditionImpl, Element> target;

	public static final String IDENTIFIER = "identifier";
	public static final String OWNED_ELEMENT = "ownedElement";
	public static final String OWNED_RELATIONSHIP = "ownedRelationship";
	public static final String RELATED_ELEMENT = "relatedElement";
	public static final String NAME = "name";
	public static final String OWNED_RELATED_ELEMENT = "ownedRelatedElement";
	public static final String SOURCE = "source";
	public static final String TARGET = "target";

}

