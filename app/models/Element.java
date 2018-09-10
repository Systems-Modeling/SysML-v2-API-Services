package models;

import java.util.UUID;

/**
 * @author Manas Bajaj
 *
 * Representation of Element in SysML v2 meta-model
 */

public class Element {
    private UUID identifier;
    private String name;

    public Element(String name) {
        this.identifier = UUID.randomUUID();
        this.name = name;
    }

    public Element(UUID identifier, String name) {
        this.identifier = identifier;
        this.name = name;
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        if(other instanceof Element) {
            Element otherElement = (Element)other;
            if(otherElement.getIdentifier().equals(identifier))
                return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return (int) identifier.hashCode() * name.hashCode();
    }

    @Override
    public String toString() {
        return "Element (name = " + name + ", identifier = " + identifier +")";
    }
}
