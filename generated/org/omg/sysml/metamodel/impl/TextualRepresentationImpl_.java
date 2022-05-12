package org.omg.sysml.metamodel.impl;

import java.util.UUID;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.omg.sysml.metamodel.Annotation;
import org.omg.sysml.metamodel.Documentation;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.Relationship;
import org.omg.sysml.metamodel.TextualRepresentation;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TextualRepresentationImpl.class)
public abstract class TextualRepresentationImpl_ extends org.omg.sysml.lifecycle.impl.DataImpl_ {

	public static volatile ListAttribute<TextualRepresentationImpl, Annotation> annotation;
	public static volatile SingularAttribute<TextualRepresentationImpl, UUID> elementId;
	public static volatile ListAttribute<TextualRepresentationImpl, TextualRepresentation> textualRepresentation;
	public static volatile ListAttribute<TextualRepresentationImpl, Annotation> ownedAnnotation;
	public static volatile ListAttribute<TextualRepresentationImpl, Element> ownedElement;
	public static volatile ListAttribute<TextualRepresentationImpl, String> aliasIds;
	public static volatile ListAttribute<TextualRepresentationImpl, Relationship> ownedRelationship;
	public static volatile SingularAttribute<TextualRepresentationImpl, String> qualifiedName;
	public static volatile ListAttribute<TextualRepresentationImpl, Documentation> documentation;
	public static volatile SingularAttribute<TextualRepresentationImpl, String> language;
	public static volatile ListAttribute<TextualRepresentationImpl, Element> annotatedElement;
	public static volatile SingularAttribute<TextualRepresentationImpl, String> body;
	public static volatile SingularAttribute<TextualRepresentationImpl, String> name;
	public static volatile SingularAttribute<TextualRepresentationImpl, String> shortName;
	public static volatile SingularAttribute<TextualRepresentationImpl, String> effectiveName;

	public static final String ANNOTATION = "annotation";
	public static final String ELEMENT_ID = "elementId";
	public static final String TEXTUAL_REPRESENTATION = "textualRepresentation";
	public static final String OWNED_ANNOTATION = "ownedAnnotation";
	public static final String OWNED_ELEMENT = "ownedElement";
	public static final String ALIAS_IDS = "aliasIds";
	public static final String OWNED_RELATIONSHIP = "ownedRelationship";
	public static final String QUALIFIED_NAME = "qualifiedName";
	public static final String DOCUMENTATION = "documentation";
	public static final String LANGUAGE = "language";
	public static final String ANNOTATED_ELEMENT = "annotatedElement";
	public static final String BODY = "body";
	public static final String NAME = "name";
	public static final String SHORT_NAME = "shortName";
	public static final String EFFECTIVE_NAME = "effectiveName";

}

