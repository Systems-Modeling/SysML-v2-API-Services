package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface Import extends Relationship {
    Package getImportedPackage();

    Package getImportOwningPackage();

    VisibilityKind getVisibility();
}