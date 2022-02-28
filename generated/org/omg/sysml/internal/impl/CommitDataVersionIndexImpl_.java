package org.omg.sysml.internal.impl;

import java.util.UUID;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.omg.sysml.lifecycle.impl.CommitImpl;
import org.omg.sysml.lifecycle.impl.DataVersionImpl;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CommitDataVersionIndexImpl.class)
public abstract class CommitDataVersionIndexImpl_ {

	public static volatile SingularAttribute<CommitDataVersionIndexImpl, CommitImpl> commit;
	public static volatile SetAttribute<CommitDataVersionIndexImpl, DataVersionImpl> workingDataVersion;
	public static volatile SingularAttribute<CommitDataVersionIndexImpl, UUID> id;

	public static final String COMMIT = "commit";
	public static final String WORKING_DATA_VERSION = "workingDataVersion";
	public static final String ID = "id";

}

