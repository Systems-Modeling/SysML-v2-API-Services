package models;

import java.util.UUID;

/**
 * @author Manas Bajaj
 *
 * Representation of Model in SysML v2 meta-model
 */

public class Model {
    public UUID identifier;
    public String name;
    public String description;

    public Model() {
        super();
    }
    public Model(String name, String description) {
        this.identifier = UUID.randomUUID();
        this.name = name;
        this.description = description;
    }

    public Model(UUID identifier, String name, String description) {
        this.identifier = identifier;
        this.name = name;
        this.description = description;
    }

    @Override
    public boolean equals(Object other) {
        if(other instanceof Model) {
            Model otherModel = (Model)other;
            if(otherModel.identifier.equals(identifier))
                return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (int) identifier.hashCode();
    }

}
