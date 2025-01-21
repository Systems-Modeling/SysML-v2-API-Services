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
@StaticMetamodel(CrossSubsettingImpl.class)
public abstract class CrossSubsettingImpl_ extends org.omg.sysml.lifecycle.impl.DataImpl_ {

	public static volatile SingularAttribute<CrossSubsettingImpl, UUID> elementId;
	public static volatile ListAttribute<CrossSubsettingImpl, TextualRepresentation> textualRepresentation;
	public static volatile SingularAttribute<CrossSubsettingImpl, String> declaredShortName;
	public static volatile SingularAttribute<CrossSubsettingImpl, Boolean> isLibraryElement;
	public static volatile SingularAttribute<CrossSubsettingImpl, Boolean> isImplied;
	public static volatile ListAttribute<CrossSubsettingImpl, Annotation> ownedAnnotation;
	public static volatile ListAttribute<CrossSubsettingImpl, Element> ownedElement;
	public static volatile ListAttribute<CrossSubsettingImpl, String> aliasIds;
	public static volatile ListAttribute<CrossSubsettingImpl, Relationship> ownedRelationship;
	public static volatile SingularAttribute<CrossSubsettingImpl, String> qualifiedName;
	public static volatile ListAttribute<CrossSubsettingImpl, Documentation> documentation;
	public static volatile ListAttribute<CrossSubsettingImpl, Element> ownedRelatedElement;
	public static volatile ListAttribute<CrossSubsettingImpl, Element> source;
	public static volatile SingularAttribute<CrossSubsettingImpl, Boolean> isImpliedIncluded;
	public static volatile ListAttribute<CrossSubsettingImpl, Element> target;
	public static volatile ListAttribute<CrossSubsettingImpl, Element> relatedElement;
	public static volatile SingularAttribute<CrossSubsettingImpl, String> name;
	public static volatile SingularAttribute<CrossSubsettingImpl, String> declaredName;
	public static volatile SingularAttribute<CrossSubsettingImpl, String> shortName;

	public static final String ELEMENT_ID = "elementId";
	public static final String TEXTUAL_REPRESENTATION = "textualRepresentation";
	public static final String DECLARED_SHORT_NAME = "declaredShortName";
	public static final String IS_LIBRARY_ELEMENT = "isLibraryElement";
	public static final String IS_IMPLIED = "isImplied";
	public static final String OWNED_ANNOTATION = "ownedAnnotation";
	public static final String OWNED_ELEMENT = "ownedElement";
	public static final String ALIAS_IDS = "aliasIds";
	public static final String OWNED_RELATIONSHIP = "ownedRelationship";
	public static final String QUALIFIED_NAME = "qualifiedName";
	public static final String DOCUMENTATION = "documentation";
	public static final String OWNED_RELATED_ELEMENT = "ownedRelatedElement";
	public static final String SOURCE = "source";
	public static final String IS_IMPLIED_INCLUDED = "isImpliedIncluded";
	public static final String TARGET = "target";
	public static final String RELATED_ELEMENT = "relatedElement";
	public static final String NAME = "name";
	public static final String DECLARED_NAME = "declaredName";
	public static final String SHORT_NAME = "shortName";

}

