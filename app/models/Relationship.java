package models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jackson.ElementDeserializer;
import jackson.ElementSerializer;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "relationships")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonTypeName("Relationship")
public final class Relationship extends models.Element {
    /**
     * The source of the relationship, the object that would "contain" the target in a POJO
     */
    @OneToOne
    @JoinColumn(name = "source_id")
    @JsonSerialize(using = ElementSerializer.class, as = models.Element.class)
    @JsonDeserialize(using = ElementDeserializer.class, as = models.Element.class)
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private models.Element source;

    /**
     * The target of the relationship, the element "contained" by another in a POJO
     */
    @OneToOne
    @JoinColumn(name = "target_id")
    @JsonSerialize(using = ElementSerializer.class, as = models.Element.class)
    @JsonDeserialize(using = ElementDeserializer.class, as = models.Element.class)
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private models.Element target;

    public models.Element getSource() {
        return source;
    }

    public void setSource(models.Element element) {
        this.source = element;
    }

    public models.Element getTarget() {
        return target;
    }

    public void setTarget(Element element) {
        this.target = element;
    }
}
