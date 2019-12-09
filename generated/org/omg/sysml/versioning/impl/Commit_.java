package org.omg.sysml.versioning.impl;

import java.time.ZonedDateTime;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.omg.sysml.extension.impl.ProjectImpl;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Commit.class)
public abstract class Commit_ extends org.omg.sysml.versioning.impl.Record_ {

	public static volatile SetAttribute<Commit, ElementRecordImpl> changes;
	public static volatile SingularAttribute<Commit, ProjectImpl> project;
	public static volatile SingularAttribute<Commit, ZonedDateTime> timestamp;

	public static final String CHANGES = "changes";
	public static final String PROJECT = "project";
	public static final String TIMESTAMP = "timestamp";

}

