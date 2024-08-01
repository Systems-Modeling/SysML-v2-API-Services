package org.omg.sysml.metamodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum PortionKind {
    @JsonProperty("snapshot") SNAPSHOT,
    @JsonProperty("timeslice") TIMESLICE
}