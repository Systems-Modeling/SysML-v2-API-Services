package org.omg.sysml.external.impl;

import java.net.URI;
import java.util.UUID;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ExternalDataImpl.class)
public abstract class ExternalDataImpl_ extends org.omg.sysml.lifecycle.impl.DataImpl_ {

	public static volatile SingularAttribute<ExternalDataImpl, URI> resourceIdentifier;
	public static volatile SingularAttribute<ExternalDataImpl, UUID> id;

	public static final String RESOURCE_IDENTIFIER = "resourceIdentifier";
	public static final String ID = "id";

}

