package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface Membership extends Relationship, MofObject {
    String getMemberName();

    VisibilityKind getVisibility();

    Collection<? extends String> getAliases();

    Element getMemberElement();

    Element getOwnedMemberElement();

    Package getMembershipOwningPackage();
}