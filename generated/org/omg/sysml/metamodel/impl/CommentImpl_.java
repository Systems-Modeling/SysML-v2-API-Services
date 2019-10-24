package org.omg.sysml.metamodel.impl;

import java.util.UUID;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.Relationship;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CommentImpl.class)
public abstract class CommentImpl_ extends org.omg.sysml.metamodel.impl.MofObjectImpl_ {

	public static volatile SingularAttribute<CommentImpl, UUID> identifier;
	public static volatile CollectionAttribute<CommentImpl, Element> ownedElement;
	public static volatile CollectionAttribute<CommentImpl, Relationship> ownedRelationship;
	public static volatile SingularAttribute<CommentImpl, String> name;
	public static volatile SingularAttribute<CommentImpl, String> body;

	public static final String IDENTIFIER = "identifier";
	public static final String OWNED_ELEMENT = "ownedElement";
	public static final String OWNED_RELATIONSHIP = "ownedRelationship";
	public static final String NAME = "name";
	public static final String BODY = "body";

}

