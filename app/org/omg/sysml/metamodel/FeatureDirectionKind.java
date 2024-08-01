package org.omg.sysml.metamodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum FeatureDirectionKind {
    @JsonProperty("in") IN,
    @JsonProperty("inout") INOUT,
    @JsonProperty("out") OUT
}