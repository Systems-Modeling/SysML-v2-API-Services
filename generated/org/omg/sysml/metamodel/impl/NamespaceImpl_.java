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
import org.omg.sysml.metamodel.Import;
import org.omg.sysml.metamodel.Membership;
import org.omg.sysml.metamodel.Relationship;
import org.omg.sysml.metamodel.TextualRepresentation;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(NamespaceImpl.class)
public abstract class NamespaceImpl_ extends org.omg.sysml.metamodel.impl.MofObjectImpl_ {

	public static volatile SingularAttribute<NamespaceImpl, UUID> identifier;
	public static volatile ListAttribute<NamespaceImpl, Membership> importedMembership;
	public static volatile CollectionAttribute<NamespaceImpl, Annotation> ownedAnnotation;
	public static volatile CollectionAttribute<NamespaceImpl, Element> ownedElement;
	public static volatile ListAttribute<NamespaceImpl, Relationship> ownedRelationship;
	public static volatile CollectionAttribute<NamespaceImpl, Documentation> documentation;
	public static volatile ListAttribute<NamespaceImpl, Membership> membership;
	public static volatile ListAttribute<NamespaceImpl, Import> ownedImport;
	public static volatile CollectionAttribute<NamespaceImpl, TextualRepresentation> ownedTextualRepresentation;
	public static volatile SingularAttribute<NamespaceImpl, String> humanId;
	public static volatile CollectionAttribute<NamespaceImpl, String> aliasId;
	public static volatile ListAttribute<NamespaceImpl, Element> member;
	public static volatile SingularAttribute<NamespaceImpl, String> name;
	public static volatile CollectionAttribute<NamespaceImpl, Comment> documentationComment;
	public static volatile ListAttribute<NamespaceImpl, Element> ownedMember;
	public static volatile ListAttribute<NamespaceImpl, Membership> ownedMembership;

	public static final String IDENTIFIER = "identifier";
	public static final String IMPORTED_MEMBERSHIP = "importedMembership";
	public static final String OWNED_ANNOTATION = "ownedAnnotation";
	public static final String OWNED_ELEMENT = "ownedElement";
	public static final String OWNED_RELATIONSHIP = "ownedRelationship";
	public static final String DOCUMENTATION = "documentation";
	public static final String MEMBERSHIP = "membership";
	public static final String OWNED_IMPORT = "ownedImport";
	public static final String OWNED_TEXTUAL_REPRESENTATION = "ownedTextualRepresentation";
	public static final String HUMAN_ID = "humanId";
	public static final String ALIAS_ID = "aliasId";
	public static final String MEMBER = "member";
	public static final String NAME = "name";
	public static final String DOCUMENTATION_COMMENT = "documentationComment";
	public static final String OWNED_MEMBER = "ownedMember";
	public static final String OWNED_MEMBERSHIP = "ownedMembership";

}

