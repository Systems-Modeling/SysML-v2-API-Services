package org.omg.sysml.query;

public interface PrimitiveConstraint extends Constraint {
    Boolean getInverse();

    void setInverse(Boolean inverse);

    String getProperty();

    void setProperty(String property);

    String getValue();

    void setValue(String value);

    PrimitiveOperator getOperator();

    void setOperator(PrimitiveOperator operator);
}
