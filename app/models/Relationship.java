package models;

import java.util.UUID;

/**
 * @author Manas Bajaj
 *
 * Representation of Element in SysML v2 meta-model
 */

public class Relationship {
    public UUID identifier;
    public String name;
    public String description;
    public UUID parent_model;
    public String type;
    public String source_element_role;
    public UUID source_element;
    public String target_element_role;
    public UUID target_element;

    public Relationship(String name, UUID parent_model, String type, String source_element_role, UUID source_element,
                        String target_element_role, UUID target_element) {
        this.identifier = UUID.randomUUID();
        this.name = name;
        this.description = name;
        this.parent_model = parent_model;
        this.type = type;
        this.source_element_role = source_element_role;
        this.source_element = source_element;
        this.target_element_role = target_element_role;
        this.target_element = target_element;
    }

    public Relationship(UUID identifier, String name, String description, UUID parent_model, String type,
                        String source_element_role, UUID source_element, String target_element_role, UUID target_element) {
        this.identifier = identifier;
        this.name = name;
        this.description = description;
        this.parent_model = parent_model;
        this.type = type;
        this.source_element_role = source_element_role;
        this.source_element = source_element;
        this.target_element_role = target_element_role;
        this.target_element = target_element;
    }

    @Override
    public boolean equals(Object other) {
        if(other instanceof Relationship) {
            Relationship otherElement = (Relationship)other;
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
