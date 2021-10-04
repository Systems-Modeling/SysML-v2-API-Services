package org.omg.sysml.external.impl;

import java.util.UUID;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ExternalRelationshipImpl.class)
public abstract class ExternalRelationshipImpl_ extends org.omg.sysml.lifecycle.impl.DataImpl_ {

	public static volatile SingularAttribute<ExternalRelationshipImpl, String> specification;
	public static volatile SingularAttribute<ExternalRelationshipImpl, String> language;
	public static volatile SingularAttribute<ExternalRelationshipImpl, UUID> id;
	public static volatile SingularAttribute<ExternalRelationshipImpl, ExternalDataImpl> externalDataEnd;

	public static final String SPECIFICATION = "specification";
	public static final String LANGUAGE = "language";
	public static final String ID = "id";
	public static final String EXTERNAL_DATA_END = "externalDataEnd";

}

