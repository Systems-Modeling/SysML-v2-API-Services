package org.omg.sysml.metamodel.impl;

import org.omg.sysml.metamodel.*;

import org.omg.sysml.metamodel.Package;
import org.omg.sysml.metamodel.Class;

import jackson.MofObjectSerializer;
import jackson.MofObjectDeserializer;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.hibernate.annotations.Any;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.FetchMode;

// import info.archinnov.achilles.annotations.UDT;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.EnumType;
import javax.persistence.ElementCollection;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.FetchType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Table;
import javax.persistence.SecondaryTable;
import javax.persistence.CollectionTable;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
@Entity(name = "AtomicInteractionImpl")
@SecondaryTable(name = "AtomicInteraction")
@org.hibernate.annotations.Table(appliesTo = "AtomicInteraction", fetch = FetchMode.SELECT, optional = false)
// @info.archinnov.achilles.annotations.Table(table = "AtomicInteraction")
@DiscriminatorValue(value = "AtomicInteraction")
@JsonTypeName(value = "AtomicInteraction")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
public class AtomicInteractionImpl extends MofObjectImpl implements AtomicInteraction {
    // @info.archinnov.achilles.annotations.Column("itemType")
    private Collection<Classifier> itemType;

    @JsonGetter
    @JsonSerialize(contentUsing = MofObjectSerializer.class)
    @ManyToAny(metaDef = "ClassifierMetaDef", metaColumn = @javax.persistence.Column(name = "itemTypeType"), fetch = FetchType.LAZY)
    @JoinTable(name = "AtomicInteraction_itemType",
            joinColumns = @JoinColumn(name = "AtomicInteractionId"),
            inverseJoinColumns = @JoinColumn(name = "itemTypeId"))
    public Collection<Classifier> getItemType() {
        if (itemType == null) {
            itemType = new ArrayList<>();
        }
        return itemType;
    }

    @JsonSetter
    @JsonDeserialize(contentUsing = MofObjectDeserializer.class, contentAs = ClassifierImpl.class)
    public void setItemType(Collection<Classifier> itemType) {
        this.itemType = itemType;
    }



}
