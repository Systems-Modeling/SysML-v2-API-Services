package org.omg.sysml.external;

import org.omg.sysml.lifecycle.Record;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.MofObject;

import java.net.URI;

public interface ExternalElement extends Element, MofObject {

    URI getResourceIdentifier();
}
