package org.omg.sysml.query;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum PrimitiveOperator {

    @JsonProperty("instanceOf")
    INSTANCE_OF,
    @JsonProperty("=")
    EQUALS,
    @JsonProperty(">")
    GREATER_THAN,
    @JsonProperty("<")
    LESS_THAN,
    @JsonProperty("in")
    IN;
}
