package org.omg.sysml.query;

import java.util.Collection;

public interface CompositeConstraint {
    Collection<Constraint> getConstraint();

    void setConstraint(Collection<Constraint> constraint);

    CompositeOperator getOperator();

    void setOperator(CompositeOperator operator);
}
