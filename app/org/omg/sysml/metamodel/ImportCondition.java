package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface ImportCondition extends Relationship {
    ConditionalImport getConditionalImport();

    Predicate getPredicate();
}