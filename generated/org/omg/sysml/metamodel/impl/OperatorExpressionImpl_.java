package org.omg.sysml.metamodel.impl;

import java.util.UUID;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.omg.sysml.metamodel.Expression;
import org.omg.sysml.metamodel.FeatureTyping;
import org.omg.sysml.metamodel.Relationship;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OperatorExpressionImpl.class)
public abstract class OperatorExpressionImpl_ extends org.omg.sysml.metamodel.impl.MofObjectImpl_ {

	public static volatile SingularAttribute<OperatorExpressionImpl, UUID> identifier;
	public static volatile CollectionAttribute<OperatorExpressionImpl, Relationship> ownedRelationship;
	public static volatile SingularAttribute<OperatorExpressionImpl, Boolean> isUnique;
	public static volatile CollectionAttribute<OperatorExpressionImpl, FeatureTyping> typing;
	public static volatile SingularAttribute<OperatorExpressionImpl, Boolean> isAbstract;
	public static volatile SingularAttribute<OperatorExpressionImpl, Boolean> isOrdered;
	public static volatile SingularAttribute<OperatorExpressionImpl, String> operator;
	public static volatile ListAttribute<OperatorExpressionImpl, Expression> operand;

	public static final String IDENTIFIER = "identifier";
	public static final String OWNED_RELATIONSHIP = "ownedRelationship";
	public static final String IS_UNIQUE = "isUnique";
	public static final String TYPING = "typing";
	public static final String IS_ABSTRACT = "isAbstract";
	public static final String IS_ORDERED = "isOrdered";
	public static final String OPERATOR = "operator";
	public static final String OPERAND = "operand";

}

