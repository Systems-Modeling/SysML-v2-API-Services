package models;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jackson.ElementDeserializer;
import jackson.ElementSerializer;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "elements")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonTypeName("Element")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Element {
    @Id
    @Column(name = "id")
    @GeneratedValue
    @JsonProperty(value = "@id", required = true)
    protected UUID id;

    @Column
    @JsonProperty(required = true)
    private String type;

    @ManyToOne
    @JoinColumn(name = "model_id")
    @JsonSerialize(using = ElementSerializer.class, as = models.Model.class)
    @JsonDeserialize(using = ElementDeserializer.class, as = models.Model.class)
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private models.Model model;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @JsonIgnore
    public String getType() {
        return type;
    }

    @JsonIgnore
    public void setType(String type) {
        this.type = type;
    }

    public models.Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
