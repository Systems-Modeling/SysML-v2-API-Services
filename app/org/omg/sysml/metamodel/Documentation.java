package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface Documentation extends Annotation, MofObject {
    Element getOwningDocumentedElement();

    Comment getDocumentingComment();
}