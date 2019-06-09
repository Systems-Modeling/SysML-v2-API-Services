package org.omg.sysml.metamodel;

import java.util.List;

public interface ItemFlow extends Connector, Step, MofObject {
    List<? extends Class> getItemType();

    List<? extends Feature> getTargetInputFeature();

    List<? extends Feature> getSourceOutputFeature();
}