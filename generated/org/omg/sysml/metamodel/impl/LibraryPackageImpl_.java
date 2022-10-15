package org.omg.sysml.metamodel.impl;

import java.util.UUID;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.omg.sysml.metamodel.Annotation;
import org.omg.sysml.metamodel.Documentation;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.Expression;
import org.omg.sysml.metamodel.Import;
import org.omg.sysml.metamodel.Membership;
import org.omg.sysml.metamodel.Relationship;
import org.omg.sysml.metamodel.TextualRepresentation;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(LibraryPackageImpl.class)
public abstract class LibraryPackageImpl_ extends org.omg.sysml.lifecycle.impl.DataImpl_ {

	public static volatile SingularAttribute<LibraryPackageImpl, UUID> elementId;
	public static volatile ListAttribute<LibraryPackageImpl, TextualRepresentation> textualRepresentation;
	public static volatile ListAttribute<LibraryPackageImpl, Membership> importedMembership;
	public static volatile SingularAttribute<LibraryPackageImpl, Boolean> isLibraryElement;
	public static volatile ListAttribute<LibraryPackageImpl, Annotation> ownedAnnotation;
	public static volatile ListAttribute<LibraryPackageImpl, Element> ownedElement;
	public static volatile ListAttribute<LibraryPackageImpl, String> aliasIds;
	public static volatile ListAttribute<LibraryPackageImpl, Relationship> ownedRelationship;
	public static volatile SingularAttribute<LibraryPackageImpl, String> qualifiedName;
	public static volatile ListAttribute<LibraryPackageImpl, Documentation> documentation;
	public static volatile ListAttribute<LibraryPackageImpl, Membership> membership;
	public static volatile ListAttribute<LibraryPackageImpl, Import> ownedImport;
	public static volatile SingularAttribute<LibraryPackageImpl, Boolean> isImpliedIncluded;
	public static volatile SingularAttribute<LibraryPackageImpl, Boolean> isStandard;
	public static volatile ListAttribute<LibraryPackageImpl, Expression> filterCondition;
	public static volatile ListAttribute<LibraryPackageImpl, Element> member;
	public static volatile SingularAttribute<LibraryPackageImpl, String> name;
	public static volatile SingularAttribute<LibraryPackageImpl, String> shortName;
	public static volatile SingularAttribute<LibraryPackageImpl, String> effectiveName;
	public static volatile ListAttribute<LibraryPackageImpl, Element> ownedMember;
	public static volatile ListAttribute<LibraryPackageImpl, Membership> ownedMembership;

	public static final String ELEMENT_ID = "elementId";
	public static final String TEXTUAL_REPRESENTATION = "textualRepresentation";
	public static final String IMPORTED_MEMBERSHIP = "importedMembership";
	public static final String IS_LIBRARY_ELEMENT = "isLibraryElement";
	public static final String OWNED_ANNOTATION = "ownedAnnotation";
	public static final String OWNED_ELEMENT = "ownedElement";
	public static final String ALIAS_IDS = "aliasIds";
	public static final String OWNED_RELATIONSHIP = "ownedRelationship";
	public static final String QUALIFIED_NAME = "qualifiedName";
	public static final String DOCUMENTATION = "documentation";
	public static final String MEMBERSHIP = "membership";
	public static final String OWNED_IMPORT = "ownedImport";
	public static final String IS_IMPLIED_INCLUDED = "isImpliedIncluded";
	public static final String IS_STANDARD = "isStandard";
	public static final String FILTER_CONDITION = "filterCondition";
	public static final String MEMBER = "member";
	public static final String NAME = "name";
	public static final String SHORT_NAME = "shortName";
	public static final String EFFECTIVE_NAME = "effectiveName";
	public static final String OWNED_MEMBER = "ownedMember";
	public static final String OWNED_MEMBERSHIP = "ownedMembership";

}

