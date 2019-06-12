package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface SequenceConstructionExpression extends Expression, MofObject {
    List<? extends Expression> getElement();
}