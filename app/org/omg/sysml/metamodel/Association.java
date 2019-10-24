package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface Association extends Class, Relationship, MofObject {
    Collection<? extends Type> getRelatedType();

    Connector getOwningConnector();

    Collection<? extends Feature> getAssociationEnd();
}