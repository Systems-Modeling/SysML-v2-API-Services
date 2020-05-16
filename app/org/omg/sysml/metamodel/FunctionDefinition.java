package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface FunctionDefinition extends Function, Activity {
    Collection<? extends FunctionUsage> getFunctionInvocation();
}