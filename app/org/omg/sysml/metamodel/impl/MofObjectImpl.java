package org.omg.sysml.metamodel.impl;

import com.fasterxml.jackson.annotation.*;

import org.omg.sysml.extension.Model;
import org.omg.sysml.extension.impl.ModelImpl;
import org.omg.sysml.metamodel.MofObject;

//import info.archinnov.achilles.annotations.PartitionKey;

import javax.persistence.*;

// TODO Remove temporary modification for prototyping Model concept

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
    public java.util.UUID identifier;

    @Id
    @Column(name = "identifier")
    @JsonGetter(value = "identifier")
    public java.util.UUID getIdentifier() {
        return identifier;
    }

    @JsonSetter(value = "identifier")
    public void setIdentifier(java.util.UUID identifier) {
        this.identifier = identifier;
    }

    // TODO Remove temporary modification for prototyping Model concept

    private Model containingModel;

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "ModelMetaDef", metaColumn = @javax.persistence.Column(name = "containingModelType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "containingModelId", table = "MofObject")
    public Model getContainingModel() {
        return containingModel;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = ModelImpl.class)
    public void setContainingModel(Model containingModel) {
        this.containingModel = containingModel;
    }
}
