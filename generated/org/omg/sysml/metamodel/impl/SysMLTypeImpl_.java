package org.omg.sysml.metamodel.impl;

import java.util.UUID;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SysMLTypeImpl.class)
public abstract class SysMLTypeImpl_ {

	public static volatile SingularAttribute<SysMLTypeImpl, UUID> identifier;
	public static volatile SingularAttribute<SysMLTypeImpl, UUID> key;

	public static final String IDENTIFIER = "identifier";
	public static final String KEY = "key";

}

