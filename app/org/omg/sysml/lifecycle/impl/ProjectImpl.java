package org.omg.sysml.lifecycle.impl;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.omg.sysml.lifecycle.Project;
import org.omg.sysml.record.impl.RecordImpl;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Transient;

// import info.archinnov.achilles.annotations.UDT;

@Entity(name = "Project")
public class ProjectImpl extends RecordImpl implements Project {
    private String name;

    @JsonProperty(required = true)
    @JsonGetter
    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    @javax.persistence.Column(name = "name", table = "Project")
    public String getName() {
        return name;
    }

    @JsonProperty(required = true)
    @JsonSetter
    public void setName(String name) {
        this.name = name;
    }

    @Transient
    @JsonProperty("@type")
    public String getType() {
        return Project.class.getSimpleName();
    }
}
