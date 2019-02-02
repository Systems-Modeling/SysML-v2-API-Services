package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Relationship.class)
public abstract class Relationship_ extends models.Element_ {

	public static volatile SingularAttribute<Relationship, Element> source;
	public static volatile SingularAttribute<Relationship, Element> target;

	public static final String SOURCE = "source";
	public static final String TARGET = "target";

}

