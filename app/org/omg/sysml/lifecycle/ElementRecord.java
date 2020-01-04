package org.omg.sysml.lifecycle;

import org.omg.sysml.metamodel.MofObject;

public interface ElementRecord extends Record {
    MofObject getData();

    ElementIdentity getIdentity();
}
