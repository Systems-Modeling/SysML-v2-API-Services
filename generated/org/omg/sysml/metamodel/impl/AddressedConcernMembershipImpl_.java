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
import org.omg.sysml.metamodel.FeatureDirectionKind;
import org.omg.sysml.metamodel.Relationship;
import org.omg.sysml.metamodel.RequirementConstraintKind;
import org.omg.sysml.metamodel.TextualRepresentation;
import org.omg.sysml.metamodel.VisibilityKind;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AddressedConcernMembershipImpl.class)
public abstract class AddressedConcernMembershipImpl_ extends org.omg.sysml.metamodel.impl.MofObjectImpl_ {

	public static volatile SingularAttribute<AddressedConcernMembershipImpl, String> memberName;
	public static volatile ListAttribute<AddressedConcernMembershipImpl, Element> ownedRelatedElement;
	public static volatile ListAttribute<AddressedConcernMembershipImpl, Element> source;
	public static volatile SingularAttribute<AddressedConcernMembershipImpl, Boolean> isPortion;
	public static volatile SingularAttribute<AddressedConcernMembershipImpl, Boolean> isReadOnly;
	public static volatile ListAttribute<AddressedConcernMembershipImpl, Comment> documentationComment;
	public static volatile SingularAttribute<AddressedConcernMembershipImpl, String> effectiveName;
	public static volatile SingularAttribute<AddressedConcernMembershipImpl, FeatureDirectionKind> direction;
	public static volatile SingularAttribute<AddressedConcernMembershipImpl, UUID> identifier;
	public static volatile SingularAttribute<AddressedConcernMembershipImpl, Boolean> isDerived;
	public static volatile ListAttribute<AddressedConcernMembershipImpl, Annotation> ownedAnnotation;
	public static volatile ListAttribute<AddressedConcernMembershipImpl, Element> ownedElement;
	public static volatile SingularAttribute<AddressedConcernMembershipImpl, VisibilityKind> visibility;
	public static volatile SingularAttribute<AddressedConcernMembershipImpl, RequirementConstraintKind> kind;
	public static volatile ListAttribute<AddressedConcernMembershipImpl, Relationship> ownedRelationship;
	public static volatile SingularAttribute<AddressedConcernMembershipImpl, String> qualifiedName;
	public static volatile ListAttribute<AddressedConcernMembershipImpl, Documentation> documentation;
	public static volatile SingularAttribute<AddressedConcernMembershipImpl, String> effectiveMemberName;
	public static volatile SingularAttribute<AddressedConcernMembershipImpl, Boolean> isPort;
	public static volatile ListAttribute<AddressedConcernMembershipImpl, Element> target;
	public static volatile CollectionAttribute<AddressedConcernMembershipImpl, TextualRepresentation> ownedTextualRepresentation;
	public static volatile SingularAttribute<AddressedConcernMembershipImpl, Boolean> isComposite;
	public static volatile SingularAttribute<AddressedConcernMembershipImpl, String> humanId;
	public static volatile ListAttribute<AddressedConcernMembershipImpl, String> aliasId;
	public static volatile ListAttribute<AddressedConcernMembershipImpl, Element> relatedElement;
	public static volatile SingularAttribute<AddressedConcernMembershipImpl, String> name;

	public static final String MEMBER_NAME = "memberName";
	public static final String OWNED_RELATED_ELEMENT = "ownedRelatedElement";
	public static final String SOURCE = "source";
	public static final String IS_PORTION = "isPortion";
	public static final String IS_READ_ONLY = "isReadOnly";
	public static final String DOCUMENTATION_COMMENT = "documentationComment";
	public static final String EFFECTIVE_NAME = "effectiveName";
	public static final String DIRECTION = "direction";
	public static final String IDENTIFIER = "identifier";
	public static final String IS_DERIVED = "isDerived";
	public static final String OWNED_ANNOTATION = "ownedAnnotation";
	public static final String OWNED_ELEMENT = "ownedElement";
	public static final String VISIBILITY = "visibility";
	public static final String KIND = "kind";
	public static final String OWNED_RELATIONSHIP = "ownedRelationship";
	public static final String QUALIFIED_NAME = "qualifiedName";
	public static final String DOCUMENTATION = "documentation";
	public static final String EFFECTIVE_MEMBER_NAME = "effectiveMemberName";
	public static final String IS_PORT = "isPort";
	public static final String TARGET = "target";
	public static final String OWNED_TEXTUAL_REPRESENTATION = "ownedTextualRepresentation";
	public static final String IS_COMPOSITE = "isComposite";
	public static final String HUMAN_ID = "humanId";
	public static final String ALIAS_ID = "aliasId";
	public static final String RELATED_ELEMENT = "relatedElement";
	public static final String NAME = "name";

}

