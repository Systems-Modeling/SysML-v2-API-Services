package org.omg.sysml.versioning.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.omg.sysml.extension.Project;
import org.omg.sysml.extension.impl.ProjectImpl;
import org.omg.sysml.versioning.Commit;
import org.omg.sysml.versioning.ElementRecord;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Commit")
public class CommitImpl extends RecordImpl implements Commit {
    @JsonProperty("@type")
    private final String type = Commit.class.getSimpleName();

    private Project containingProject;
    private Set<ElementRecord> changes;
    private ZonedDateTime timestamp;

    @Override
    @ManyToOne(targetEntity = ProjectImpl.class, fetch = FetchType.LAZY)
    public Project getContainingProject() {
        return containingProject;
    }

    public void setContainingProject(Project containingProject) {
        this.containingProject = containingProject;
    }

    @Override
    @OneToMany(targetEntity = ElementRecordImpl.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonDeserialize(contentAs = ElementRecordImpl.class)
    public Set<ElementRecord> getChanges() {
        if (changes == null) {
            changes = new HashSet<>();
        }
        return changes;
    }

    public void setChanges(Set<ElementRecord> changes) {
        this.changes = changes;
    }

    @Override
    @Column
    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
