package org.omg.sysml.internal.impl;

import org.omg.sysml.internal.CommitIndex;
import org.omg.sysml.lifecycle.Commit;
import org.omg.sysml.lifecycle.ElementVersion;
import org.omg.sysml.lifecycle.impl.CommitImpl;
import org.omg.sysml.lifecycle.impl.ElementVersionImpl;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity(name = "CommitIndex")
public class CommitIndexImpl implements CommitIndex {
    private UUID id;
    private Commit commit;
    private Set<ElementVersion> workingElementVersions;

    @Id
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    @JoinColumn(name = "id")
    @OneToOne(targetEntity = CommitImpl.class)
    @MapsId
    public Commit getCommit() {
        return commit;
    }

    @Override
    public void setCommit(Commit commit) {
        this.commit = commit;
    }

    @Override
    @ManyToMany(targetEntity = ElementVersionImpl.class, fetch = FetchType.LAZY)
    public Set<ElementVersion> getWorkingElementVersions() {
        return workingElementVersions;
    }

    @Override
    public void setWorkingElementVersions(Set<ElementVersion> workingElementVersions) {
        this.workingElementVersions = workingElementVersions;
    }
}
