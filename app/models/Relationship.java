package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jackson.ElementDeserializer;
import jackson.ElementSerializer;

import javax.persistence.*;

// TODO Jar after modification. See README.

@Entity
@Table(name = "relationships")
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "source_target_query_id")
    @JsonIgnore
    private SourceTargetQuery sourceTargetQuery;

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

    public SourceTargetQuery getSourceTargetQuery() {
        if (sourceTargetQuery == null) {
            sourceTargetQuery = new SourceTargetQuery();
        }
        sourceTargetQuery.setKey(getSource(), getTarget());
        sourceTargetQuery.setRelationship(this);
        return sourceTargetQuery;
    }
}
