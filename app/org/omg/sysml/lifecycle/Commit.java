package org.omg.sysml.lifecycle;

import java.time.ZonedDateTime;
import java.util.Set;

public interface Commit extends Record {
    Project getContainingProject();

    void setContainingProject(Project containingProject);

    Set<ElementVersion> getChanges();

    void setChanges(Set<ElementVersion> changes);

    Commit getPreviousCommit();

    void setPreviousCommit(Commit previousCommit);

    ZonedDateTime getTimestamp();

    void setTimestamp(ZonedDateTime timestamp);
}
