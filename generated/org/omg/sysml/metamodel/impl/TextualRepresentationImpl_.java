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
@StaticMetamodel(TextualRepresentationImpl.class)
public abstract class TextualRepresentationImpl_ extends org.omg.sysml.metamodel.impl.MofObjectImpl_ {

	public static volatile CollectionAttribute<TextualRepresentationImpl, Annotation> annotation;
	public static volatile SingularAttribute<TextualRepresentationImpl, UUID> identifier;
	public static volatile CollectionAttribute<TextualRepresentationImpl, Annotation> ownedAnnotation;
	public static volatile CollectionAttribute<TextualRepresentationImpl, Element> ownedElement;
	public static volatile ListAttribute<TextualRepresentationImpl, Relationship> ownedRelationship;
	public static volatile SingularAttribute<TextualRepresentationImpl, String> qualifiedName;
	public static volatile CollectionAttribute<TextualRepresentationImpl, Documentation> documentation;
	public static volatile SingularAttribute<TextualRepresentationImpl, String> language;
	public static volatile CollectionAttribute<TextualRepresentationImpl, Element> annotatedElement;
	public static volatile SingularAttribute<TextualRepresentationImpl, String> body;
	public static volatile CollectionAttribute<TextualRepresentationImpl, TextualRepresentation> ownedTextualRepresentation;
	public static volatile SingularAttribute<TextualRepresentationImpl, String> humanId;
	public static volatile CollectionAttribute<TextualRepresentationImpl, String> aliasId;
	public static volatile SingularAttribute<TextualRepresentationImpl, String> name;
	public static volatile CollectionAttribute<TextualRepresentationImpl, Comment> documentationComment;

	public static final String ANNOTATION = "annotation";
	public static final String IDENTIFIER = "identifier";
	public static final String OWNED_ANNOTATION = "ownedAnnotation";
	public static final String OWNED_ELEMENT = "ownedElement";
	public static final String OWNED_RELATIONSHIP = "ownedRelationship";
	public static final String QUALIFIED_NAME = "qualifiedName";
	public static final String DOCUMENTATION = "documentation";
	public static final String LANGUAGE = "language";
	public static final String ANNOTATED_ELEMENT = "annotatedElement";
	public static final String BODY = "body";
	public static final String OWNED_TEXTUAL_REPRESENTATION = "ownedTextualRepresentation";
	public static final String HUMAN_ID = "humanId";
	public static final String ALIAS_ID = "aliasId";
	public static final String NAME = "name";
	public static final String DOCUMENTATION_COMMENT = "documentationComment";

}

