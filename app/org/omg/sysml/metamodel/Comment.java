package org.omg.sysml.metamodel;

public interface Comment extends Element, MofObject {
    String getBody();

    Element getCommentedElement();

    Annotation getAnnotationForComment();
}