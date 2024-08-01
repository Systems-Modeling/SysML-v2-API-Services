package org.omg.sysml.metamodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TransitionFeatureKind {
    @JsonProperty("effect") EFFECT,
    @JsonProperty("guard") GUARD,
    @JsonProperty("trigger") TRIGGER
}