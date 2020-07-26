package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface AnnotatingElement extends Element, MofObject {
    Collection<? extends Annotation> getAnnotation();

    Collection<? extends Element> getAnnotatedElement();
}