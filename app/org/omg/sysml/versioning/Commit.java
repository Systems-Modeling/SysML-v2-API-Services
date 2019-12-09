package org.omg.sysml.versioning;

import org.omg.sysml.extension.Project;

import java.time.ZonedDateTime;
import java.util.Set;

public interface Commit extends Record {
    Project getContainingProject();

    Set<ElementRecord> getChanges();

    ZonedDateTime getTimestamp();

    void setTimestamp(ZonedDateTime timestamp);
}
