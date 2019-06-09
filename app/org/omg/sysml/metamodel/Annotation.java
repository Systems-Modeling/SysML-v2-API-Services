package org.omg.sysml.metamodel;

public interface Annotation extends Relationship, MofObject {
    Comment getAnnotatingComment();

    Element getAnnotatedElement();
}