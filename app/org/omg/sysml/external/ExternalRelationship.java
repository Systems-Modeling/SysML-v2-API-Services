package org.omg.sysml.external;

import org.omg.sysml.lifecycle.Record;
import org.omg.sysml.metamodel.MofObject;
import org.omg.sysml.metamodel.Relationship;

import java.net.URI;

public interface ExternalRelationship extends Relationship, MofObject {

    URI getResourceIdentifier();

    String getSpecification();
}
