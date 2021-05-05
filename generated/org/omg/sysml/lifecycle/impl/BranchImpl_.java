package org.omg.sysml.lifecycle.impl;

import java.time.ZonedDateTime;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BranchImpl.class)
public abstract class BranchImpl_ extends org.omg.sysml.record.impl.RecordImpl_ {

	public static volatile SingularAttribute<BranchImpl, CommitImpl> head;
	public static volatile SingularAttribute<BranchImpl, String> name;
	public static volatile SingularAttribute<BranchImpl, ProjectImpl> owningProject;
	public static volatile SingularAttribute<BranchImpl, ZonedDateTime> timestamp;

	public static final String HEAD = "head";
	public static final String NAME = "name";
	public static final String OWNING_PROJECT = "owningProject";
	public static final String TIMESTAMP = "timestamp";

}

