package org.omg.sysml.external.impl;

import java.net.URI;
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
import org.omg.sysml.metamodel.Relationship;
import org.omg.sysml.metamodel.TextualRepresentation;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ExternalDataImpl.class)
public abstract class ExternalElementImpl_ extends org.omg.sysml.metamodel.impl.SysMLTypeImpl_ {

	public static volatile SingularAttribute<ExternalDataImpl, UUID> identifier;
	public static volatile ListAttribute<ExternalDataImpl, Annotation> ownedAnnotation;
	public static volatile ListAttribute<ExternalDataImpl, Element> ownedElement;
	public static volatile ListAttribute<ExternalDataImpl, Relationship> ownedRelationship;
	public static volatile SingularAttribute<ExternalDataImpl, String> qualifiedName;
	public static volatile ListAttribute<ExternalDataImpl, Documentation> documentation;
	public static volatile CollectionAttribute<ExternalDataImpl, TextualRepresentation> ownedTextualRepresentation;
	public static volatile SingularAttribute<ExternalDataImpl, URI> resourceIdentifier;
	public static volatile SingularAttribute<ExternalDataImpl, String> humanId;
	public static volatile ListAttribute<ExternalDataImpl, String> aliasId;
	public static volatile SingularAttribute<ExternalDataImpl, String> name;
	public static volatile ListAttribute<ExternalDataImpl, Comment> documentationComment;
	public static volatile SingularAttribute<ExternalDataImpl, String> effectiveName;

	public static final String IDENTIFIER = "identifier";
	public static final String OWNED_ANNOTATION = "ownedAnnotation";
	public static final String OWNED_ELEMENT = "ownedElement";
	public static final String OWNED_RELATIONSHIP = "ownedRelationship";
	public static final String QUALIFIED_NAME = "qualifiedName";
	public static final String DOCUMENTATION = "documentation";
	public static final String OWNED_TEXTUAL_REPRESENTATION = "ownedTextualRepresentation";
	public static final String RESOURCE_IDENTIFIER = "resourceIdentifier";
	public static final String HUMAN_ID = "humanId";
	public static final String ALIAS_ID = "aliasId";
	public static final String NAME = "name";
	public static final String DOCUMENTATION_COMMENT = "documentationComment";
	public static final String EFFECTIVE_NAME = "effectiveName";

}

