package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface TextualRepresentation extends AnnotatingElement, MofObject {
    String getLanguage();

    String getBody();

    Element getRepresentedElement();
}