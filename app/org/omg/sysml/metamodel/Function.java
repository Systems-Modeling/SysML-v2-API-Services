package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface Function extends Behavior {
    Collection<? extends Expression> getExpression();

    Parameter getResult();
}