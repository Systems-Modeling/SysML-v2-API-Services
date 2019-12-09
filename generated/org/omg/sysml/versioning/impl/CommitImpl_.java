package org.omg.sysml.versioning.impl;

import java.time.ZonedDateTime;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.omg.sysml.extension.impl.ProjectImpl;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CommitImpl.class)
public abstract class CommitImpl_ extends org.omg.sysml.versioning.impl.RecordImpl_ {

	public static volatile SetAttribute<CommitImpl, ElementRecordImpl> changes;
	public static volatile SingularAttribute<CommitImpl, ProjectImpl> containingProject;
	public static volatile SingularAttribute<CommitImpl, ZonedDateTime> timestamp;

	public static final String CHANGES = "changes";
	public static final String CONTAINING_PROJECT = "containingProject";
	public static final String TIMESTAMP = "timestamp";

}

