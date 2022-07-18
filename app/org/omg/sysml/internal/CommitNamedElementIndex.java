package org.omg.sysml.internal;

import org.omg.sysml.lifecycle.Commit;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.record.Record;

import java.util.Map;
import java.util.UUID;

public interface CommitNamedElementIndex extends Record {

    Commit getCommit();

    void setCommit(Commit commit);

    Map<String, Element> getWorkingNamedElement();

    void setWorkingNamedElement(Map<String, Element> workingNamedElement);
}
