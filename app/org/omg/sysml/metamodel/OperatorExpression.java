package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface OperatorExpression extends InvocationExpression, MofObject {
    String getOperator();

    List<? extends Expression> getOperand();
}