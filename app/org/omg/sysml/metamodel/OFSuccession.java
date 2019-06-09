package org.omg.sysml.metamodel;

public interface OFSuccession extends MofObject {
    OrderedFeature getTo();

    OrderedFeature getFrom();
}