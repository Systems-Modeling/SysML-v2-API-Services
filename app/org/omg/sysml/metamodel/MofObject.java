package org.omg.sysml.metamodel;

import org.omg.sysml.extension.Model;

public interface MofObject {
    java.util.UUID getIdentifier();

    // TODO Remove temporary modification for prototyping Model concept

    Model getContainingModel();
}