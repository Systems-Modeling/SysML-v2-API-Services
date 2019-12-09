package org.omg.sysml.metamodel.impl;

import com.fasterxml.jackson.annotation.*;

import org.omg.sysml.metamodel.MofObject;

//import info.archinnov.achilles.annotations.PartitionKey;

import javax.persistence.*;

// TODO Remove temporary modification for prototyping Project concept

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jackson.MofObjectDeserializer;
import jackson.MofObjectSerializer;
import org.hibernate.annotations.Any;

@Entity(name = "MofObjectImpl")
@Table(name = "MofObject")
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
public abstract class MofObjectImpl implements MofObject {
    //@PartitionKey
    public java.util.UUID id;

    @Id
    // TODO Abstract this concept to cli option
    @GeneratedValue(generator = "UseExistingOrGenerateUUIDGenerator")
    @Column(name = "id")
    @JsonGetter(value = "id")
    public java.util.UUID getId() {
        return id;
    }

    @JsonSetter(value = "id")
    public void setId(java.util.UUID id) {
        this.id = id;
    }

    // TODO Remove temporary modification for prototyping Project concept

    private org.omg.sysml.extension.Project containingProject;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "ProjectMetaDef", metaColumn = @javax.persistence.Column(name = "containingProjectType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "containingProjectId", table = "MofObject")
    public org.omg.sysml.extension.Project getContainingProject() {
        return containingProject;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = org.omg.sysml.extension.impl.ProjectImpl.class)
    public void setContainingProject(org.omg.sysml.extension.Project containingProject) {
        this.containingProject = containingProject;
    }

    // TODO Remove hardcoding for identifier

    // @info.archinnov.achilles.annotations.Column("identifier")
    private java.util.UUID identifier;

    @JsonGetter
    @javax.persistence.Column(name = "identifier", table = "MofObject")
    public java.util.UUID getIdentifier() {
        return identifier;
    }

    @JsonSetter
    public void setIdentifier(java.util.UUID identifier) {
        this.identifier = identifier;
    }
}
