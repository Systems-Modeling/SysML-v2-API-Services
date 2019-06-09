package org.omg.sysml.metamodel;

import java.util.Collection;

public interface Expression extends Step, MofObject {
    Collection<? extends Function> getType();
}