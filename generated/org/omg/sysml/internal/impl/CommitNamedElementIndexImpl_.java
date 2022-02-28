package org.omg.sysml.internal.impl;

import java.util.UUID;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.MapAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.omg.sysml.lifecycle.impl.CommitImpl;
import org.omg.sysml.metamodel.Element;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CommitNamedElementIndexImpl.class)
public abstract class CommitNamedElementIndexImpl_ {

	public static volatile MapAttribute<CommitNamedElementIndexImpl, String, Element> workingNamedElement;
	public static volatile SingularAttribute<CommitNamedElementIndexImpl, CommitImpl> commit;
	public static volatile SingularAttribute<CommitNamedElementIndexImpl, UUID> id;

	public static final String WORKING_NAMED_ELEMENT = "workingNamedElement";
	public static final String COMMIT = "commit";
	public static final String ID = "id";

}

