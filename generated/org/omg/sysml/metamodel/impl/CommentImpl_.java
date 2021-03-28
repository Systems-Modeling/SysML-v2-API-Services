package org.omg.sysml.metamodel.impl;

import java.util.UUID;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.omg.sysml.metamodel.Annotation;
import org.omg.sysml.metamodel.Comment;
import org.omg.sysml.metamodel.Documentation;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.Relationship;
import org.omg.sysml.metamodel.TextualRepresentation;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CommentImpl.class)
public abstract class CommentImpl_ extends org.omg.sysml.metamodel.impl.MofObjectImpl_ {

	public static volatile ListAttribute<CommentImpl, Annotation> annotation;
	public static volatile SingularAttribute<CommentImpl, UUID> identifier;
	public static volatile ListAttribute<CommentImpl, Annotation> ownedAnnotation;
	public static volatile ListAttribute<CommentImpl, Element> ownedElement;
	public static volatile ListAttribute<CommentImpl, Relationship> ownedRelationship;
	public static volatile SingularAttribute<CommentImpl, String> qualifiedName;
	public static volatile ListAttribute<CommentImpl, Documentation> documentation;
	public static volatile ListAttribute<CommentImpl, Element> annotatedElement;
	public static volatile SingularAttribute<CommentImpl, String> body;
	public static volatile CollectionAttribute<CommentImpl, TextualRepresentation> ownedTextualRepresentation;
	public static volatile SingularAttribute<CommentImpl, String> humanId;
	public static volatile ListAttribute<CommentImpl, String> aliasId;
	public static volatile SingularAttribute<CommentImpl, String> name;
	public static volatile ListAttribute<CommentImpl, Comment> documentationComment;

	public static final String ANNOTATION = "annotation";
	public static final String IDENTIFIER = "identifier";
	public static final String OWNED_ANNOTATION = "ownedAnnotation";
	public static final String OWNED_ELEMENT = "ownedElement";
	public static final String OWNED_RELATIONSHIP = "ownedRelationship";
	public static final String QUALIFIED_NAME = "qualifiedName";
	public static final String DOCUMENTATION = "documentation";
	public static final String ANNOTATED_ELEMENT = "annotatedElement";
	public static final String BODY = "body";
	public static final String OWNED_TEXTUAL_REPRESENTATION = "ownedTextualRepresentation";
	public static final String HUMAN_ID = "humanId";
	public static final String ALIAS_ID = "aliasId";
	public static final String NAME = "name";
	public static final String DOCUMENTATION_COMMENT = "documentationComment";

}

