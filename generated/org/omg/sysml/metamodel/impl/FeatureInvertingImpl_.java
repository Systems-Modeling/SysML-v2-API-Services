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
@StaticMetamodel(FeatureInvertingImpl.class)
public abstract class FeatureInvertingImpl_ extends org.omg.sysml.lifecycle.impl.DataImpl_ {

	public static volatile SingularAttribute<FeatureInvertingImpl, UUID> elementId;
	public static volatile ListAttribute<FeatureInvertingImpl, TextualRepresentation> textualRepresentation;
	public static volatile ListAttribute<FeatureInvertingImpl, Annotation> ownedAnnotation;
	public static volatile ListAttribute<FeatureInvertingImpl, Element> ownedElement;
	public static volatile ListAttribute<FeatureInvertingImpl, String> aliasIds;
	public static volatile ListAttribute<FeatureInvertingImpl, Relationship> ownedRelationship;
	public static volatile SingularAttribute<FeatureInvertingImpl, String> qualifiedName;
	public static volatile ListAttribute<FeatureInvertingImpl, Documentation> documentation;
	public static volatile ListAttribute<FeatureInvertingImpl, Element> ownedRelatedElement;
	public static volatile ListAttribute<FeatureInvertingImpl, Element> source;
	public static volatile ListAttribute<FeatureInvertingImpl, Element> target;
	public static volatile ListAttribute<FeatureInvertingImpl, Element> relatedElement;
	public static volatile SingularAttribute<FeatureInvertingImpl, String> name;
	public static volatile SingularAttribute<FeatureInvertingImpl, String> shortName;
	public static volatile SingularAttribute<FeatureInvertingImpl, String> effectiveName;

	public static final String ELEMENT_ID = "elementId";
	public static final String TEXTUAL_REPRESENTATION = "textualRepresentation";
	public static final String OWNED_ANNOTATION = "ownedAnnotation";
	public static final String OWNED_ELEMENT = "ownedElement";
	public static final String ALIAS_IDS = "aliasIds";
	public static final String OWNED_RELATIONSHIP = "ownedRelationship";
	public static final String QUALIFIED_NAME = "qualifiedName";
	public static final String DOCUMENTATION = "documentation";
	public static final String OWNED_RELATED_ELEMENT = "ownedRelatedElement";
	public static final String SOURCE = "source";
	public static final String TARGET = "target";
	public static final String RELATED_ELEMENT = "relatedElement";
	public static final String NAME = "name";
	public static final String SHORT_NAME = "shortName";
	public static final String EFFECTIVE_NAME = "effectiveName";

}

