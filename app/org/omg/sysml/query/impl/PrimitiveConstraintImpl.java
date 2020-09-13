package org.omg.sysml.query.impl;

import com.fasterxml.jackson.annotation.JsonTypeName;
import org.omg.sysml.query.PrimitiveConstraint;
import org.omg.sysml.query.PrimitiveOperator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

// @Embeddable // Can't embed and have polymorphism between Constraints, which is necessary for CompositeConstraint.constraint
@Entity(name = "PrimitiveConstraint")
@DiscriminatorValue(value = "PrimitiveConstraint")
@JsonTypeName(value = "PrimitiveConstraint")
public class PrimitiveConstraintImpl extends ConstraintImpl implements PrimitiveConstraint {
    private Boolean inverse;
    private String property;
    private Collection<String> value;
    private PrimitiveOperator operator;

    @Override
    @Column
    public Boolean getInverse() {
        if (inverse == null) {
            inverse = false;
        }
        return inverse;
    }

    @Override
    public void setInverse(Boolean inverse) {
        this.inverse = inverse;
    }

    @Override
    @Column
    public String getProperty() {
        return property;
    }

    @Override
    public void setProperty(String property) {
        this.property = property;
    }

    @Override
    @ElementCollection
    public Collection<String> getValue() {
        if (value == null) {
            value = new ArrayList<>();
        }
        return value;
    }

    @Override
    public void setValue(Collection<String> value) {
        this.value = value;
    }

    @Override
    @Enumerated(EnumType.STRING)
    public PrimitiveOperator getOperator() {
        return operator;
    }

    @Override
    public void setOperator(PrimitiveOperator operator) {
        this.operator = operator;
    }
}
