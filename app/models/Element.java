package models;

import java.util.UUID;

/**
 * @author Manas Bajaj
 *
 * Representation of Element in SysML v2 meta-model
 */

public class Element {
    public UUID identifier;
    public String name;
    public String description;
    public UUID parent_model;
    public String type;

    public Element() {
        super();
    }

    public Element(String name, UUID parent_model, String type) {
        this.identifier = UUID.randomUUID();
        this.name = name;
        this.description = name;
        this.parent_model = parent_model;
        this.type = type;
    }

    public Element(UUID identifier, String name, String description, UUID parent_model, String type) {
        this.identifier = identifier;
        this.name = name;
        this.description = description;
        this.parent_model = parent_model;
        this.type = type;
    }

    @Override
    public boolean equals(Object other) {
        if(other instanceof Element) {
            Element otherElement = (Element)other;
            if(otherElement.identifier.equals(identifier))
                return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return (int) identifier.hashCode();
    }

}
