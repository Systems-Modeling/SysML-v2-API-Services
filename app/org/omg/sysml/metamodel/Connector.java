package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface Connector extends Feature, Relationship, MofObject {
    Collection<? extends Feature> getRelatedFeature();

    Association getAssociation();

    Boolean getIsDirected();

    Association getOwnedAssociationType();

    Collection<? extends Feature> getConnectorEnd();
}