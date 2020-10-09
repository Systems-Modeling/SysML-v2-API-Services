package org.omg.sysml.query.impl;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.omg.sysml.query.CompositeOperator;
import org.omg.sysml.query.Constraint;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CompositeConstraintImpl.class)
public abstract class CompositeConstraintImpl_ extends org.omg.sysml.query.impl.ConstraintImpl_ {

	public static volatile CollectionAttribute<CompositeConstraintImpl, Constraint> constraint;
	public static volatile SingularAttribute<CompositeConstraintImpl, CompositeOperator> operator;

	public static final String CONSTRAINT = "constraint";
	public static final String OPERATOR = "operator";

}

