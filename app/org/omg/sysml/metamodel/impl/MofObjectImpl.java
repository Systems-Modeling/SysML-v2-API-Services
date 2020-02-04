package org.omg.sysml.metamodel.impl;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jackson.RecordSerialization;
import org.hibernate.annotations.Any;
import org.omg.sysml.lifecycle.Project;
import org.omg.sysml.lifecycle.impl.ProjectImpl;
import org.omg.sysml.metamodel.MofObject;

import javax.persistence.*;

//import info.archinnov.achilles.annotations.PartitionKey;
// TODO Remove temporary modification for prototyping Project concept

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

    private Project containingProject;

    @JsonGetter
    @JsonSerialize(using = RecordSerialization.RecordSerializer.class)
    @Any(metaDef = "ProjectMetaDef", metaColumn = @javax.persistence.Column(name = "containingProjectType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "containingProjectId", table = "MofObject")
    public Project getContainingProject() {
        return containingProject;
    }

    @JsonSetter
    @JsonDeserialize(using = RecordSerialization.ProjectDeserializer.class, as = ProjectImpl.class)
    public void setContainingProject(Project containingProject) {
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
