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
@StaticMetamodel(OwningMembershipImpl.class)
public abstract class OwningMembershipImpl_ extends org.omg.sysml.lifecycle.impl.DataImpl_ {

	public static volatile SingularAttribute<OwningMembershipImpl, UUID> elementId;
	public static volatile ListAttribute<OwningMembershipImpl, TextualRepresentation> textualRepresentation;
	public static volatile SingularAttribute<OwningMembershipImpl, Boolean> isImplied;
	public static volatile ListAttribute<OwningMembershipImpl, Annotation> ownedAnnotation;
	public static volatile ListAttribute<OwningMembershipImpl, Element> ownedElement;
	public static volatile ListAttribute<OwningMembershipImpl, String> aliasIds;
	public static volatile SingularAttribute<OwningMembershipImpl, VisibilityKind> visibility;
	public static volatile ListAttribute<OwningMembershipImpl, Relationship> ownedRelationship;
	public static volatile SingularAttribute<OwningMembershipImpl, String> qualifiedName;
	public static volatile ListAttribute<OwningMembershipImpl, Documentation> documentation;
	public static volatile SingularAttribute<OwningMembershipImpl, String> ownedMemberElementId;
	public static volatile SingularAttribute<OwningMembershipImpl, String> memberName;
	public static volatile ListAttribute<OwningMembershipImpl, Element> ownedRelatedElement;
	public static volatile ListAttribute<OwningMembershipImpl, Element> source;
	public static volatile SingularAttribute<OwningMembershipImpl, String> memberElementId;
	public static volatile SingularAttribute<OwningMembershipImpl, Boolean> isImpliedIncluded;
	public static volatile ListAttribute<OwningMembershipImpl, Element> target;
	public static volatile ListAttribute<OwningMembershipImpl, Element> relatedElement;
	public static volatile SingularAttribute<OwningMembershipImpl, String> name;
	public static volatile SingularAttribute<OwningMembershipImpl, String> memberShortName;
	public static volatile SingularAttribute<OwningMembershipImpl, String> ownedMemberName;
	public static volatile SingularAttribute<OwningMembershipImpl, String> shortName;
	public static volatile SingularAttribute<OwningMembershipImpl, String> effectiveName;
	public static volatile SingularAttribute<OwningMembershipImpl, String> ownedMemberShortName;

	public static final String ELEMENT_ID = "elementId";
	public static final String TEXTUAL_REPRESENTATION = "textualRepresentation";
	public static final String IS_IMPLIED = "isImplied";
	public static final String OWNED_ANNOTATION = "ownedAnnotation";
	public static final String OWNED_ELEMENT = "ownedElement";
	public static final String ALIAS_IDS = "aliasIds";
	public static final String VISIBILITY = "visibility";
	public static final String OWNED_RELATIONSHIP = "ownedRelationship";
	public static final String QUALIFIED_NAME = "qualifiedName";
	public static final String DOCUMENTATION = "documentation";
	public static final String OWNED_MEMBER_ELEMENT_ID = "ownedMemberElementId";
	public static final String MEMBER_NAME = "memberName";
	public static final String OWNED_RELATED_ELEMENT = "ownedRelatedElement";
	public static final String SOURCE = "source";
	public static final String MEMBER_ELEMENT_ID = "memberElementId";
	public static final String IS_IMPLIED_INCLUDED = "isImpliedIncluded";
	public static final String TARGET = "target";
	public static final String RELATED_ELEMENT = "relatedElement";
	public static final String NAME = "name";
	public static final String MEMBER_SHORT_NAME = "memberShortName";
	public static final String OWNED_MEMBER_NAME = "ownedMemberName";
	public static final String SHORT_NAME = "shortName";
	public static final String EFFECTIVE_NAME = "effectiveName";
	public static final String OWNED_MEMBER_SHORT_NAME = "ownedMemberShortName";

}

