package org.omg.sysml.internal;

import org.omg.sysml.data.ProjectUsage;
import org.omg.sysml.lifecycle.DataVersion;
import org.omg.sysml.record.Record;

public interface WorkingDataVersion extends Record {

    ProjectUsage getSource();

    void setSource(ProjectUsage commit);

    DataVersion getDataVersion();

    void setDataVersion(DataVersion dataVersion);
}
