package org.omg.sysml.metamodel.impl;

import java.util.UUID;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.omg.sysml.metamodel.ActionUsage;
import org.omg.sysml.metamodel.DataType;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.Feature;
import org.omg.sysml.metamodel.FeatureMembership;
import org.omg.sysml.metamodel.FeatureTyping;
import org.omg.sysml.metamodel.Generalization;
import org.omg.sysml.metamodel.Import;
import org.omg.sysml.metamodel.Membership;
import org.omg.sysml.metamodel.PortUsage;
import org.omg.sysml.metamodel.Property;
import org.omg.sysml.metamodel.Redefinition;
import org.omg.sysml.metamodel.Relationship;
import org.omg.sysml.metamodel.Subsetting;
import org.omg.sysml.metamodel.Type;
import org.omg.sysml.metamodel.Usage;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ValuePropertyImpl.class)
public abstract class ValuePropertyImpl_ extends org.omg.sysml.metamodel.impl.MofObjectImpl_ {

	public static volatile ListAttribute<ValuePropertyImpl, Generalization> ownedGeneralization;
	public static volatile SingularAttribute<ValuePropertyImpl, Boolean> isUnique;
	public static volatile CollectionAttribute<ValuePropertyImpl, Usage> nestedUsage;
	public static volatile CollectionAttribute<ValuePropertyImpl, Subsetting> ownedSubsetting;
	public static volatile ListAttribute<ValuePropertyImpl, Membership> membership;
	public static volatile CollectionAttribute<ValuePropertyImpl, Type> type;
	public static volatile SingularAttribute<ValuePropertyImpl, Boolean> isNonunique;
	public static volatile CollectionAttribute<ValuePropertyImpl, Feature> output;
	public static volatile CollectionAttribute<ValuePropertyImpl, PortUsage> nestedPort;
	public static volatile CollectionAttribute<ValuePropertyImpl, Feature> feature;
	public static volatile CollectionAttribute<ValuePropertyImpl, DataType> valueType;
	public static volatile ListAttribute<ValuePropertyImpl, Membership> inheritedMembership;
	public static volatile CollectionAttribute<ValuePropertyImpl, Feature> ownedEndFeature;
	public static volatile CollectionAttribute<ValuePropertyImpl, Property> property;
	public static volatile ListAttribute<ValuePropertyImpl, Element> member;
	public static volatile ListAttribute<ValuePropertyImpl, FeatureMembership> ownedFeatureMembership;
	public static volatile SingularAttribute<ValuePropertyImpl, Boolean> isSufficient;
	public static volatile SingularAttribute<ValuePropertyImpl, Boolean> isOrdered;
	public static volatile CollectionAttribute<ValuePropertyImpl, Redefinition> ownedRedefinition;
	public static volatile ListAttribute<ValuePropertyImpl, Membership> importedMembership;
	public static volatile SingularAttribute<ValuePropertyImpl, UUID> identifier;
	public static volatile CollectionAttribute<ValuePropertyImpl, Element> ownedElement;
	public static volatile CollectionAttribute<ValuePropertyImpl, ActionUsage> nestedAction;
	public static volatile CollectionAttribute<ValuePropertyImpl, Feature> ownedFeature;
	public static volatile CollectionAttribute<ValuePropertyImpl, Property> nestedProperty;
	public static volatile CollectionAttribute<ValuePropertyImpl, Relationship> ownedRelationship;
	public static volatile CollectionAttribute<ValuePropertyImpl, Feature> endFeature;
	public static volatile CollectionAttribute<ValuePropertyImpl, Type> referencedType;
	public static volatile ListAttribute<ValuePropertyImpl, Import> ownedImport;
	public static volatile SingularAttribute<ValuePropertyImpl, Boolean> isAbstract;
	public static volatile SingularAttribute<ValuePropertyImpl, Boolean> isEnd;
	public static volatile CollectionAttribute<ValuePropertyImpl, Feature> input;
	public static volatile SingularAttribute<ValuePropertyImpl, Boolean> isComposite;
	public static volatile CollectionAttribute<ValuePropertyImpl, Type> ownedType;
	public static volatile SingularAttribute<ValuePropertyImpl, String> name;
	public static volatile CollectionAttribute<ValuePropertyImpl, FeatureTyping> typing;
	public static volatile ListAttribute<ValuePropertyImpl, Element> ownedMember;
	public static volatile ListAttribute<ValuePropertyImpl, Membership> ownedMembership;

	public static final String OWNED_GENERALIZATION = "ownedGeneralization";
	public static final String IS_UNIQUE = "isUnique";
	public static final String NESTED_USAGE = "nestedUsage";
	public static final String OWNED_SUBSETTING = "ownedSubsetting";
	public static final String MEMBERSHIP = "membership";
	public static final String TYPE = "type";
	public static final String IS_NONUNIQUE = "isNonunique";
	public static final String OUTPUT = "output";
	public static final String NESTED_PORT = "nestedPort";
	public static final String FEATURE = "feature";
	public static final String VALUE_TYPE = "valueType";
	public static final String INHERITED_MEMBERSHIP = "inheritedMembership";
	public static final String OWNED_END_FEATURE = "ownedEndFeature";
	public static final String PROPERTY = "property";
	public static final String MEMBER = "member";
	public static final String OWNED_FEATURE_MEMBERSHIP = "ownedFeatureMembership";
	public static final String IS_SUFFICIENT = "isSufficient";
	public static final String IS_ORDERED = "isOrdered";
	public static final String OWNED_REDEFINITION = "ownedRedefinition";
	public static final String IMPORTED_MEMBERSHIP = "importedMembership";
	public static final String IDENTIFIER = "identifier";
	public static final String OWNED_ELEMENT = "ownedElement";
	public static final String NESTED_ACTION = "nestedAction";
	public static final String OWNED_FEATURE = "ownedFeature";
	public static final String NESTED_PROPERTY = "nestedProperty";
	public static final String OWNED_RELATIONSHIP = "ownedRelationship";
	public static final String END_FEATURE = "endFeature";
	public static final String REFERENCED_TYPE = "referencedType";
	public static final String OWNED_IMPORT = "ownedImport";
	public static final String IS_ABSTRACT = "isAbstract";
	public static final String IS_END = "isEnd";
	public static final String INPUT = "input";
	public static final String IS_COMPOSITE = "isComposite";
	public static final String OWNED_TYPE = "ownedType";
	public static final String NAME = "name";
	public static final String TYPING = "typing";
	public static final String OWNED_MEMBER = "ownedMember";
	public static final String OWNED_MEMBERSHIP = "ownedMembership";

}

