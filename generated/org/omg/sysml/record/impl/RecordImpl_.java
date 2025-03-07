package org.omg.sysml.record.impl;

import java.util.UUID;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RecordImpl.class)
public abstract class RecordImpl_ {

	public static volatile SingularAttribute<RecordImpl, String> name;
	public static volatile SetAttribute<RecordImpl, String> alias;
	public static volatile SingularAttribute<RecordImpl, String> description;
	public static volatile SingularAttribute<RecordImpl, UUID> id;

	public static final String NAME = "name";
	public static final String ALIAS = "alias";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";

}

