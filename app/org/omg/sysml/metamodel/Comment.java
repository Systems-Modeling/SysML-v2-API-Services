package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface Comment extends Element, MofObject {
    String getBody();

    Element getCommentedElement();

    Annotation getAnnotationForComment();
}