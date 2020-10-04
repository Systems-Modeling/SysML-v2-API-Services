package org.omg.sysml.query;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum PrimitiveOperator {
    @JsonProperty("=")
    EQUALS,
    @JsonProperty(">")
    GREATER_THAN,
    @JsonProperty("<")
    LESS_THAN;
}
