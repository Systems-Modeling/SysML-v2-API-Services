package org.omg.sysml.internal;

import org.omg.sysml.lifecycle.Commit;
import org.omg.sysml.lifecycle.ElementVersion;
import org.omg.sysml.lifecycle.Record;

import java.util.Set;

public interface CommitIndex extends Record {

    Commit getCommit();

    void setCommit(Commit commit);

    Set<ElementVersion> getWorkingElementVersions();

    void setWorkingElementVersions(Set<ElementVersion> elements);
}
