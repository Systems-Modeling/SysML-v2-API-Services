package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface Connector extends Feature, Relationship, MofObject {
    List<? extends Feature> getRelatedFeature();

    Collection<? extends Association> getAssociation();

    Boolean getIsDirected();

    Collection<? extends Association> getOwnedAssociationType();

    Collection<? extends Feature> getConnectorEnd();

    Feature getSourceFeature();

    Collection<? extends Feature> getTargetFeature();
}