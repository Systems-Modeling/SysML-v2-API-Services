package org.omg.sysml.query.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ManyToAny;
import org.omg.sysml.query.CompositeConstraint;
import org.omg.sysml.query.CompositeOperator;
import org.omg.sysml.query.Constraint;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

// @Embeddable // Can't embed and have polymorphism between Constraints, which is necessary for CompositeConstraint.constraint
@Entity(name = "CompositeConstraint")
@DiscriminatorValue(value = "CompositeConstraint")
@JsonTypeName(value = "CompositeConstraint")
public class CompositeConstraintImpl extends ConstraintImpl implements CompositeConstraint {
    private Collection<Constraint> constraint;
    private CompositeOperator operator;

    @Override
    @ManyToAny(metaDef = "ConstraintMetaDef", metaColumn = @javax.persistence.Column(name = "attributeType"), fetch = FetchType.EAGER)
    @JoinTable(name = "CompositeConstraint_constraint",
            joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "attributeId"))
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public Collection<Constraint> getConstraint() {
        if (constraint == null) {
            return new ArrayList<>();
        }
        return constraint;
    }

    @Override
    @JsonDeserialize(contentAs = ConstraintImpl.class)
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
    public void setConstraint(Collection<Constraint> constraint) {
        this.constraint = constraint;
    }

    @Override
    @Enumerated(EnumType.STRING)
    public CompositeOperator getOperator() {
        return operator;
    }

    @Override
    public void setOperator(CompositeOperator operator) {
        this.operator = operator;
    }
}
