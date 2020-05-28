package org.omg.sysml.metamodel.impl;

import java.util.UUID;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.FeatureDirectionKind;
import org.omg.sysml.metamodel.Relationship;
import org.omg.sysml.metamodel.RequirementConstraintKind;
import org.omg.sysml.metamodel.VisibilityKind;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RequirementConstraintMembershipImpl.class)
public abstract class RequirementConstraintMembershipImpl_ extends org.omg.sysml.metamodel.impl.MofObjectImpl_ {

	public static volatile SingularAttribute<RequirementConstraintMembershipImpl, UUID> identifier;
	public static volatile SingularAttribute<RequirementConstraintMembershipImpl, Boolean> isDerived;
	public static volatile CollectionAttribute<RequirementConstraintMembershipImpl, Element> ownedElement;
	public static volatile SingularAttribute<RequirementConstraintMembershipImpl, VisibilityKind> visibility;
	public static volatile SingularAttribute<RequirementConstraintMembershipImpl, RequirementConstraintKind> kind;
	public static volatile CollectionAttribute<RequirementConstraintMembershipImpl, Relationship> ownedRelationship;
	public static volatile SingularAttribute<RequirementConstraintMembershipImpl, String> memberName;
	public static volatile CollectionAttribute<RequirementConstraintMembershipImpl, Element> ownedRelatedElement;
	public static volatile CollectionAttribute<RequirementConstraintMembershipImpl, Element> source;
	public static volatile SingularAttribute<RequirementConstraintMembershipImpl, Boolean> isPort;
	public static volatile SingularAttribute<RequirementConstraintMembershipImpl, Boolean> isPortion;
	public static volatile CollectionAttribute<RequirementConstraintMembershipImpl, Element> target;
	public static volatile SingularAttribute<RequirementConstraintMembershipImpl, Boolean> isComposite;
	public static volatile SingularAttribute<RequirementConstraintMembershipImpl, Boolean> isReadOnly;
	public static volatile CollectionAttribute<RequirementConstraintMembershipImpl, Element> relatedElement;
	public static volatile SingularAttribute<RequirementConstraintMembershipImpl, String> name;
	public static volatile SingularAttribute<RequirementConstraintMembershipImpl, FeatureDirectionKind> direction;

	public static final String IDENTIFIER = "identifier";
	public static final String IS_DERIVED = "isDerived";
	public static final String OWNED_ELEMENT = "ownedElement";
	public static final String VISIBILITY = "visibility";
	public static final String KIND = "kind";
	public static final String OWNED_RELATIONSHIP = "ownedRelationship";
	public static final String MEMBER_NAME = "memberName";
	public static final String OWNED_RELATED_ELEMENT = "ownedRelatedElement";
	public static final String SOURCE = "source";
	public static final String IS_PORT = "isPort";
	public static final String IS_PORTION = "isPortion";
	public static final String TARGET = "target";
	public static final String IS_COMPOSITE = "isComposite";
	public static final String IS_READ_ONLY = "isReadOnly";
	public static final String RELATED_ELEMENT = "relatedElement";
	public static final String NAME = "name";
	public static final String DIRECTION = "direction";

}

