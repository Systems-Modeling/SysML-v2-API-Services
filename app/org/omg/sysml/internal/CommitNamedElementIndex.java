package org.omg.sysml.internal;

import org.omg.sysml.lifecycle.Commit;
import org.omg.sysml.metamodel.Element;

import java.util.Map;
import java.util.UUID;

public interface CommitNamedElementIndex {

    UUID getId();

    void setId(UUID id);

    Commit getCommit();

    void setCommit(Commit commit);

    Map<String, Element> getWorkingNamedElement();

    void setWorkingNamedElement(Map<String, Element> workingNamedElement);
}
