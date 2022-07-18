package org.omg.sysml.internal.impl;

import org.omg.sysml.data.ProjectUsage;
import org.omg.sysml.data.impl.ProjectUsageImpl;
import org.omg.sysml.internal.WorkingDataVersion;
import org.omg.sysml.lifecycle.DataVersion;
import org.omg.sysml.lifecycle.impl.CommitImpl;
import org.omg.sysml.lifecycle.impl.DataVersionImpl;
import org.omg.sysml.record.impl.RecordImpl;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity(name = "WorkingDataVersion")
public class WorkingDataVersionImpl extends RecordImpl implements WorkingDataVersion {

    private ProjectUsage source;
    private DataVersion dataVersion;

    @Override
    @ManyToOne(targetEntity = ProjectUsageImpl.class, fetch = FetchType.LAZY)
    public ProjectUsage getSource() {
        return source;
    }

    @Override
    public void setSource(ProjectUsage source) {
        this.source = source;
    }

    @Override
    @ManyToOne(targetEntity = DataVersionImpl.class, fetch = FetchType.LAZY)
    public DataVersion getDataVersion() {
        return dataVersion;
    }

    @Override
    public void setDataVersion(DataVersion dataVersion) {
        this.dataVersion = dataVersion;
    }
}
