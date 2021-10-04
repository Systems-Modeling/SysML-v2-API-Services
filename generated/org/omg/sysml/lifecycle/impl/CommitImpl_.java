package org.omg.sysml.lifecycle.impl;

import java.time.ZonedDateTime;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CommitImpl.class)
public abstract class CommitImpl_ extends org.omg.sysml.record.impl.RecordImpl_ {

	public static volatile SetAttribute<CommitImpl, DataVersionImpl> change;
	public static volatile SingularAttribute<CommitImpl, CommitImpl> previousCommit;
	public static volatile SingularAttribute<CommitImpl, ProjectImpl> owningProject;
	public static volatile SingularAttribute<CommitImpl, ZonedDateTime> timestamp;

	public static final String CHANGE = "change";
	public static final String PREVIOUS_COMMIT = "previousCommit";
	public static final String OWNING_PROJECT = "owningProject";
	public static final String TIMESTAMP = "timestamp";

}

