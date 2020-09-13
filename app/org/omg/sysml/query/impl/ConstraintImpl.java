package org.omg.sysml.query.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.omg.sysml.query.Constraint;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "Constraint_") // Constraint is a SQL keyword
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
public abstract class ConstraintImpl implements Constraint {
    private UUID id;

    @Id
    @GeneratedValue(generator = "UseExistingOrGenerateUUIDGenerator")
    @JsonIgnore
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
