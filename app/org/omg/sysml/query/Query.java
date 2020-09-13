package org.omg.sysml.query;

import org.omg.sysml.lifecycle.ElementIdentity;
import org.omg.sysml.lifecycle.Project;
import org.omg.sysml.record.Record;

import java.util.List;
import java.util.Set;

public interface Query extends Record {
    Project getContainingProject();

    void setContainingProject(Project project);

    Set<String> getSelect();

    void setSelect(Set<String> select);

    Set<ElementIdentity> getScope();

    void setScope(Set<ElementIdentity> scope);

    Boolean getRecursiveInScope();

    void setRecursiveInScope(Boolean recursiveInScope);

    List<String> getOrderBy();

    void setOrderBy(List<String> orderBy);

    Constraint getWhere();

    void setWhere(Constraint where);
}
