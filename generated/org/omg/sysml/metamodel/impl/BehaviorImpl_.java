package org.omg.sysml.metamodel.impl;

import java.util.UUID;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.omg.sysml.metamodel.Feature;
import org.omg.sysml.metamodel.Relationship;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BehaviorImpl.class)
public abstract class BehaviorImpl_ extends org.omg.sysml.metamodel.impl.MofObjectImpl_ {

	public static volatile SingularAttribute<BehaviorImpl, UUID> identifier;
	public static volatile CollectionAttribute<BehaviorImpl, Relationship> ownedRelationship;
	public static volatile SingularAttribute<BehaviorImpl, Boolean> isAbstract;
	public static volatile CollectionAttribute<BehaviorImpl, Feature> involvesFeature;

	public static final String IDENTIFIER = "identifier";
	public static final String OWNED_RELATIONSHIP = "ownedRelationship";
	public static final String IS_ABSTRACT = "isAbstract";
	public static final String INVOLVES_FEATURE = "involvesFeature";

}

