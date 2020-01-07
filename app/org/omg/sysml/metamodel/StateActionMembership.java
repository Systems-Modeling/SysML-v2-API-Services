package org.omg.sysml.metamodel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface StateActionMembership extends FeatureMembership, MofObject {
    StateActionKind getKind();
}