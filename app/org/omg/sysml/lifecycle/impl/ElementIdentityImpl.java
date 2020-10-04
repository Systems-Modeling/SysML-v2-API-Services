package org.omg.sysml.lifecycle.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.omg.sysml.lifecycle.ElementIdentity;
import org.omg.sysml.record.impl.RecordImpl;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity(name = "ElementIdentity")
@JsonTypeName(value = "ElementIdentity")
public class ElementIdentityImpl extends RecordImpl implements ElementIdentity {
    @Transient
    @JsonProperty("@type")
    public String getType() {
        return ElementIdentity.class.getSimpleName();
    }
}
