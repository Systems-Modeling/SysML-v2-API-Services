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
@StaticMetamodel(AnnotatingElementImpl.class)
public abstract class AnnotatingElementImpl_ extends org.omg.sysml.lifecycle.impl.DataImpl_ {

	public static volatile ListAttribute<AnnotatingElementImpl, Annotation> annotation;
	public static volatile SingularAttribute<AnnotatingElementImpl, UUID> elementId;
	public static volatile ListAttribute<AnnotatingElementImpl, TextualRepresentation> textualRepresentation;
	public static volatile ListAttribute<AnnotatingElementImpl, Annotation> ownedAnnotation;
	public static volatile ListAttribute<AnnotatingElementImpl, Element> ownedElement;
	public static volatile ListAttribute<AnnotatingElementImpl, String> aliasIds;
	public static volatile ListAttribute<AnnotatingElementImpl, Relationship> ownedRelationship;
	public static volatile SingularAttribute<AnnotatingElementImpl, String> qualifiedName;
	public static volatile ListAttribute<AnnotatingElementImpl, Documentation> documentation;
	public static volatile ListAttribute<AnnotatingElementImpl, Element> annotatedElement;
	public static volatile SingularAttribute<AnnotatingElementImpl, String> name;
	public static volatile SingularAttribute<AnnotatingElementImpl, String> shortName;
	public static volatile SingularAttribute<AnnotatingElementImpl, String> effectiveName;

	public static final String ANNOTATION = "annotation";
	public static final String ELEMENT_ID = "elementId";
	public static final String TEXTUAL_REPRESENTATION = "textualRepresentation";
	public static final String OWNED_ANNOTATION = "ownedAnnotation";
	public static final String OWNED_ELEMENT = "ownedElement";
	public static final String ALIAS_IDS = "aliasIds";
	public static final String OWNED_RELATIONSHIP = "ownedRelationship";
	public static final String QUALIFIED_NAME = "qualifiedName";
	public static final String DOCUMENTATION = "documentation";
	public static final String ANNOTATED_ELEMENT = "annotatedElement";
	public static final String NAME = "name";
	public static final String SHORT_NAME = "shortName";
	public static final String EFFECTIVE_NAME = "effectiveName";

}

