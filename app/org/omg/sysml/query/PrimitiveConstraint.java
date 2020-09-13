package org.omg.sysml.query;

import java.util.Collection;

public interface PrimitiveConstraint extends Constraint {
    Boolean getInverse();

    void setInverse(Boolean inverse);

    String getProperty();

    void setProperty(String property);

    Collection<String> getValue();

    void setValue(Collection<String> value);

    PrimitiveOperator getOperator();

    void setOperator(PrimitiveOperator operator);
}
