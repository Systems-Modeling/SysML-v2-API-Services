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
import org.omg.sysml.metamodel.Step;
import org.omg.sysml.metamodel.Superclassing;
import org.omg.sysml.metamodel.TextualRepresentation;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BehaviorImpl.class)
public abstract class BehaviorImpl_ extends org.omg.sysml.metamodel.impl.MofObjectImpl_ {

	public static volatile ListAttribute<BehaviorImpl, Generalization> ownedGeneralization;
	public static volatile SingularAttribute<BehaviorImpl, Boolean> isConjugated;
	public static volatile ListAttribute<BehaviorImpl, Membership> membership;
	public static volatile CollectionAttribute<BehaviorImpl, Superclassing> ownedSuperclassing;
	public static volatile ListAttribute<BehaviorImpl, Feature> output;
	public static volatile ListAttribute<BehaviorImpl, Feature> feature;
	public static volatile ListAttribute<BehaviorImpl, Membership> inheritedMembership;
	public static volatile ListAttribute<BehaviorImpl, Feature> parameter;
	public static volatile ListAttribute<BehaviorImpl, Element> member;
	public static volatile ListAttribute<BehaviorImpl, Feature> ownedEndFeature;
	public static volatile ListAttribute<BehaviorImpl, FeatureMembership> ownedFeatureMembership;
	public static volatile ListAttribute<BehaviorImpl, Feature> inheritedFeature;
	public static volatile SingularAttribute<BehaviorImpl, Boolean> isSufficient;
	public static volatile ListAttribute<BehaviorImpl, Comment> documentationComment;
	public static volatile SingularAttribute<BehaviorImpl, String> effectiveName;
	public static volatile SingularAttribute<BehaviorImpl, UUID> identifier;
	public static volatile ListAttribute<BehaviorImpl, Membership> importedMembership;
	public static volatile ListAttribute<BehaviorImpl, Annotation> ownedAnnotation;
	public static volatile ListAttribute<BehaviorImpl, Element> ownedElement;
	public static volatile ListAttribute<BehaviorImpl, Feature> ownedFeature;
	public static volatile ListAttribute<BehaviorImpl, Relationship> ownedRelationship;
	public static volatile SingularAttribute<BehaviorImpl, String> qualifiedName;
	public static volatile ListAttribute<BehaviorImpl, Documentation> documentation;
	public static volatile ListAttribute<BehaviorImpl, FeatureMembership> featureMembership;
	public static volatile ListAttribute<BehaviorImpl, Feature> endFeature;
	public static volatile ListAttribute<BehaviorImpl, Import> ownedImport;
	public static volatile SingularAttribute<BehaviorImpl, Boolean> isAbstract;
	public static volatile ListAttribute<BehaviorImpl, Feature> input;
	public static volatile CollectionAttribute<BehaviorImpl, TextualRepresentation> ownedTextualRepresentation;
	public static volatile SingularAttribute<BehaviorImpl, String> humanId;
	public static volatile ListAttribute<BehaviorImpl, String> aliasId;
	public static volatile SingularAttribute<BehaviorImpl, String> name;
	public static volatile CollectionAttribute<BehaviorImpl, Step> step;
	public static volatile ListAttribute<BehaviorImpl, Element> ownedMember;
	public static volatile ListAttribute<BehaviorImpl, Membership> ownedMembership;

	public static final String OWNED_GENERALIZATION = "ownedGeneralization";
	public static final String IS_CONJUGATED = "isConjugated";
	public static final String MEMBERSHIP = "membership";
	public static final String OWNED_SUPERCLASSING = "ownedSuperclassing";
	public static final String OUTPUT = "output";
	public static final String FEATURE = "feature";
	public static final String INHERITED_MEMBERSHIP = "inheritedMembership";
	public static final String PARAMETER = "parameter";
	public static final String MEMBER = "member";
	public static final String OWNED_END_FEATURE = "ownedEndFeature";
	public static final String OWNED_FEATURE_MEMBERSHIP = "ownedFeatureMembership";
	public static final String INHERITED_FEATURE = "inheritedFeature";
	public static final String IS_SUFFICIENT = "isSufficient";
	public static final String DOCUMENTATION_COMMENT = "documentationComment";
	public static final String EFFECTIVE_NAME = "effectiveName";
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
	public static final String STEP = "step";
	public static final String OWNED_MEMBER = "ownedMember";
	public static final String OWNED_MEMBERSHIP = "ownedMembership";

}

