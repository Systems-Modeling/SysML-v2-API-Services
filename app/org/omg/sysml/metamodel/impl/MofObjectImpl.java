package org.omg.sysml.metamodel.impl;

import com.fasterxml.jackson.annotation.*;

import org.omg.sysml.metamodel.MofObject;

//import info.archinnov.achilles.annotations.PartitionKey;

import java.util.UUID;
import javax.persistence.*;

@Entity(name = "MofObjectImpl")
@Table(name = "MofObject")
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
public abstract class MofObjectImpl implements MofObject {
    //@PartitionKey
    private UUID key;

    @Id
    @GeneratedValue(generator = "UseExistingOrGenerateUUIDGenerator")
    @Column(name = "key")
    @JsonIgnore
    public UUID getKey() {
        return key;
    }

    public void setKey(UUID key) {
        this.key = key;
    }

    public abstract java.util.UUID getIdentifier();

    public abstract void setIdentifier(java.util.UUID identifier);

    //@PartitionKey
    private java.util.UUID id;

    @Transient
    @JsonGetter(value = "@id")
    public java.util.UUID getId_() {
        return getIdentifier();
    }

    @JsonSetter(value = "@id")
    public void setId_(java.util.UUID id_) {
        setIdentifier(id_);
    }
}
