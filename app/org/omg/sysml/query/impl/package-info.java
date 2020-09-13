@AnyMetaDefs(value = {
        @AnyMetaDef(name = "ConstraintMetaDef", metaType = "string", idType = "java.util.UUID",
                metaValues = {
                        @MetaValue(value = "CompositeConstraint", targetEntity = CompositeConstraintImpl.class),
                        @MetaValue(value = "PrimitiveConstraint", targetEntity = PrimitiveConstraintImpl.class),
                }),
        @AnyMetaDef(name = "QueryMetaDef", metaType = "string", idType = "java.util.UUID",
                metaValues = {
                        @MetaValue(value = "Query", targetEntity = QueryImpl.class),
                }),
})

@GenericGenerators(value = {
        @GenericGenerator(name = "UseExistingOrGenerateUUIDGenerator", strategy = "jpa.UseExistingOrGenerateUUIDGenerator")
})
package org.omg.sysml.query.impl;

import org.hibernate.annotations.*;