package org.omg.sysml.lifecycle.impl;

import java.time.ZonedDateTime;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TagImpl.class)
public abstract class TagImpl_ extends org.omg.sysml.record.impl.RecordImpl_ {

	public static volatile SingularAttribute<TagImpl, CommitImpl> taggedCommit;
	public static volatile SingularAttribute<TagImpl, String> name;
	public static volatile SingularAttribute<TagImpl, ProjectImpl> owningProject;
	public static volatile SingularAttribute<TagImpl, ZonedDateTime> timestamp;

	public static final String TAGGED_COMMIT = "taggedCommit";
	public static final String NAME = "name";
	public static final String OWNING_PROJECT = "owningProject";
	public static final String TIMESTAMP = "timestamp";

}

