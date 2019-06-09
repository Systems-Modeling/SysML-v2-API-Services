package org.omg.sysml.metamodel.impl;

import org.omg.sysml.metamodel.AtomicInteraction;
import org.omg.sysml.metamodel.Class;
import org.omg.sysml.metamodel.*;

import jackson.MofObjectSerializer;
import jackson.MofObjectDeserializer;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.FetchMode;

// import info.archinnov.achilles.annotations.UDT;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.FetchType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.SecondaryTable;

import java.util.Collection;
import java.util.ArrayList;

@Entity(name = "AtomicInteractionImpl")
@SecondaryTable(name = "AtomicInteraction")
@org.hibernate.annotations.Table(appliesTo = "AtomicInteraction", fetch = FetchMode.SELECT, optional = false)
// @info.archinnov.achilles.annotations.Table(table = "AtomicInteraction")
@DiscriminatorValue(value = "AtomicInteraction")
@JsonTypeName(value = "AtomicInteraction")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
public class AtomicInteractionImpl extends MofObjectImpl implements AtomicInteraction {
    // @info.archinnov.achilles.annotations.Column("itemType")
    private Collection<Class> itemType;

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @ManyToAny(metaDef = "ClassMetaDef", metaColumn = @javax.persistence.Column(name = "itemTypeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "AtomicInteraction_itemType",
            joinColumns = @JoinColumn(name = "AtomicInteractionId"),
            inverseJoinColumns = @JoinColumn(name = "itemTypeId"))
    public Collection<Class> getItemType() {
        if (itemType == null) {
            itemType = new ArrayList<>();
        }
        return itemType;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ClassImpl.class)
    public void setItemType(Collection<Class> itemType) {
        this.itemType = itemType;
    }



}
