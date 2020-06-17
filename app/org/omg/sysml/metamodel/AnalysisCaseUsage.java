package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface AnalysisCaseUsage extends CaseUsage, MofObject {
    Collection<? extends ActionUsage> getAnalysisAction();

    AnalysisCaseDefinition getAnalysisCaseDefinition();

    Expression getResultExpression();
}