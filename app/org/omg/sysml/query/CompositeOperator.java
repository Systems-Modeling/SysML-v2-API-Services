package org.omg.sysml.query;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CompositeOperator {
    @JsonProperty("and")
    AND,
    @JsonProperty("or")
    OR;
}
