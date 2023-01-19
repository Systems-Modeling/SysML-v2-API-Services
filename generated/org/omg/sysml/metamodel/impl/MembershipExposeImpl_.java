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
@StaticMetamodel(MembershipExposeImpl.class)
public abstract class MembershipExposeImpl_ extends org.omg.sysml.lifecycle.impl.DataImpl_ {

	public static volatile SingularAttribute<MembershipExposeImpl, UUID> elementId;
	public static volatile ListAttribute<MembershipExposeImpl, TextualRepresentation> textualRepresentation;
	public static volatile SingularAttribute<MembershipExposeImpl, String> declaredShortName;
	public static volatile SingularAttribute<MembershipExposeImpl, Boolean> isLibraryElement;
	public static volatile SingularAttribute<MembershipExposeImpl, Boolean> isImplied;
	public static volatile ListAttribute<MembershipExposeImpl, Annotation> ownedAnnotation;
	public static volatile ListAttribute<MembershipExposeImpl, Element> ownedElement;
	public static volatile ListAttribute<MembershipExposeImpl, String> aliasIds;
	public static volatile SingularAttribute<MembershipExposeImpl, VisibilityKind> visibility;
	public static volatile ListAttribute<MembershipExposeImpl, Relationship> ownedRelationship;
	public static volatile SingularAttribute<MembershipExposeImpl, String> qualifiedName;
	public static volatile ListAttribute<MembershipExposeImpl, Documentation> documentation;
	public static volatile ListAttribute<MembershipExposeImpl, Element> ownedRelatedElement;
	public static volatile ListAttribute<MembershipExposeImpl, Element> source;
	public static volatile SingularAttribute<MembershipExposeImpl, Boolean> isImpliedIncluded;
	public static volatile ListAttribute<MembershipExposeImpl, Element> target;
	public static volatile SingularAttribute<MembershipExposeImpl, Boolean> isRecursive;
	public static volatile ListAttribute<MembershipExposeImpl, Element> relatedElement;
	public static volatile SingularAttribute<MembershipExposeImpl, String> name;
	public static volatile SingularAttribute<MembershipExposeImpl, String> declaredName;
	public static volatile SingularAttribute<MembershipExposeImpl, Boolean> isImportAll;
	public static volatile SingularAttribute<MembershipExposeImpl, String> shortName;

	public static final String ELEMENT_ID = "elementId";
	public static final String TEXTUAL_REPRESENTATION = "textualRepresentation";
	public static final String DECLARED_SHORT_NAME = "declaredShortName";
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
	public static final String DECLARED_NAME = "declaredName";
	public static final String IS_IMPORT_ALL = "isImportAll";
	public static final String SHORT_NAME = "shortName";

}

