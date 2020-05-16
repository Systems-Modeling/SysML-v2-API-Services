package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface MultiplicityRange extends Multiplicity {
    Expression getLowerBound();

    Expression getUpperBound();

    List<? extends Expression> getBound();
}