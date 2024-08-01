package org.omg.sysml.metamodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum StateSubactionKind {
    @JsonProperty("do") DO,
    @JsonProperty("entry") ENTRY,
    @JsonProperty("exit") EXIT
}