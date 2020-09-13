package org.omg.sysml.query.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jackson.RecordSerialization;
import org.hibernate.annotations.Any;
import org.hibernate.annotations.Cascade;
import org.omg.sysml.lifecycle.ElementIdentity;
import org.omg.sysml.lifecycle.Project;
import org.omg.sysml.lifecycle.impl.ElementIdentityImpl;
import org.omg.sysml.lifecycle.impl.ProjectImpl;
import org.omg.sysml.query.Constraint;
import org.omg.sysml.query.Query;
import org.omg.sysml.record.impl.RecordImpl;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "Query")
public class QueryImpl extends RecordImpl implements Query {
    private Project containingProject;
    private Set<String> select;
    private Set<ElementIdentity> scope;
    private Boolean recursiveInScope;
    private List<String> orderBy;
    private Constraint where;

    @Override
    @ManyToOne(targetEntity = ProjectImpl.class, fetch = FetchType.LAZY)
    @JsonSerialize(as = ProjectImpl.class, using = RecordSerialization.RecordSerializer.class)
    public Project getContainingProject() {
        return containingProject;
    }

    @Override
    @JsonDeserialize(as = ProjectImpl.class, using = RecordSerialization.ProjectDeserializer.class)
    public void setContainingProject(Project containingProject) {
        this.containingProject = containingProject;
    }

    @Override
    @ElementCollection
    @Column(name = "select_") // select is a reserved keyword in SQL
    public Set<String> getSelect() {
        if (select == null) {
            select = new HashSet<>();
        }
        return select;
    }

    @Override
    public void setSelect(Set<String> select) {
        this.select = select;
    }

    @Override
    @ManyToMany(targetEntity = ElementIdentityImpl.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<ElementIdentity> getScope() {
        if (scope == null) {
            scope = new HashSet<>();
        }
        return scope;
    }

    @Override
    @JsonDeserialize(contentAs = ElementIdentityImpl.class)
    public void setScope(Set<ElementIdentity> scope) {
        this.scope = scope;
    }

    @Override
    @Column
    public Boolean getRecursiveInScope() {
        if (recursiveInScope == null) {
            recursiveInScope = false;
        }
        return recursiveInScope;
    }

    @Override
    public void setRecursiveInScope(Boolean recursiveInScope) {
        this.recursiveInScope = recursiveInScope;
    }

    @Override
    @ElementCollection
    public List<String> getOrderBy() {
        if (orderBy == null) {
            orderBy = new ArrayList<>();
        }
        return orderBy;
    }

    @Override
    public void setOrderBy(List<String> orderBy) {
        this.orderBy = orderBy;
    }

    @Override
    @Any(metaDef = "ConstraintMetaDef", metaColumn = @javax.persistence.Column(name = "whereType"), fetch = FetchType.EAGER)
    @JoinColumn(name = "whereId")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public Constraint getWhere() {
        return where;
    }

    @Override
    @JsonDeserialize(as = ConstraintImpl.class)
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
    public void setWhere(Constraint where) {
        this.where = where;
    }

    @Transient
    @JsonProperty("@type")
    public String getType() {
        return Query.class.getSimpleName();
    }
}
