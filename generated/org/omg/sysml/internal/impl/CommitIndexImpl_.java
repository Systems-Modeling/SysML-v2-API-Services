package org.omg.sysml.internal.impl;

import java.util.UUID;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.omg.sysml.lifecycle.impl.CommitImpl;
import org.omg.sysml.lifecycle.impl.DataVersionImpl;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CommitIndexImpl.class)
public abstract class CommitIndexImpl_ {

	public static volatile SingularAttribute<CommitIndexImpl, CommitImpl> commit;
	public static volatile SingularAttribute<CommitIndexImpl, UUID> id;
	public static volatile SetAttribute<CommitIndexImpl, DataVersionImpl> workingDataVersions;

	public static final String COMMIT = "commit";
	public static final String ID = "id";
	public static final String WORKING_DATA_VERSIONS = "workingDataVersions";

}

