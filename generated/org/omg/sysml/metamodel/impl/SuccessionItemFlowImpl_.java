package org.omg.sysml.metamodel.impl;

import java.util.UUID;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.omg.sysml.metamodel.Class;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.Feature;
import org.omg.sysml.metamodel.FeatureTyping;
import org.omg.sysml.metamodel.Relationship;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SuccessionItemFlowImpl.class)
public abstract class SuccessionItemFlowImpl_ extends org.omg.sysml.metamodel.impl.MofObjectImpl_ {

	public static volatile SingularAttribute<SuccessionItemFlowImpl, UUID> identifier;
	public static volatile ListAttribute<SuccessionItemFlowImpl, Class> itemType;
	public static volatile CollectionAttribute<SuccessionItemFlowImpl, Relationship> ownedRelationship;
	public static volatile SingularAttribute<SuccessionItemFlowImpl, Boolean> isUnique;
	public static volatile CollectionAttribute<SuccessionItemFlowImpl, Element> ownedRelatedElement;
	public static volatile CollectionAttribute<SuccessionItemFlowImpl, Element> source;
	public static volatile SingularAttribute<SuccessionItemFlowImpl, Boolean> isAbstract;
	public static volatile CollectionAttribute<SuccessionItemFlowImpl, Element> target;
	public static volatile ListAttribute<SuccessionItemFlowImpl, Feature> targetInputFeature;
	public static volatile ListAttribute<SuccessionItemFlowImpl, Feature> sourceOutputFeature;
	public static volatile SingularAttribute<SuccessionItemFlowImpl, Boolean> isDirected;
	public static volatile CollectionAttribute<SuccessionItemFlowImpl, FeatureTyping> typing;
	public static volatile SingularAttribute<SuccessionItemFlowImpl, Boolean> isOrdered;

	public static final String IDENTIFIER = "identifier";
	public static final String ITEM_TYPE = "itemType";
	public static final String OWNED_RELATIONSHIP = "ownedRelationship";
	public static final String IS_UNIQUE = "isUnique";
	public static final String OWNED_RELATED_ELEMENT = "ownedRelatedElement";
	public static final String SOURCE = "source";
	public static final String IS_ABSTRACT = "isAbstract";
	public static final String TARGET = "target";
	public static final String TARGET_INPUT_FEATURE = "targetInputFeature";
	public static final String SOURCE_OUTPUT_FEATURE = "sourceOutputFeature";
	public static final String IS_DIRECTED = "isDirected";
	public static final String TYPING = "typing";
	public static final String IS_ORDERED = "isOrdered";

}

