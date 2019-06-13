package org.omg.sysml.extension.impl;

import com.fasterxml.jackson.annotation.*;
import org.omg.sysml.extension.Project;

import org.hibernate.annotations.FetchMode;
import org.omg.sysml.metamodel.impl.MofObjectImpl;

// import info.archinnov.achilles.annotations.UDT;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.DiscriminatorValue;
import javax.persistence.SecondaryTable;

@Entity
@SecondaryTable(name = "Project")
@org.hibernate.annotations.Table(appliesTo = "Project", fetch = FetchMode.SELECT, optional = false)
// @info.archinnov.achilles.annotations.Table(table = "Project")
@DiscriminatorValue(value = "Project")
@JsonTypeName(value = "Project")
public class ProjectImpl extends MofObjectImpl implements Project {
    private String name;

    @JsonProperty(required = true)
    @JsonGetter
    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    @javax.persistence.Column(name = "name", table = "Project")
    public String getName() {
        return name;
    }

    @JsonProperty(required = true)
    @JsonSetter
    public void setName(String name) {
        this.name = name;
    }

    @Override
    @JsonIgnore
    public Project getContainingProject() {
        return null;
    }

    /*

    // @info.archinnov.achilles.annotations.Column("ownedRelationship")
    private Collection<Element> containedElement;

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @ManyToAny(metaDef = "RelationshipMetaDef", metaColumn = @javax.persistence.Column(name = "ownedRelationshipType"), fetch = FetchType.LAZY)
    @JoinTable(name = "Package_ownedRelationship",
            joinColumns = @JoinColumn(name = "PackageId"),
            inverseJoinColumns = @JoinColumn(name =  "ownedRelationshipId"))
    public Collection<Element> getContainedElement() {
        if (containedElement == null) {
            containedElement = new ArrayList<>();
        }
        return containedElement;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = RelationshipImpl.class)
    public void setContainedElement(Collection<Element> containedElement) {
        this.containedElement = containedElement;
    }

     */
}
