package org.omg.sysml.metamodel.impl;

import org.omg.sysml.metamodel.OFSuccession;
import org.omg.sysml.metamodel.OrderedFeature;
import org.omg.sysml.metamodel.*;

import jackson.MofObjectSerializer;
import jackson.MofObjectDeserializer;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.hibernate.annotations.Any;
import org.hibernate.annotations.FetchMode;

// import info.archinnov.achilles.annotations.UDT;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.SecondaryTable;

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

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "OrderedFeatureMetaDef", metaColumn = @javax.persistence.Column(name = "toType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "toId", table = "OFSuccession")
    public OrderedFeature getTo() {
        return to;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = OrderedFeatureImpl.class)
    public void setTo(OrderedFeature to) {
        this.to = to;
    }



    // @info.archinnov.achilles.annotations.Column("from")
    private OrderedFeature from;

    @JsonProperty(required = true)
    @JsonGetter
    @JsonSerialize(using = MofObjectSerializer.class)
    @Any(metaDef = "OrderedFeatureMetaDef", metaColumn = @javax.persistence.Column(name = "fromType"), fetch = FetchType.LAZY)
    @JoinColumn(name = "fromId", table = "OFSuccession")
    public OrderedFeature getFrom() {
        return from;
    }

    @JsonProperty(required = true)
    @JsonSetter
    @JsonDeserialize(using = MofObjectDeserializer.class, as = OrderedFeatureImpl.class)
    public void setFrom(OrderedFeature from) {
        this.from = from;
    }



}
