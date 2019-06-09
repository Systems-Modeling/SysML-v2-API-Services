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
@StaticMetamodel(ItemFlowImpl.class)
public abstract class ItemFlowImpl_ extends org.omg.sysml.metamodel.impl.MofObjectImpl_ {

	public static volatile SingularAttribute<ItemFlowImpl, UUID> identifier;
	public static volatile ListAttribute<ItemFlowImpl, Class> itemType;
	public static volatile CollectionAttribute<ItemFlowImpl, Relationship> ownedRelationship;
	public static volatile SingularAttribute<ItemFlowImpl, Boolean> isUnique;
	public static volatile CollectionAttribute<ItemFlowImpl, Element> ownedRelatedElement;
	public static volatile CollectionAttribute<ItemFlowImpl, Element> source;
	public static volatile SingularAttribute<ItemFlowImpl, Boolean> isAbstract;
	public static volatile CollectionAttribute<ItemFlowImpl, Element> target;
	public static volatile ListAttribute<ItemFlowImpl, Feature> targetInputFeature;
	public static volatile ListAttribute<ItemFlowImpl, Feature> sourceOutputFeature;
	public static volatile SingularAttribute<ItemFlowImpl, Boolean> isDirected;
	public static volatile CollectionAttribute<ItemFlowImpl, FeatureTyping> typing;
	public static volatile SingularAttribute<ItemFlowImpl, Boolean> isOrdered;

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

