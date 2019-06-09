package org.omg.sysml.metamodel.impl;

import java.util.UUID;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.omg.sysml.metamodel.FeatureTyping;
import org.omg.sysml.metamodel.Relationship;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(LiteralRealImpl.class)
public abstract class LiteralRealImpl_ extends org.omg.sysml.metamodel.impl.MofObjectImpl_ {

	public static volatile SingularAttribute<LiteralRealImpl, UUID> identifier;
	public static volatile CollectionAttribute<LiteralRealImpl, Relationship> ownedRelationship;
	public static volatile SingularAttribute<LiteralRealImpl, Boolean> isUnique;
	public static volatile CollectionAttribute<LiteralRealImpl, FeatureTyping> typing;
	public static volatile SingularAttribute<LiteralRealImpl, Double> value;
	public static volatile SingularAttribute<LiteralRealImpl, Boolean> isAbstract;
	public static volatile SingularAttribute<LiteralRealImpl, Boolean> isOrdered;

	public static final String IDENTIFIER = "identifier";
	public static final String OWNED_RELATIONSHIP = "ownedRelationship";
	public static final String IS_UNIQUE = "isUnique";
	public static final String TYPING = "typing";
	public static final String VALUE = "value";
	public static final String IS_ABSTRACT = "isAbstract";
	public static final String IS_ORDERED = "isOrdered";

}

