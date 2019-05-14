package models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jackson.ElementDeserializer;
import jackson.ElementSerializer;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

// TODO Jar after modification. See README.

@Entity
//@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
//@JsonTypeName("Relationship")
public final class Relationship extends Element {
    /**
     * The source of the relationship, the object that would "contain" the target in a POJO
     */
    @OneToOne
    @JoinColumn(name = "source_id")
    @JsonSerialize(using = ElementSerializer.class, as = Element.class)
    @JsonDeserialize(using = ElementDeserializer.class, as = Element.class)
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private Element source;

    /**
     * The target of the relationship, the element "contained" by another in a POJO
     */
    @OneToOne
    @JoinColumn(name = "target_id")
    @JsonSerialize(using = ElementSerializer.class, as = Element.class)
    @JsonDeserialize(using = ElementDeserializer.class, as = Element.class)
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private Element target;

    public Element getSource() {
        return source;
    }

    public void setSource(Element element) {
        this.source = element;
    }

    public Element getTarget() {
        return target;
    }

    public void setTarget(Element element) {
        this.target = element;
    }
}
