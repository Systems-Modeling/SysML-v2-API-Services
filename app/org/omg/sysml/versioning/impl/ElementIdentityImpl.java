package org.omg.sysml.versioning.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.omg.sysml.versioning.ElementIdentity;

import javax.persistence.Entity;

@Entity(name = "ElementIdentity")
@JsonTypeName(value = "ElementIdentity")
public class ElementIdentityImpl extends RecordImpl implements ElementIdentity {
    @JsonProperty("@type")
    private final String type = ElementIdentity.class.getSimpleName();
}
