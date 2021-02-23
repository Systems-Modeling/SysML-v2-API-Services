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
@StaticMetamodel(DocumentationImpl.class)
public abstract class DocumentationImpl_ extends org.omg.sysml.metamodel.impl.MofObjectImpl_ {

	public static volatile SingularAttribute<DocumentationImpl, UUID> identifier;
	public static volatile CollectionAttribute<DocumentationImpl, Annotation> ownedAnnotation;
	public static volatile CollectionAttribute<DocumentationImpl, Element> ownedElement;
	public static volatile ListAttribute<DocumentationImpl, Relationship> ownedRelationship;
	public static volatile SingularAttribute<DocumentationImpl, String> qualifiedName;
	public static volatile CollectionAttribute<DocumentationImpl, Documentation> documentation;
	public static volatile ListAttribute<DocumentationImpl, Element> ownedRelatedElement;
	public static volatile ListAttribute<DocumentationImpl, Element> source;
	public static volatile ListAttribute<DocumentationImpl, Element> target;
	public static volatile CollectionAttribute<DocumentationImpl, TextualRepresentation> ownedTextualRepresentation;
	public static volatile SingularAttribute<DocumentationImpl, String> humanId;
	public static volatile CollectionAttribute<DocumentationImpl, String> aliasId;
	public static volatile ListAttribute<DocumentationImpl, Element> relatedElement;
	public static volatile SingularAttribute<DocumentationImpl, String> name;
	public static volatile CollectionAttribute<DocumentationImpl, Comment> documentationComment;

	public static final String IDENTIFIER = "identifier";
	public static final String OWNED_ANNOTATION = "ownedAnnotation";
	public static final String OWNED_ELEMENT = "ownedElement";
	public static final String OWNED_RELATIONSHIP = "ownedRelationship";
	public static final String QUALIFIED_NAME = "qualifiedName";
	public static final String DOCUMENTATION = "documentation";
	public static final String OWNED_RELATED_ELEMENT = "ownedRelatedElement";
	public static final String SOURCE = "source";
	public static final String TARGET = "target";
	public static final String OWNED_TEXTUAL_REPRESENTATION = "ownedTextualRepresentation";
	public static final String HUMAN_ID = "humanId";
	public static final String ALIAS_ID = "aliasId";
	public static final String RELATED_ELEMENT = "relatedElement";
	public static final String NAME = "name";
	public static final String DOCUMENTATION_COMMENT = "documentationComment";

}

