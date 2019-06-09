package org.omg.sysml.metamodel;

import java.util.Collection;

public interface Interaction extends Behavior, Association, MofObject {
    Collection<? extends Feature> getParticipantFeature();
}