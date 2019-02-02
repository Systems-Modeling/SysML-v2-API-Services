package models;

import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Element.class)
public abstract class Element_ {

	public static volatile SingularAttribute<Element, String> name;
	public static volatile SingularAttribute<Element, Model> model;
	public static volatile SingularAttribute<Element, UUID> id;
	public static volatile SingularAttribute<Element, String> type;

	public static final String NAME = "name";
	public static final String MODEL = "model";
	public static final String ID = "id";
	public static final String TYPE = "type";

}

