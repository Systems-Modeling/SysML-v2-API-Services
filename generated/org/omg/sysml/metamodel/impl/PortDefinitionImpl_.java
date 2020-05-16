package org.omg.sysml.metamodel.impl;

import java.util.UUID;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.omg.sysml.metamodel.ActionUsage;
import org.omg.sysml.metamodel.ConstraintUsage;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.Feature;
import org.omg.sysml.metamodel.FeatureMembership;
import org.omg.sysml.metamodel.FunctionUsage;
import org.omg.sysml.metamodel.Generalization;
import org.omg.sysml.metamodel.Import;
import org.omg.sysml.metamodel.Membership;
import org.omg.sysml.metamodel.PortUsage;
import org.omg.sysml.metamodel.Property;
import org.omg.sysml.metamodel.Relationship;
import org.omg.sysml.metamodel.RequirementUsage;
import org.omg.sysml.metamodel.StateUsage;
import org.omg.sysml.metamodel.Superclassing;
import org.omg.sysml.metamodel.TransitionUsage;
import org.omg.sysml.metamodel.Usage;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PortDefinitionImpl.class)
public abstract class PortDefinitionImpl_ extends org.omg.sysml.metamodel.impl.MofObjectImpl_ {

	public static volatile CollectionAttribute<PortDefinitionImpl, PortUsage> ownedPort;
	public static volatile ListAttribute<PortDefinitionImpl, Generalization> ownedGeneralization;
	public static volatile SingularAttribute<PortDefinitionImpl, Boolean> isConjugated;
	public static volatile CollectionAttribute<PortDefinitionImpl, StateUsage> ownedState;
	public static volatile ListAttribute<PortDefinitionImpl, Membership> membership;
	public static volatile CollectionAttribute<PortDefinitionImpl, Superclassing> ownedSuperclassing;
	public static volatile CollectionAttribute<PortDefinitionImpl, ConstraintUsage> ownedConstraint;
	public static volatile CollectionAttribute<PortDefinitionImpl, Feature> output;
	public static volatile CollectionAttribute<PortDefinitionImpl, Feature> feature;
	public static volatile ListAttribute<PortDefinitionImpl, Membership> inheritedMembership;
	public static volatile ListAttribute<PortDefinitionImpl, Element> member;
	public static volatile CollectionAttribute<PortDefinitionImpl, Feature> ownedEndFeature;
	public static volatile CollectionAttribute<PortDefinitionImpl, Property> property;
	public static volatile ListAttribute<PortDefinitionImpl, FeatureMembership> ownedFeatureMembership;
	public static volatile CollectionAttribute<PortDefinitionImpl, FunctionUsage> ownedFunction;
	public static volatile CollectionAttribute<PortDefinitionImpl, Feature> inheritedFeature;
	public static volatile CollectionAttribute<PortDefinitionImpl, TransitionUsage> ownedTransition;
	public static volatile SingularAttribute<PortDefinitionImpl, Boolean> isSufficient;
	public static volatile CollectionAttribute<PortDefinitionImpl, Property> ownedProperty;
	public static volatile SingularAttribute<PortDefinitionImpl, UUID> identifier;
	public static volatile ListAttribute<PortDefinitionImpl, Membership> importedMembership;
	public static volatile CollectionAttribute<PortDefinitionImpl, Element> ownedElement;
	public static volatile CollectionAttribute<PortDefinitionImpl, Feature> ownedFeature;
	public static volatile CollectionAttribute<PortDefinitionImpl, Relationship> ownedRelationship;
	public static volatile ListAttribute<PortDefinitionImpl, FeatureMembership> featureMembership;
	public static volatile CollectionAttribute<PortDefinitionImpl, Feature> endFeature;
	public static volatile ListAttribute<PortDefinitionImpl, Import> ownedImport;
	public static volatile CollectionAttribute<PortDefinitionImpl, RequirementUsage> ownedRequirement;
	public static volatile SingularAttribute<PortDefinitionImpl, Boolean> isAbstract;
	public static volatile CollectionAttribute<PortDefinitionImpl, Property> flowProperty;
	public static volatile CollectionAttribute<PortDefinitionImpl, Feature> input;
	public static volatile CollectionAttribute<PortDefinitionImpl, ActionUsage> ownedAction;
	public static volatile SingularAttribute<PortDefinitionImpl, String> name;
	public static volatile CollectionAttribute<PortDefinitionImpl, Usage> ownedUsage;
	public static volatile ListAttribute<PortDefinitionImpl, Element> ownedMember;
	public static volatile ListAttribute<PortDefinitionImpl, Membership> ownedMembership;

	public static final String OWNED_PORT = "ownedPort";
	public static final String OWNED_GENERALIZATION = "ownedGeneralization";
	public static final String IS_CONJUGATED = "isConjugated";
	public static final String OWNED_STATE = "ownedState";
	public static final String MEMBERSHIP = "membership";
	public static final String OWNED_SUPERCLASSING = "ownedSuperclassing";
	public static final String OWNED_CONSTRAINT = "ownedConstraint";
	public static final String OUTPUT = "output";
	public static final String FEATURE = "feature";
	public static final String INHERITED_MEMBERSHIP = "inheritedMembership";
	public static final String MEMBER = "member";
	public static final String OWNED_END_FEATURE = "ownedEndFeature";
	public static final String PROPERTY = "property";
	public static final String OWNED_FEATURE_MEMBERSHIP = "ownedFeatureMembership";
	public static final String OWNED_FUNCTION = "ownedFunction";
	public static final String INHERITED_FEATURE = "inheritedFeature";
	public static final String OWNED_TRANSITION = "ownedTransition";
	public static final String IS_SUFFICIENT = "isSufficient";
	public static final String OWNED_PROPERTY = "ownedProperty";
	public static final String IDENTIFIER = "identifier";
	public static final String IMPORTED_MEMBERSHIP = "importedMembership";
	public static final String OWNED_ELEMENT = "ownedElement";
	public static final String OWNED_FEATURE = "ownedFeature";
	public static final String OWNED_RELATIONSHIP = "ownedRelationship";
	public static final String FEATURE_MEMBERSHIP = "featureMembership";
	public static final String END_FEATURE = "endFeature";
	public static final String OWNED_IMPORT = "ownedImport";
	public static final String OWNED_REQUIREMENT = "ownedRequirement";
	public static final String IS_ABSTRACT = "isAbstract";
	public static final String FLOW_PROPERTY = "flowProperty";
	public static final String INPUT = "input";
	public static final String OWNED_ACTION = "ownedAction";
	public static final String NAME = "name";
	public static final String OWNED_USAGE = "ownedUsage";
	public static final String OWNED_MEMBER = "ownedMember";
	public static final String OWNED_MEMBERSHIP = "ownedMembership";

}

