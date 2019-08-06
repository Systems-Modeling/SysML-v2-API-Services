package org.omg.sysml.metamodel.impl;

import java.util.UUID;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.Import;
import org.omg.sysml.metamodel.Membership;
import org.omg.sysml.metamodel.Relationship;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PackageImpl.class)
public abstract class PackageImpl_ extends org.omg.sysml.metamodel.impl.MofObjectImpl_ {

	public static volatile ListAttribute<PackageImpl, Membership> importedMembership;
	public static volatile SingularAttribute<PackageImpl, UUID> identifier;
	public static volatile CollectionAttribute<PackageImpl, Element> ownedElement;
	public static volatile CollectionAttribute<PackageImpl, Relationship> ownedRelationship;
	public static volatile SingularAttribute<PackageImpl, String> name;
	public static volatile ListAttribute<PackageImpl, Element> member;
	public static volatile ListAttribute<PackageImpl, Membership> membership;
	public static volatile ListAttribute<PackageImpl, Import> ownedImport;
	public static volatile ListAttribute<PackageImpl, Element> ownedMember;
	public static volatile ListAttribute<PackageImpl, Membership> ownedMembership;

	public static final String IMPORTED_MEMBERSHIP = "importedMembership";
	public static final String IDENTIFIER = "identifier";
	public static final String OWNED_ELEMENT = "ownedElement";
	public static final String OWNED_RELATIONSHIP = "ownedRelationship";
	public static final String NAME = "name";
	public static final String MEMBER = "member";
	public static final String MEMBERSHIP = "membership";
	public static final String OWNED_IMPORT = "ownedImport";
	public static final String OWNED_MEMBER = "ownedMember";
	public static final String OWNED_MEMBERSHIP = "ownedMembership";

}

