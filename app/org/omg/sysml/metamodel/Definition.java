package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface Definition extends Classifier, MofObject {
    Collection<? extends Usage> getOwnedUsage();

    Collection<? extends PortUsage> getOwnedPort();

    Collection<? extends Property> getFlowProperty();

    Collection<? extends Property> getOwnedProperty();

    Collection<? extends Property> getProperty();

    Collection<? extends ActionUsage> getOwnedAction();
}