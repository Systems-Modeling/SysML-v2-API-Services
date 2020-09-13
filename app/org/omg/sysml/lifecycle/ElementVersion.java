package org.omg.sysml.lifecycle;

import org.omg.sysml.metamodel.MofObject;
import org.omg.sysml.record.Record;

public interface ElementVersion extends Record {
    MofObject getData();

    ElementIdentity getIdentity();
}
