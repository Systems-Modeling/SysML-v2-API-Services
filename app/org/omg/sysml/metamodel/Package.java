package org.omg.sysml.metamodel;

import java.util.List;

public interface Package extends Element, MofObject {
    List<? extends Membership> getMembership();

    List<? extends Import> getOwnedImport();

    List<? extends Element> getMember();

    List<? extends Element> getOwnedMember();

    List<? extends Membership> getOwnedMembership();

    List<? extends Membership> getImportedMembership();
}