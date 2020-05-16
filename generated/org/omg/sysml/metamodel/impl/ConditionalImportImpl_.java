package org.omg.sysml.metamodel.impl;

import java.util.UUID;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.Relationship;
import org.omg.sysml.metamodel.VisibilityKind;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ConditionalImportImpl.class)
public abstract class ConditionalImportImpl_ extends org.omg.sysml.metamodel.impl.MofObjectImpl_ {

	public static volatile SingularAttribute<ConditionalImportImpl, UUID> identifier;
	public static volatile CollectionAttribute<ConditionalImportImpl, Element> ownedElement;
	public static volatile SingularAttribute<ConditionalImportImpl, VisibilityKind> visibility;
	public static volatile CollectionAttribute<ConditionalImportImpl, Relationship> ownedRelationship;
	public static volatile CollectionAttribute<ConditionalImportImpl, Element> relatedElement;
	public static volatile SingularAttribute<ConditionalImportImpl, String> name;
	public static volatile CollectionAttribute<ConditionalImportImpl, Element> ownedRelatedElement;
	public static volatile CollectionAttribute<ConditionalImportImpl, Element> source;
	public static volatile CollectionAttribute<ConditionalImportImpl, Element> target;

	public static final String IDENTIFIER = "identifier";
	public static final String OWNED_ELEMENT = "ownedElement";
	public static final String VISIBILITY = "visibility";
	public static final String OWNED_RELATIONSHIP = "ownedRelationship";
	public static final String RELATED_ELEMENT = "relatedElement";
	public static final String NAME = "name";
	public static final String OWNED_RELATED_ELEMENT = "ownedRelatedElement";
	public static final String SOURCE = "source";
	public static final String TARGET = "target";

}

