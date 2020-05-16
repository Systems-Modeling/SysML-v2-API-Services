package org.omg.sysml.metamodel.impl;

import java.util.UUID;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.Relationship;
import org.omg.sysml.metamodel.VisibilityKind;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MembershipImpl.class)
public abstract class MembershipImpl_ extends org.omg.sysml.metamodel.impl.MofObjectImpl_ {

	public static volatile SingularAttribute<MembershipImpl, UUID> identifier;
	public static volatile CollectionAttribute<MembershipImpl, Element> ownedElement;
	public static volatile SingularAttribute<MembershipImpl, VisibilityKind> visibility;
	public static volatile CollectionAttribute<MembershipImpl, Relationship> ownedRelationship;
	public static volatile CollectionAttribute<MembershipImpl, Element> relatedElement;
	public static volatile SingularAttribute<MembershipImpl, String> name;
	public static volatile SingularAttribute<MembershipImpl, String> memberName;
	public static volatile CollectionAttribute<MembershipImpl, Element> ownedRelatedElement;
	public static volatile CollectionAttribute<MembershipImpl, Element> source;
	public static volatile CollectionAttribute<MembershipImpl, Element> target;

	public static final String IDENTIFIER = "identifier";
	public static final String OWNED_ELEMENT = "ownedElement";
	public static final String VISIBILITY = "visibility";
	public static final String OWNED_RELATIONSHIP = "ownedRelationship";
	public static final String RELATED_ELEMENT = "relatedElement";
	public static final String NAME = "name";
	public static final String MEMBER_NAME = "memberName";
	public static final String OWNED_RELATED_ELEMENT = "ownedRelatedElement";
	public static final String SOURCE = "source";
	public static final String TARGET = "target";

}

