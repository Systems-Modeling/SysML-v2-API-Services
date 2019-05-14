package models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jackson.ElementDeserializer;
import jackson.ElementSerializer;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
//@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
//@JsonTypeName("Element")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Element {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "myUuidGenerator")
    @GenericGenerator(name = "myUuidGenerator", strategy = "jpa.MyUUIDGenerator")
    @JsonProperty(value = "id", required = true)
    protected UUID id;

    @Column
    @JsonProperty
    private String name;

    @Column
    @JsonProperty
    private String type;

    @ManyToOne
    @JoinColumn(name = "model_id")
    @JsonSerialize(using = ElementSerializer.class, as = Model.class)
    @JsonDeserialize(using = ElementDeserializer.class, as = Model.class)
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private Model model;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}