package org.omg.sysml.metamodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TriggerKind {
    @JsonProperty("after") AFTER,
    @JsonProperty("at") AT,
    @JsonProperty("when") WHEN
}