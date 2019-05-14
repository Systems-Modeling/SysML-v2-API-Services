package models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jackson.ElementDeserializer;
import jackson.ElementSerializer;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

// TODO Jar after modification. See README.

@Entity
//@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
//@JsonTypeName("Model")
public class Model extends Element {
    @OneToMany(mappedBy = "model")
    @JsonSerialize(contentUsing = ElementSerializer.class, contentAs = Element.class)
    @JsonDeserialize(contentUsing = ElementDeserializer.class, contentAs = Element.class)
    private List<Element> elements;

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Override
    public Model getModel() {
        return this;
    }

    @Override
    public void setModel(Model model) {
        throw new UnsupportedOperationException("Cannot set a Model's model");
    }
}
