package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface AnalysisCaseDefinition extends CaseDefinition, MofObject {
    Collection<? extends ActionUsage> getAnalysisAction();

    Expression getResultExpression();
}