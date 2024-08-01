package org.omg.sysml.metamodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum RequirementConstraintKind {
    @JsonProperty("assumption") ASSUMPTION,
    @JsonProperty("requirement") REQUIREMENT
}