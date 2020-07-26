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
import org.omg.sysml.metamodel.VisibilityKind;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ResultExpressionMembershipImpl.class)
public abstract class ResultExpressionMembershipImpl_ extends org.omg.sysml.metamodel.impl.MofObjectImpl_ {

	public static volatile SingularAttribute<ResultExpressionMembershipImpl, UUID> identifier;
	public static volatile SingularAttribute<ResultExpressionMembershipImpl, Boolean> isDerived;
	public static volatile CollectionAttribute<ResultExpressionMembershipImpl, Annotation> ownedAnnotation;
	public static volatile CollectionAttribute<ResultExpressionMembershipImpl, Element> ownedElement;
	public static volatile SingularAttribute<ResultExpressionMembershipImpl, VisibilityKind> visibility;
	public static volatile ListAttribute<ResultExpressionMembershipImpl, Relationship> ownedRelationship;
	public static volatile CollectionAttribute<ResultExpressionMembershipImpl, Documentation> documentation;
	public static volatile SingularAttribute<ResultExpressionMembershipImpl, String> memberName;
	public static volatile ListAttribute<ResultExpressionMembershipImpl, Element> ownedRelatedElement;
	public static volatile ListAttribute<ResultExpressionMembershipImpl, Element> source;
	public static volatile SingularAttribute<ResultExpressionMembershipImpl, Boolean> isPort;
	public static volatile SingularAttribute<ResultExpressionMembershipImpl, Boolean> isPortion;
	public static volatile ListAttribute<ResultExpressionMembershipImpl, Element> target;
	public static volatile CollectionAttribute<ResultExpressionMembershipImpl, TextualRepresentation> ownedTextualRepresentation;
	public static volatile SingularAttribute<ResultExpressionMembershipImpl, Boolean> isComposite;
	public static volatile SingularAttribute<ResultExpressionMembershipImpl, Boolean> isReadOnly;
	public static volatile SingularAttribute<ResultExpressionMembershipImpl, String> humanId;
	public static volatile CollectionAttribute<ResultExpressionMembershipImpl, String> aliasId;
	public static volatile ListAttribute<ResultExpressionMembershipImpl, Element> relatedElement;
	public static volatile SingularAttribute<ResultExpressionMembershipImpl, String> name;
	public static volatile CollectionAttribute<ResultExpressionMembershipImpl, Comment> documentationComment;
	public static volatile SingularAttribute<ResultExpressionMembershipImpl, FeatureDirectionKind> direction;

	public static final String IDENTIFIER = "identifier";
	public static final String IS_DERIVED = "isDerived";
	public static final String OWNED_ANNOTATION = "ownedAnnotation";
	public static final String OWNED_ELEMENT = "ownedElement";
	public static final String VISIBILITY = "visibility";
	public static final String OWNED_RELATIONSHIP = "ownedRelationship";
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

