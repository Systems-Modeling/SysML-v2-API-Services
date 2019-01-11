package models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jackson.ElementDeserializer;
import jackson.ElementSerializer;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

// TODO Jar after modification. See README.

@Entity
@Table(name = "models")
//@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
//@JsonTypeName("Model")
public class Model extends Element {
    @OneToMany(mappedBy = "models")
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
        return super.getModel();
    }

    @Override
    public void setModel(Model model) {
        throw new UnsupportedOperationException("Cannot set a Model's model");
    }
}
