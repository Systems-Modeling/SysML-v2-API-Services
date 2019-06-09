package org.omg.sysml.metamodel;

import java.util.List;

public interface OperatorExpression extends InvocationExpression, MofObject {
    String getOperator();

    List<? extends Expression> getOperand();
}