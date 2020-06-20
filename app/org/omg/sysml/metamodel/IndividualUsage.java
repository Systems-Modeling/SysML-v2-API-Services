package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface IndividualUsage extends ItemUsage, MofObject {
    IndividualDefinition getIndividualDefinition();

    Boolean getIsTimeSlice();

    Boolean getIsSnapshot();

    Feature getTimeSliceFeature();

    Feature getSnapshotFeature();
}