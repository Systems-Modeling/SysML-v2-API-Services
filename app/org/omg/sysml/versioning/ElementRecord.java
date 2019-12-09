package org.omg.sysml.versioning;

import org.omg.sysml.metamodel.MofObject;

public interface ElementRecord extends Record {
    MofObject getData();

    ElementIdentity getIdentity();
}
