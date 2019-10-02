package org.omg.sysml.metamodel.impl;

import java.util.UUID;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.Relationship;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AnnotationImpl.class)
public abstract class AnnotationImpl_ extends org.omg.sysml.metamodel.impl.MofObjectImpl_ {

	public static volatile SingularAttribute<AnnotationImpl, UUID> identifier;
	public static volatile CollectionAttribute<AnnotationImpl, Element> ownedElement;
	public static volatile CollectionAttribute<AnnotationImpl, Relationship> ownedRelationship;
	public static volatile CollectionAttribute<AnnotationImpl, Element> relatedElement;
	public static volatile SingularAttribute<AnnotationImpl, String> name;
	public static volatile CollectionAttribute<AnnotationImpl, Element> ownedRelatedElement;
	public static volatile CollectionAttribute<AnnotationImpl, Element> source;
	public static volatile CollectionAttribute<AnnotationImpl, Element> target;

	public static final String IDENTIFIER = "identifier";
	public static final String OWNED_ELEMENT = "ownedElement";
	public static final String OWNED_RELATIONSHIP = "ownedRelationship";
	public static final String RELATED_ELEMENT = "relatedElement";
	public static final String NAME = "name";
	public static final String OWNED_RELATED_ELEMENT = "ownedRelatedElement";
	public static final String SOURCE = "source";
	public static final String TARGET = "target";

}

