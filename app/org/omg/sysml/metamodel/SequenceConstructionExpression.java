package org.omg.sysml.metamodel;

import java.util.List;

public interface SequenceConstructionExpression extends Expression, MofObject {
    List<? extends Expression> getElement();
}