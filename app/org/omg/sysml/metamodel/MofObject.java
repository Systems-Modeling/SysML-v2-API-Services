package org.omg.sysml.metamodel;

import org.omg.sysml.lifecycle.Project;

public interface MofObject {
    java.util.UUID getId();

    // TODO Remove temporary modification for prototyping Project concept

    Project getContainingProject();
}