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
@StaticMetamodel(ImportImpl.class)
public abstract class ImportImpl_ extends org.omg.sysml.metamodel.impl.MofObjectImpl_ {

	public static volatile SingularAttribute<ImportImpl, UUID> identifier;
	public static volatile CollectionAttribute<ImportImpl, Element> ownedElement;
	public static volatile SingularAttribute<ImportImpl, VisibilityKind> visibility;
	public static volatile CollectionAttribute<ImportImpl, Element> relatedElement;
	public static volatile CollectionAttribute<ImportImpl, Relationship> ownedRelationship;
	public static volatile SingularAttribute<ImportImpl, String> name;
	public static volatile CollectionAttribute<ImportImpl, Element> ownedRelatedElement;
	public static volatile CollectionAttribute<ImportImpl, Element> source;
	public static volatile CollectionAttribute<ImportImpl, Element> target;

	public static final String IDENTIFIER = "identifier";
	public static final String OWNED_ELEMENT = "ownedElement";
	public static final String VISIBILITY = "visibility";
	public static final String RELATED_ELEMENT = "relatedElement";
	public static final String OWNED_RELATIONSHIP = "ownedRelationship";
	public static final String NAME = "name";
	public static final String OWNED_RELATED_ELEMENT = "ownedRelatedElement";
	public static final String SOURCE = "source";
	public static final String TARGET = "target";

}

