package org.omg.sysml.lifecycle;

import org.omg.sysml.metamodel.MofObject;

public interface ElementVersion extends Record {
    MofObject getData();

    ElementIdentity getIdentity();
}
