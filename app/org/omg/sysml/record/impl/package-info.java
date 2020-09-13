@AnyMetaDefs(value = {
        @AnyMetaDef(name = "RecordMetaDef", metaType = "string", idType = "java.util.UUID",
                metaValues = {
                        @MetaValue(value = "Commit", targetEntity = CommitImpl.class),
                        @MetaValue(value = "ElementIdentity", targetEntity = ElementIdentityImpl.class),
                        @MetaValue(value = "ElementVersion", targetEntity = ElementVersionImpl.class),
                        @MetaValue(value = "Project", targetEntity = ProjectImpl.class),
                        @MetaValue(value = "Query", targetEntity = QueryImpl.class),
                        @MetaValue(value = "Record", targetEntity = RecordImpl.class),
                }),
})

@GenericGenerators(value = {
        @GenericGenerator(name = "UseExistingOrGenerateUUIDGenerator", strategy = "jpa.UseExistingOrGenerateUUIDGenerator")
})
package org.omg.sysml.record.impl;

import org.hibernate.annotations.*;
import org.omg.sysml.lifecycle.impl.CommitImpl;
import org.omg.sysml.lifecycle.impl.ElementIdentityImpl;
import org.omg.sysml.lifecycle.impl.ElementVersionImpl;
import org.omg.sysml.lifecycle.impl.ProjectImpl;
import org.omg.sysml.query.impl.QueryImpl;