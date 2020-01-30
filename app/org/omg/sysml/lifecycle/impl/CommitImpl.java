package org.omg.sysml.lifecycle.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jackson.MofObjectDeserializer;
import jackson.MofObjectSerializer;
import jackson.RecordSerialization;
import org.omg.sysml.lifecycle.Project;
import org.omg.sysml.lifecycle.Commit;
import org.omg.sysml.lifecycle.ElementRecord;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Commit")
public class CommitImpl extends RecordImpl implements Commit {
    private Project containingProject;
    private Set<ElementRecord> changes;
    private ZonedDateTime timestamp;
    private Commit previousCommit;

    @Override
    @ManyToOne(targetEntity = ProjectImpl.class, fetch = FetchType.LAZY)
    @JsonSerialize(as = ProjectImpl.class, using = MofObjectSerializer.class)
    @JsonView(Views.Compact.class)
    public Project getContainingProject() {
        return containingProject;
    }

    @JsonDeserialize(as = ProjectImpl.class, using = MofObjectDeserializer.class)
    public void setContainingProject(Project containingProject) {
        this.containingProject = containingProject;
    }

    @Override
    @OneToMany(targetEntity = ElementRecordImpl.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonView(Views.Complete.class)
    public Set<ElementRecord> getChanges() {
        if (changes == null) {
            changes = new HashSet<>();
        }
        return changes;
    }

    @JsonDeserialize(contentAs = ElementRecordImpl.class)
    public void setChanges(Set<ElementRecord> changes) {
        this.changes = changes;
    }

    @Override
    @Column
    @JsonView(Views.Compact.class)
    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @ManyToOne(targetEntity = CommitImpl.class, fetch = FetchType.LAZY)
    @JsonSerialize(as = CommitImpl.class, using = RecordSerialization.RecordSerializer.class)
    @JsonView(Views.Compact.class)
    public Commit getPreviousCommit() {
        return previousCommit;
    }

    @JsonDeserialize(as = CommitImpl.class, using = RecordSerialization.CommitDeserializer.class)
    public void setPreviousCommit(Commit previousCommit) {
        this.previousCommit = previousCommit;
    }

    @Transient
    @JsonProperty("@type")
    @JsonView(Views.Compact.class)
    public String getType() {
        return Commit.class.getSimpleName();
    }

    public static class Views {
        public interface Compact {}
        public interface Complete extends Compact {}
    }
}
