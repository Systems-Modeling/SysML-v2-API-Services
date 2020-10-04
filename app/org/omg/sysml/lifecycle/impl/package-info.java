@AnyMetaDefs(value = {
        @AnyMetaDef(name = "ProjectMetaDef", metaType = "string", idType = "java.util.UUID",
                metaValues = {
                        @MetaValue(value = "Project", targetEntity = ProjectImpl.class),
                })
})

@GenericGenerators(value = {
        @GenericGenerator(name = "UseExistingOrGenerateUUIDGenerator", strategy = "jpa.UseExistingOrGenerateUUIDGenerator")
})
package org.omg.sysml.lifecycle.impl;

import org.hibernate.annotations.*;