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
import org.omg.sysml.metamodel.Feature;
import org.omg.sysml.metamodel.FeatureMembership;
import org.omg.sysml.metamodel.Generalization;
import org.omg.sysml.metamodel.Import;
import org.omg.sysml.metamodel.Membership;
import org.omg.sysml.metamodel.Relationship;
import org.omg.sysml.metamodel.Superclassing;
import org.omg.sysml.metamodel.TextualRepresentation;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DataTypeImpl.class)
public abstract class DataTypeImpl_ extends org.omg.sysml.metamodel.impl.MofObjectImpl_ {

	public static volatile ListAttribute<DataTypeImpl, Generalization> ownedGeneralization;
	public static volatile SingularAttribute<DataTypeImpl, Boolean> isConjugated;
	public static volatile ListAttribute<DataTypeImpl, Membership> membership;
	public static volatile CollectionAttribute<DataTypeImpl, Superclassing> ownedSuperclassing;
	public static volatile CollectionAttribute<DataTypeImpl, Feature> output;
	public static volatile CollectionAttribute<DataTypeImpl, Feature> feature;
	public static volatile ListAttribute<DataTypeImpl, Membership> inheritedMembership;
	public static volatile ListAttribute<DataTypeImpl, Element> member;
	public static volatile CollectionAttribute<DataTypeImpl, Feature> ownedEndFeature;
	public static volatile ListAttribute<DataTypeImpl, FeatureMembership> ownedFeatureMembership;
	public static volatile CollectionAttribute<DataTypeImpl, Feature> inheritedFeature;
	public static volatile SingularAttribute<DataTypeImpl, Boolean> isSufficient;
	public static volatile CollectionAttribute<DataTypeImpl, Comment> documentationComment;
	public static volatile SingularAttribute<DataTypeImpl, UUID> identifier;
	public static volatile ListAttribute<DataTypeImpl, Membership> importedMembership;
	public static volatile CollectionAttribute<DataTypeImpl, Annotation> ownedAnnotation;
	public static volatile CollectionAttribute<DataTypeImpl, Element> ownedElement;
	public static volatile CollectionAttribute<DataTypeImpl, Feature> ownedFeature;
	public static volatile ListAttribute<DataTypeImpl, Relationship> ownedRelationship;
	public static volatile SingularAttribute<DataTypeImpl, String> qualifiedName;
	public static volatile CollectionAttribute<DataTypeImpl, Documentation> documentation;
	public static volatile ListAttribute<DataTypeImpl, FeatureMembership> featureMembership;
	public static volatile CollectionAttribute<DataTypeImpl, Feature> endFeature;
	public static volatile ListAttribute<DataTypeImpl, Import> ownedImport;
	public static volatile SingularAttribute<DataTypeImpl, Boolean> isAbstract;
	public static volatile CollectionAttribute<DataTypeImpl, Feature> input;
	public static volatile CollectionAttribute<DataTypeImpl, TextualRepresentation> ownedTextualRepresentation;
	public static volatile SingularAttribute<DataTypeImpl, String> humanId;
	public static volatile CollectionAttribute<DataTypeImpl, String> aliasId;
	public static volatile SingularAttribute<DataTypeImpl, String> name;
	public static volatile ListAttribute<DataTypeImpl, Element> ownedMember;
	public static volatile ListAttribute<DataTypeImpl, Membership> ownedMembership;

	public static final String OWNED_GENERALIZATION = "ownedGeneralization";
	public static final String IS_CONJUGATED = "isConjugated";
	public static final String MEMBERSHIP = "membership";
	public static final String OWNED_SUPERCLASSING = "ownedSuperclassing";
	public static final String OUTPUT = "output";
	public static final String FEATURE = "feature";
	public static final String INHERITED_MEMBERSHIP = "inheritedMembership";
	public static final String MEMBER = "member";
	public static final String OWNED_END_FEATURE = "ownedEndFeature";
	public static final String OWNED_FEATURE_MEMBERSHIP = "ownedFeatureMembership";
	public static final String INHERITED_FEATURE = "inheritedFeature";
	public static final String IS_SUFFICIENT = "isSufficient";
	public static final String DOCUMENTATION_COMMENT = "documentationComment";
	public static final String IDENTIFIER = "identifier";
	public static final String IMPORTED_MEMBERSHIP = "importedMembership";
	public static final String OWNED_ANNOTATION = "ownedAnnotation";
	public static final String OWNED_ELEMENT = "ownedElement";
	public static final String OWNED_FEATURE = "ownedFeature";
	public static final String OWNED_RELATIONSHIP = "ownedRelationship";
	public static final String QUALIFIED_NAME = "qualifiedName";
	public static final String DOCUMENTATION = "documentation";
	public static final String FEATURE_MEMBERSHIP = "featureMembership";
	public static final String END_FEATURE = "endFeature";
	public static final String OWNED_IMPORT = "ownedImport";
	public static final String IS_ABSTRACT = "isAbstract";
	public static final String INPUT = "input";
	public static final String OWNED_TEXTUAL_REPRESENTATION = "ownedTextualRepresentation";
	public static final String HUMAN_ID = "humanId";
	public static final String ALIAS_ID = "aliasId";
	public static final String NAME = "name";
	public static final String OWNED_MEMBER = "ownedMember";
	public static final String OWNED_MEMBERSHIP = "ownedMembership";

}

