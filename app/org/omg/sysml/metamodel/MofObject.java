package org.omg.sysml.metamodel;

public interface MofObject {
    java.util.UUID getIdentifier();

    // TODO Remove temporary modification for prototyping Model concept

    org.omg.sysml.extension.Model getContainingModel();
}