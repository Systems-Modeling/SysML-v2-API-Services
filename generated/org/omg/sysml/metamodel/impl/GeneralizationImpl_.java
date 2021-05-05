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
@StaticMetamodel(GeneralizationImpl.class)
public abstract class GeneralizationImpl_ extends org.omg.sysml.metamodel.impl.MofObjectImpl_ {

	public static volatile SingularAttribute<GeneralizationImpl, UUID> identifier;
	public static volatile ListAttribute<GeneralizationImpl, Annotation> ownedAnnotation;
	public static volatile ListAttribute<GeneralizationImpl, Element> ownedElement;
	public static volatile ListAttribute<GeneralizationImpl, Relationship> ownedRelationship;
	public static volatile SingularAttribute<GeneralizationImpl, String> qualifiedName;
	public static volatile ListAttribute<GeneralizationImpl, Documentation> documentation;
	public static volatile ListAttribute<GeneralizationImpl, Element> ownedRelatedElement;
	public static volatile ListAttribute<GeneralizationImpl, Element> source;
	public static volatile ListAttribute<GeneralizationImpl, Element> target;
	public static volatile CollectionAttribute<GeneralizationImpl, TextualRepresentation> ownedTextualRepresentation;
	public static volatile SingularAttribute<GeneralizationImpl, String> humanId;
	public static volatile ListAttribute<GeneralizationImpl, String> aliasId;
	public static volatile ListAttribute<GeneralizationImpl, Element> relatedElement;
	public static volatile SingularAttribute<GeneralizationImpl, String> name;
	public static volatile ListAttribute<GeneralizationImpl, Comment> documentationComment;
	public static volatile SingularAttribute<GeneralizationImpl, String> effectiveName;

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

