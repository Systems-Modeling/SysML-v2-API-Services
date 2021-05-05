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
@StaticMetamodel(SuperclassingImpl.class)
public abstract class SuperclassingImpl_ extends org.omg.sysml.metamodel.impl.MofObjectImpl_ {

	public static volatile SingularAttribute<SuperclassingImpl, UUID> identifier;
	public static volatile ListAttribute<SuperclassingImpl, Annotation> ownedAnnotation;
	public static volatile ListAttribute<SuperclassingImpl, Element> ownedElement;
	public static volatile ListAttribute<SuperclassingImpl, Relationship> ownedRelationship;
	public static volatile SingularAttribute<SuperclassingImpl, String> qualifiedName;
	public static volatile ListAttribute<SuperclassingImpl, Documentation> documentation;
	public static volatile ListAttribute<SuperclassingImpl, Element> ownedRelatedElement;
	public static volatile ListAttribute<SuperclassingImpl, Element> source;
	public static volatile ListAttribute<SuperclassingImpl, Element> target;
	public static volatile CollectionAttribute<SuperclassingImpl, TextualRepresentation> ownedTextualRepresentation;
	public static volatile SingularAttribute<SuperclassingImpl, String> humanId;
	public static volatile ListAttribute<SuperclassingImpl, String> aliasId;
	public static volatile ListAttribute<SuperclassingImpl, Element> relatedElement;
	public static volatile SingularAttribute<SuperclassingImpl, String> name;
	public static volatile ListAttribute<SuperclassingImpl, Comment> documentationComment;
	public static volatile SingularAttribute<SuperclassingImpl, String> effectiveName;

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
	public static final String EFFECTIVE_NAME = "effectiveName";

}

