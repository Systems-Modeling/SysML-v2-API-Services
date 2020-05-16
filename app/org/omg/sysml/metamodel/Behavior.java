package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface Behavior extends Classifier {
    Collection<? extends Step> getStep();

    Collection<? extends Parameter> getParameter();
}