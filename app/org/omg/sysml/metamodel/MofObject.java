package org.omg.sysml.metamodel;

public interface MofObject {
    java.util.UUID getId();

    // TODO Remove temporary modification for prototyping Project concept

    org.omg.sysml.extension.Project getContainingProject();
}