package org.omg.sysml.metamodel;

import java.util.Collection;

public interface Function extends Behavior, MofObject {
    Collection<? extends Expression> getExpression();

    Parameter getResult();
}