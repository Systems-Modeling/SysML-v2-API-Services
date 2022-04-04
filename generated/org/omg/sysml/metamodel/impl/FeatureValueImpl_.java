package org.omg.sysml.metamodel.impl;

import java.util.UUID;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.omg.sysml.metamodel.Annotation;
import org.omg.sysml.metamodel.Documentation;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.Relationship;
import org.omg.sysml.metamodel.TextualRepresentation;
import org.omg.sysml.metamodel.VisibilityKind;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FeatureValueImpl.class)
public abstract class FeatureValueImpl_ extends org.omg.sysml.lifecycle.impl.DataImpl_ {

	public static volatile ListAttribute<FeatureValueImpl, TextualRepresentation> textualRepresentation;
	public static volatile SingularAttribute<FeatureValueImpl, UUID> identifier;
	public static volatile ListAttribute<FeatureValueImpl, Annotation> ownedAnnotation;
	public static volatile ListAttribute<FeatureValueImpl, Element> ownedElement;
	public static volatile SingularAttribute<FeatureValueImpl, VisibilityKind> visibility;
	public static volatile ListAttribute<FeatureValueImpl, Relationship> ownedRelationship;
	public static volatile SingularAttribute<FeatureValueImpl, String> qualifiedName;
	public static volatile ListAttribute<FeatureValueImpl, Documentation> documentation;
	public static volatile SingularAttribute<FeatureValueImpl, Boolean> isInitial;
	public static volatile SingularAttribute<FeatureValueImpl, String> effectiveMemberName;
	public static volatile SingularAttribute<FeatureValueImpl, String> memberName;
	public static volatile ListAttribute<FeatureValueImpl, Element> ownedRelatedElement;
	public static volatile ListAttribute<FeatureValueImpl, Element> source;
	public static volatile ListAttribute<FeatureValueImpl, Element> target;
	public static volatile SingularAttribute<FeatureValueImpl, Boolean> isDefault;
	public static volatile SingularAttribute<FeatureValueImpl, String> humanId;
	public static volatile ListAttribute<FeatureValueImpl, String> aliasId;
	public static volatile ListAttribute<FeatureValueImpl, Element> relatedElement;
	public static volatile SingularAttribute<FeatureValueImpl, String> name;
	public static volatile SingularAttribute<FeatureValueImpl, String> effectiveName;

	public static final String TEXTUAL_REPRESENTATION = "textualRepresentation";
	public static final String IDENTIFIER = "identifier";
	public static final String OWNED_ANNOTATION = "ownedAnnotation";
	public static final String OWNED_ELEMENT = "ownedElement";
	public static final String VISIBILITY = "visibility";
	public static final String OWNED_RELATIONSHIP = "ownedRelationship";
	public static final String QUALIFIED_NAME = "qualifiedName";
	public static final String DOCUMENTATION = "documentation";
	public static final String IS_INITIAL = "isInitial";
	public static final String EFFECTIVE_MEMBER_NAME = "effectiveMemberName";
	public static final String MEMBER_NAME = "memberName";
	public static final String OWNED_RELATED_ELEMENT = "ownedRelatedElement";
	public static final String SOURCE = "source";
	public static final String TARGET = "target";
	public static final String IS_DEFAULT = "isDefault";
	public static final String HUMAN_ID = "humanId";
	public static final String ALIAS_ID = "aliasId";
	public static final String RELATED_ELEMENT = "relatedElement";
	public static final String NAME = "name";
	public static final String EFFECTIVE_NAME = "effectiveName";

}

