package org.omg.sysml.metamodel;

public interface Import extends Relationship, MofObject {
    Package getImportedPackage();

    Predicate getSelecter();

    Package getImportOwningPackage();

    VisibilityKind getVisibility();
}