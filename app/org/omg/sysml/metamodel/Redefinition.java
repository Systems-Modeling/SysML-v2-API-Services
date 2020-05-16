package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface Redefinition extends Subsetting {
    Feature getRedefiningFeature();

    Feature getRedefinedFeature();
}