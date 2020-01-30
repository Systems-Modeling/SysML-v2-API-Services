package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface ConjugatedPortTyping extends FeatureTyping, MofObject {
    PortDefinition getPortDefinition();

    ConjugatedPortDefinition getConjugatedPortDefinition();
}