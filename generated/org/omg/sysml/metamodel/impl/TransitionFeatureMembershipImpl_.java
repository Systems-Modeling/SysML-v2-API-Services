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
import org.omg.sysml.metamodel.TextualRepresentation;
import org.omg.sysml.metamodel.TransitionFeatureKind;
import org.omg.sysml.metamodel.VisibilityKind;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TransitionFeatureMembershipImpl.class)
public abstract class TransitionFeatureMembershipImpl_ extends org.omg.sysml.metamodel.impl.MofObjectImpl_ {

	public static volatile SingularAttribute<TransitionFeatureMembershipImpl, UUID> identifier;
	public static volatile SingularAttribute<TransitionFeatureMembershipImpl, Boolean> isDerived;
	public static volatile CollectionAttribute<TransitionFeatureMembershipImpl, Annotation> ownedAnnotation;
	public static volatile CollectionAttribute<TransitionFeatureMembershipImpl, Element> ownedElement;
	public static volatile SingularAttribute<TransitionFeatureMembershipImpl, VisibilityKind> visibility;
	public static volatile SingularAttribute<TransitionFeatureMembershipImpl, TransitionFeatureKind> kind;
	public static volatile ListAttribute<TransitionFeatureMembershipImpl, Relationship> ownedRelationship;
	public static volatile SingularAttribute<TransitionFeatureMembershipImpl, String> qualifiedName;
	public static volatile CollectionAttribute<TransitionFeatureMembershipImpl, Documentation> documentation;
	public static volatile SingularAttribute<TransitionFeatureMembershipImpl, String> memberName;
	public static volatile ListAttribute<TransitionFeatureMembershipImpl, Element> ownedRelatedElement;
	public static volatile ListAttribute<TransitionFeatureMembershipImpl, Element> source;
	public static volatile SingularAttribute<TransitionFeatureMembershipImpl, Boolean> isPort;
	public static volatile SingularAttribute<TransitionFeatureMembershipImpl, Boolean> isPortion;
	public static volatile ListAttribute<TransitionFeatureMembershipImpl, Element> target;
	public static volatile CollectionAttribute<TransitionFeatureMembershipImpl, TextualRepresentation> ownedTextualRepresentation;
	public static volatile SingularAttribute<TransitionFeatureMembershipImpl, Boolean> isComposite;
	public static volatile SingularAttribute<TransitionFeatureMembershipImpl, Boolean> isReadOnly;
	public static volatile SingularAttribute<TransitionFeatureMembershipImpl, String> humanId;
	public static volatile CollectionAttribute<TransitionFeatureMembershipImpl, String> aliasId;
	public static volatile ListAttribute<TransitionFeatureMembershipImpl, Element> relatedElement;
	public static volatile SingularAttribute<TransitionFeatureMembershipImpl, String> name;
	public static volatile CollectionAttribute<TransitionFeatureMembershipImpl, Comment> documentationComment;
	public static volatile SingularAttribute<TransitionFeatureMembershipImpl, FeatureDirectionKind> direction;

	public static final String IDENTIFIER = "identifier";
	public static final String IS_DERIVED = "isDerived";
	public static final String OWNED_ANNOTATION = "ownedAnnotation";
	public static final String OWNED_ELEMENT = "ownedElement";
	public static final String VISIBILITY = "visibility";
	public static final String KIND = "kind";
	public static final String OWNED_RELATIONSHIP = "ownedRelationship";
	public static final String QUALIFIED_NAME = "qualifiedName";
	public static final String DOCUMENTATION = "documentation";
	public static final String MEMBER_NAME = "memberName";
	public static final String OWNED_RELATED_ELEMENT = "ownedRelatedElement";
	public static final String SOURCE = "source";
	public static final String IS_PORT = "isPort";
	public static final String IS_PORTION = "isPortion";
	public static final String TARGET = "target";
	public static final String OWNED_TEXTUAL_REPRESENTATION = "ownedTextualRepresentation";
	public static final String IS_COMPOSITE = "isComposite";
	public static final String IS_READ_ONLY = "isReadOnly";
	public static final String HUMAN_ID = "humanId";
	public static final String ALIAS_ID = "aliasId";
	public static final String RELATED_ELEMENT = "relatedElement";
	public static final String NAME = "name";
	public static final String DOCUMENTATION_COMMENT = "documentationComment";
	public static final String DIRECTION = "direction";

}

