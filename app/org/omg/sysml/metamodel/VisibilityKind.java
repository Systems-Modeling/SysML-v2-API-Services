package org.omg.sysml.metamodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum VisibilityKind {
    @JsonProperty("private") PRIVATE,
    @JsonProperty("protected") PROTECTED,
    @JsonProperty("public") PUBLIC
}