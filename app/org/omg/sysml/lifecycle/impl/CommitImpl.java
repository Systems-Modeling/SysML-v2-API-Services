package org.omg.sysml.lifecycle.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jackson.RecordSerialization;
import org.omg.sysml.lifecycle.Commit;
import org.omg.sysml.lifecycle.ElementVersion;
import org.omg.sysml.lifecycle.Project;
import org.omg.sysml.record.impl.RecordImpl;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Commit")
public class CommitImpl extends RecordImpl implements Commit {
    private Project containingProject;
    private Set<ElementVersion> change;
    private ZonedDateTime timestamp;
    private Commit previousCommit;

    @Override
    @ManyToOne(targetEntity = ProjectImpl.class, fetch = FetchType.LAZY)
    @JsonSerialize(as = ProjectImpl.class, using = RecordSerialization.RecordSerializer.class)
    @JsonView(Views.Compact.class)
    public Project getContainingProject() {
        return containingProject;
    }

    @JsonDeserialize(as = ProjectImpl.class, using = RecordSerialization.ProjectDeserializer.class)
    public void setContainingProject(Project containingProject) {
        this.containingProject = containingProject;
    }

    @OneToMany(targetEntity = ElementVersionImpl.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonView(Views.Complete.class)
    public Set<ElementVersion> getChange() {
        if (change == null) {
            change = new HashSet<>();
        }
        return change;
    }

    @JsonDeserialize(contentAs = ElementVersionImpl.class)
    public void setChange(Set<ElementVersion> change) {
        this.change = change;
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
        public interface Compact {
        }

        public interface Complete extends Compact {
        }
    }
}
