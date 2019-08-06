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
@Entity(name = "OFSuccessionImpl")
@SecondaryTable(name = "OFSuccession")
@org.hibernate.annotations.Table(appliesTo = "OFSuccession", fetch = FetchMode.SELECT, optional = false)
// @info.archinnov.achilles.annotations.Table(table = "OFSuccession")
@DiscriminatorValue(value = "OFSuccession")
@JsonTypeName(value = "OFSuccession")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
public class OFSuccessionImpl extends MofObjectImpl implements OFSuccession {
    // @info.archinnov.achilles.annotations.Column("to")
    private OrderedFeature to;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "OrderedFeatureMetaDef", metaColumn = @javax.persistence.Column(name = "toType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "toId", table = "OFSuccession")
    public OrderedFeature getTo() {
        return to;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = OrderedFeatureImpl.class)
    public void setTo(OrderedFeature to) {
        this.to = to;
    }



    // @info.archinnov.achilles.annotations.Column("from")
    private OrderedFeature from;

    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "OrderedFeatureMetaDef", metaColumn = @javax.persistence.Column(name = "fromType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "fromId", table = "OFSuccession")
    public OrderedFeature getFrom() {
        return from;
    }

    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = OrderedFeatureImpl.class)
    public void setFrom(OrderedFeature from) {
        this.from = from;
    }



}
