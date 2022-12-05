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
import org.omg.sysml.metamodel.VisibilityKind;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(NamespaceExposeImpl.class)
public abstract class NamespaceExposeImpl_ extends org.omg.sysml.lifecycle.impl.DataImpl_ {

	public static volatile SingularAttribute<NamespaceExposeImpl, UUID> elementId;
	public static volatile ListAttribute<NamespaceExposeImpl, TextualRepresentation> textualRepresentation;
	public static volatile SingularAttribute<NamespaceExposeImpl, Boolean> isLibraryElement;
	public static volatile SingularAttribute<NamespaceExposeImpl, Boolean> isImplied;
	public static volatile ListAttribute<NamespaceExposeImpl, Annotation> ownedAnnotation;
	public static volatile ListAttribute<NamespaceExposeImpl, Element> ownedElement;
	public static volatile ListAttribute<NamespaceExposeImpl, String> aliasIds;
	public static volatile SingularAttribute<NamespaceExposeImpl, VisibilityKind> visibility;
	public static volatile ListAttribute<NamespaceExposeImpl, Relationship> ownedRelationship;
	public static volatile SingularAttribute<NamespaceExposeImpl, String> qualifiedName;
	public static volatile ListAttribute<NamespaceExposeImpl, Documentation> documentation;
	public static volatile ListAttribute<NamespaceExposeImpl, Element> ownedRelatedElement;
	public static volatile ListAttribute<NamespaceExposeImpl, Element> source;
	public static volatile SingularAttribute<NamespaceExposeImpl, Boolean> isImpliedIncluded;
	public static volatile ListAttribute<NamespaceExposeImpl, Element> target;
	public static volatile SingularAttribute<NamespaceExposeImpl, Boolean> isRecursive;
	public static volatile ListAttribute<NamespaceExposeImpl, Element> relatedElement;
	public static volatile SingularAttribute<NamespaceExposeImpl, String> name;
	public static volatile SingularAttribute<NamespaceExposeImpl, Boolean> isImportAll;
	public static volatile SingularAttribute<NamespaceExposeImpl, String> shortName;
	public static volatile SingularAttribute<NamespaceExposeImpl, String> effectiveName;

	public static final String ELEMENT_ID = "elementId";
	public static final String TEXTUAL_REPRESENTATION = "textualRepresentation";
	public static final String IS_LIBRARY_ELEMENT = "isLibraryElement";
	public static final String IS_IMPLIED = "isImplied";
	public static final String OWNED_ANNOTATION = "ownedAnnotation";
	public static final String OWNED_ELEMENT = "ownedElement";
	public static final String ALIAS_IDS = "aliasIds";
	public static final String VISIBILITY = "visibility";
	public static final String OWNED_RELATIONSHIP = "ownedRelationship";
	public static final String QUALIFIED_NAME = "qualifiedName";
	public static final String DOCUMENTATION = "documentation";
	public static final String OWNED_RELATED_ELEMENT = "ownedRelatedElement";
	public static final String SOURCE = "source";
	public static final String IS_IMPLIED_INCLUDED = "isImpliedIncluded";
	public static final String TARGET = "target";
	public static final String IS_RECURSIVE = "isRecursive";
	public static final String RELATED_ELEMENT = "relatedElement";
	public static final String NAME = "name";
	public static final String IS_IMPORT_ALL = "isImportAll";
	public static final String SHORT_NAME = "shortName";
	public static final String EFFECTIVE_NAME = "effectiveName";

}

