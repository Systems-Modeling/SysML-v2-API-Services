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
import org.omg.sysml.metamodel.Type;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AssociationStructureImpl.class)
public abstract class AssociationStructureImpl_ extends org.omg.sysml.metamodel.impl.MofObjectImpl_ {

	public static volatile ListAttribute<AssociationStructureImpl, Generalization> ownedGeneralization;
	public static volatile SingularAttribute<AssociationStructureImpl, Boolean> isConjugated;
	public static volatile ListAttribute<AssociationStructureImpl, Membership> membership;
	public static volatile ListAttribute<AssociationStructureImpl, Element> ownedRelatedElement;
	public static volatile CollectionAttribute<AssociationStructureImpl, Superclassing> ownedSuperclassing;
	public static volatile ListAttribute<AssociationStructureImpl, Element> source;
	public static volatile CollectionAttribute<AssociationStructureImpl, Feature> output;
	public static volatile ListAttribute<AssociationStructureImpl, Type> relatedType;
	public static volatile CollectionAttribute<AssociationStructureImpl, Feature> feature;
	public static volatile ListAttribute<AssociationStructureImpl, Membership> inheritedMembership;
	public static volatile ListAttribute<AssociationStructureImpl, Element> member;
	public static volatile CollectionAttribute<AssociationStructureImpl, Feature> ownedEndFeature;
	public static volatile ListAttribute<AssociationStructureImpl, FeatureMembership> ownedFeatureMembership;
	public static volatile CollectionAttribute<AssociationStructureImpl, Feature> inheritedFeature;
	public static volatile SingularAttribute<AssociationStructureImpl, Boolean> isSufficient;
	public static volatile CollectionAttribute<AssociationStructureImpl, Comment> documentationComment;
	public static volatile SingularAttribute<AssociationStructureImpl, UUID> identifier;
	public static volatile ListAttribute<AssociationStructureImpl, Membership> importedMembership;
	public static volatile CollectionAttribute<AssociationStructureImpl, Annotation> ownedAnnotation;
	public static volatile CollectionAttribute<AssociationStructureImpl, Element> ownedElement;
	public static volatile CollectionAttribute<AssociationStructureImpl, Feature> ownedFeature;
	public static volatile ListAttribute<AssociationStructureImpl, Relationship> ownedRelationship;
	public static volatile CollectionAttribute<AssociationStructureImpl, Documentation> documentation;
	public static volatile ListAttribute<AssociationStructureImpl, FeatureMembership> featureMembership;
	public static volatile CollectionAttribute<AssociationStructureImpl, Feature> endFeature;
	public static volatile CollectionAttribute<AssociationStructureImpl, Type> targetType;
	public static volatile ListAttribute<AssociationStructureImpl, Import> ownedImport;
	public static volatile SingularAttribute<AssociationStructureImpl, Boolean> isAbstract;
	public static volatile CollectionAttribute<AssociationStructureImpl, Feature> associationEnd;
	public static volatile ListAttribute<AssociationStructureImpl, Element> target;
	public static volatile CollectionAttribute<AssociationStructureImpl, Feature> input;
	public static volatile CollectionAttribute<AssociationStructureImpl, TextualRepresentation> ownedTextualRepresentation;
	public static volatile SingularAttribute<AssociationStructureImpl, String> humanId;
	public static volatile CollectionAttribute<AssociationStructureImpl, String> aliasId;
	public static volatile ListAttribute<AssociationStructureImpl, Element> relatedElement;
	public static volatile SingularAttribute<AssociationStructureImpl, String> name;
	public static volatile ListAttribute<AssociationStructureImpl, Element> ownedMember;
	public static volatile ListAttribute<AssociationStructureImpl, Membership> ownedMembership;

	public static final String OWNED_GENERALIZATION = "ownedGeneralization";
	public static final String IS_CONJUGATED = "isConjugated";
	public static final String MEMBERSHIP = "membership";
	public static final String OWNED_RELATED_ELEMENT = "ownedRelatedElement";
	public static final String OWNED_SUPERCLASSING = "ownedSuperclassing";
	public static final String SOURCE = "source";
	public static final String OUTPUT = "output";
	public static final String RELATED_TYPE = "relatedType";
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
	public static final String DOCUMENTATION = "documentation";
	public static final String FEATURE_MEMBERSHIP = "featureMembership";
	public static final String END_FEATURE = "endFeature";
	public static final String TARGET_TYPE = "targetType";
	public static final String OWNED_IMPORT = "ownedImport";
	public static final String IS_ABSTRACT = "isAbstract";
	public static final String ASSOCIATION_END = "associationEnd";
	public static final String TARGET = "target";
	public static final String INPUT = "input";
	public static final String OWNED_TEXTUAL_REPRESENTATION = "ownedTextualRepresentation";
	public static final String HUMAN_ID = "humanId";
	public static final String ALIAS_ID = "aliasId";
	public static final String RELATED_ELEMENT = "relatedElement";
	public static final String NAME = "name";
	public static final String OWNED_MEMBER = "ownedMember";
	public static final String OWNED_MEMBERSHIP = "ownedMembership";

}

