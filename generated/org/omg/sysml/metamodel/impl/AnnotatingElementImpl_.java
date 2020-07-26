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
@StaticMetamodel(AnnotatingElementImpl.class)
public abstract class AnnotatingElementImpl_ extends org.omg.sysml.metamodel.impl.MofObjectImpl_ {

	public static volatile CollectionAttribute<AnnotatingElementImpl, Annotation> annotation;
	public static volatile SingularAttribute<AnnotatingElementImpl, UUID> identifier;
	public static volatile CollectionAttribute<AnnotatingElementImpl, TextualRepresentation> ownedTextualRepresentation;
	public static volatile CollectionAttribute<AnnotatingElementImpl, Annotation> ownedAnnotation;
	public static volatile CollectionAttribute<AnnotatingElementImpl, Element> ownedElement;
	public static volatile SingularAttribute<AnnotatingElementImpl, String> humanId;
	public static volatile CollectionAttribute<AnnotatingElementImpl, String> aliasId;
	public static volatile ListAttribute<AnnotatingElementImpl, Relationship> ownedRelationship;
	public static volatile CollectionAttribute<AnnotatingElementImpl, Documentation> documentation;
	public static volatile SingularAttribute<AnnotatingElementImpl, String> name;
	public static volatile CollectionAttribute<AnnotatingElementImpl, Element> annotatedElement;
	public static volatile CollectionAttribute<AnnotatingElementImpl, Comment> documentationComment;

	public static final String ANNOTATION = "annotation";
	public static final String IDENTIFIER = "identifier";
	public static final String OWNED_TEXTUAL_REPRESENTATION = "ownedTextualRepresentation";
	public static final String OWNED_ANNOTATION = "ownedAnnotation";
	public static final String OWNED_ELEMENT = "ownedElement";
	public static final String HUMAN_ID = "humanId";
	public static final String ALIAS_ID = "aliasId";
	public static final String OWNED_RELATIONSHIP = "ownedRelationship";
	public static final String DOCUMENTATION = "documentation";
	public static final String NAME = "name";
	public static final String ANNOTATED_ELEMENT = "annotatedElement";
	public static final String DOCUMENTATION_COMMENT = "documentationComment";

}

