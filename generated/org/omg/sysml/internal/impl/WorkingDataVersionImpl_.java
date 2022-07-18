package org.omg.sysml.internal.impl;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.omg.sysml.data.impl.ProjectUsageImpl;
import org.omg.sysml.lifecycle.impl.DataVersionImpl;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(WorkingDataVersionImpl.class)
public abstract class WorkingDataVersionImpl_ extends org.omg.sysml.record.impl.RecordImpl_ {

	public static volatile SingularAttribute<WorkingDataVersionImpl, DataVersionImpl> dataVersion;
	public static volatile SingularAttribute<WorkingDataVersionImpl, ProjectUsageImpl> source;

	public static final String DATA_VERSION = "dataVersion";
	public static final String SOURCE = "source";

}

