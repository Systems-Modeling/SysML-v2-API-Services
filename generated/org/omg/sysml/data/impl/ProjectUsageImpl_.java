package org.omg.sysml.data.impl;

import java.util.UUID;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.omg.sysml.lifecycle.impl.CommitImpl;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProjectUsageImpl.class)
public abstract class ProjectUsageImpl_ extends org.omg.sysml.lifecycle.impl.DataImpl_ {

	public static volatile SingularAttribute<ProjectUsageImpl, CommitImpl> usedProjectCommit;
	public static volatile SingularAttribute<ProjectUsageImpl, UUID> id;

	public static final String USED_PROJECT_COMMIT = "usedProjectCommit";
	public static final String ID = "id";

}

