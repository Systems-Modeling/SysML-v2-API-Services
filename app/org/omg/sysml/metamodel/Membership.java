package org.omg.sysml.metamodel;

import java.util.Collection;

public interface Membership extends Relationship, MofObject {
    String getMemberName();

    VisibilityKind getVisibility();

    Collection<? extends String> getAliases();

    Element getMemberElement();

    Element getOwnedMemberElement();

    Package getMembershipOwningPackage();
}