package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface ItemFlow extends Connector, Step, MofObject {
    List<? extends Classifier> getItemType();

    List<? extends Feature> getTargetInputFeature();

    List<? extends Feature> getSourceOutputFeature();

    Collection<? extends ItemFlowEnd> getItemFlowEnd();

    Collection<? extends ItemFlowFeature> getItemFlowFeature();

    Collection<? extends ItemFeature> getItemFeature();
}