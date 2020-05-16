package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface FunctionUsage extends Expression, ActionUsage {
    Function getFunctionDefinition();

    Usage getFunctionOwningUsage();

    Definition getFunctionOwningDefinition();
}