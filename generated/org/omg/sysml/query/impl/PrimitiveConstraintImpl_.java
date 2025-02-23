package org.omg.sysml.query.impl;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.omg.sysml.query.PrimitiveOperator;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PrimitiveConstraintImpl.class)
public abstract class PrimitiveConstraintImpl_ extends org.omg.sysml.query.impl.ConstraintImpl_ {

	public static volatile SingularAttribute<PrimitiveConstraintImpl, Boolean> inverse;
	public static volatile SingularAttribute<PrimitiveConstraintImpl, String> property;
	public static volatile ListAttribute<PrimitiveConstraintImpl, String> value;
	public static volatile SingularAttribute<PrimitiveConstraintImpl, PrimitiveOperator> operator;

	public static final String INVERSE = "inverse";
	public static final String PROPERTY = "property";
	public static final String VALUE = "value";
	public static final String OPERATOR = "operator";

}

