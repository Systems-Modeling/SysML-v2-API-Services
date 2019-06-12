package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface Association extends Class, Relationship, MofObject {
    Collection<? extends Category> getRelatedType();

    List<? extends EndFeatureMembership> getOwnedEndFeatureMembership();

    Connector getOwningConnector();
}