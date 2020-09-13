package org.omg.sysml.lifecycle;

import org.omg.sysml.record.Record;

import java.time.ZonedDateTime;
import java.util.Set;

public interface Commit extends Record {
    Project getContainingProject();

    void setContainingProject(Project containingProject);

    Set<ElementVersion> getChange();

    void setChange(Set<ElementVersion> changes);

    Commit getPreviousCommit();

    void setPreviousCommit(Commit previousCommit);

    ZonedDateTime getTimestamp();

    void setTimestamp(ZonedDateTime timestamp);
}
