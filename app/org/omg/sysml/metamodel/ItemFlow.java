package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface ItemFlow extends Connector, Step, MofObject {
    List<? extends Class> getItemType();

    List<? extends Feature> getTargetInputFeature();

    List<? extends Feature> getSourceOutputFeature();
}