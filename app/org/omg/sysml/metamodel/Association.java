package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;

public interface Association extends Class, Relationship, MofObject {
    Collection<? extends Category> getRelatedType();

    List<? extends EndFeatureMembership> getOwnedEndFeatureMembership();

    Connector getOwningConnector();
}