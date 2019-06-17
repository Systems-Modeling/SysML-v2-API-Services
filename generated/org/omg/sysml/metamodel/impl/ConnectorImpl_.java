package org.omg.sysml.metamodel.impl;

import java.util.UUID;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.omg.sysml.metamodel.Category;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.Feature;
import org.omg.sysml.metamodel.FeatureMembership;
import org.omg.sysml.metamodel.FeatureTyping;
import org.omg.sysml.metamodel.Generalization;
import org.omg.sysml.metamodel.Import;
import org.omg.sysml.metamodel.Membership;
import org.omg.sysml.metamodel.Redefinition;
import org.omg.sysml.metamodel.Relationship;
import org.omg.sysml.metamodel.Subsetting;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ConnectorImpl.class)
public abstract class ConnectorImpl_ extends org.omg.sysml.metamodel.impl.MofObjectImpl_ {

	public static volatile ListAttribute<ConnectorImpl, Generalization> ownedGeneralization;
	public static volatile CollectionAttribute<ConnectorImpl, Subsetting> ownedSubsetting;
	public static volatile SingularAttribute<ConnectorImpl, Boolean> isUnique;
	public static volatile CollectionAttribute<ConnectorImpl, Element> ownedRelatedElement;
	public static volatile CollectionAttribute<ConnectorImpl, Element> source;
	public static volatile ListAttribute<ConnectorImpl, Membership> membership;
	public static volatile CollectionAttribute<ConnectorImpl, Category> type;
	public static volatile SingularAttribute<ConnectorImpl, Boolean> isNonunique;
	public static volatile CollectionAttribute<ConnectorImpl, Feature> output;
	public static volatile CollectionAttribute<ConnectorImpl, Feature> feature;
	public static volatile ListAttribute<ConnectorImpl, Membership> inheritedMembership;
	public static volatile ListAttribute<ConnectorImpl, Element> member;
	public static volatile ListAttribute<ConnectorImpl, FeatureMembership> ownedFeatureMembership;
	public static volatile SingularAttribute<ConnectorImpl, Boolean> isOrdered;
	public static volatile CollectionAttribute<ConnectorImpl, Redefinition> ownedRedefinition;
	public static volatile ListAttribute<ConnectorImpl, Membership> importedMembership;
	public static volatile SingularAttribute<ConnectorImpl, UUID> identifier;
	public static volatile CollectionAttribute<ConnectorImpl, Element> ownedElement;
	public static volatile CollectionAttribute<ConnectorImpl, Feature> ownedFeature;
	public static volatile CollectionAttribute<ConnectorImpl, Relationship> ownedRelationship;
	public static volatile ListAttribute<ConnectorImpl, Import> ownedImport;
	public static volatile CollectionAttribute<ConnectorImpl, Category> referencedType;
	public static volatile CollectionAttribute<ConnectorImpl, Feature> relatedFeature;
	public static volatile SingularAttribute<ConnectorImpl, Boolean> isAbstract;
	public static volatile CollectionAttribute<ConnectorImpl, Element> target;
	public static volatile CollectionAttribute<ConnectorImpl, Feature> input;
	public static volatile SingularAttribute<ConnectorImpl, Boolean> isComposite;
	public static volatile SingularAttribute<ConnectorImpl, Boolean> isDirected;
	public static volatile CollectionAttribute<ConnectorImpl, Element> relatedElement;
	public static volatile CollectionAttribute<ConnectorImpl, Category> ownedType;
	public static volatile SingularAttribute<ConnectorImpl, String> name;
	public static volatile CollectionAttribute<ConnectorImpl, FeatureTyping> typing;
	public static volatile CollectionAttribute<ConnectorImpl, Feature> connectorEnd;
	public static volatile ListAttribute<ConnectorImpl, Element> ownedMember;
	public static volatile ListAttribute<ConnectorImpl, Membership> ownedMembership;

	public static final String OWNED_GENERALIZATION = "ownedGeneralization";
	public static final String OWNED_SUBSETTING = "ownedSubsetting";
	public static final String IS_UNIQUE = "isUnique";
	public static final String OWNED_RELATED_ELEMENT = "ownedRelatedElement";
	public static final String SOURCE = "source";
	public static final String MEMBERSHIP = "membership";
	public static final String TYPE = "type";
	public static final String IS_NONUNIQUE = "isNonunique";
	public static final String OUTPUT = "output";
	public static final String FEATURE = "feature";
	public static final String INHERITED_MEMBERSHIP = "inheritedMembership";
	public static final String MEMBER = "member";
	public static final String OWNED_FEATURE_MEMBERSHIP = "ownedFeatureMembership";
	public static final String IS_ORDERED = "isOrdered";
	public static final String OWNED_REDEFINITION = "ownedRedefinition";
	public static final String IMPORTED_MEMBERSHIP = "importedMembership";
	public static final String IDENTIFIER = "identifier";
	public static final String OWNED_ELEMENT = "ownedElement";
	public static final String OWNED_FEATURE = "ownedFeature";
	public static final String OWNED_RELATIONSHIP = "ownedRelationship";
	public static final String OWNED_IMPORT = "ownedImport";
	public static final String REFERENCED_TYPE = "referencedType";
	public static final String RELATED_FEATURE = "relatedFeature";
	public static final String IS_ABSTRACT = "isAbstract";
	public static final String TARGET = "target";
	public static final String INPUT = "input";
	public static final String IS_COMPOSITE = "isComposite";
	public static final String IS_DIRECTED = "isDirected";
	public static final String RELATED_ELEMENT = "relatedElement";
	public static final String OWNED_TYPE = "ownedType";
	public static final String NAME = "name";
	public static final String TYPING = "typing";
	public static final String CONNECTOR_END = "connectorEnd";
	public static final String OWNED_MEMBER = "ownedMember";
	public static final String OWNED_MEMBERSHIP = "ownedMembership";

}

