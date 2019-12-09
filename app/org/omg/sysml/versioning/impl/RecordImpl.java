package org.omg.sysml.versioning.impl;

import org.omg.sysml.versioning.Record;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
public abstract class RecordImpl implements Record {
    private UUID id;

    @Override
    @Id
    @GeneratedValue(generator = "UseExistingOrGenerateUUIDGenerator")
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
