@AnyMetaDefs(value = {
        @AnyMetaDef(name = "ProjectMetaDef", metaType = "string", idType = "java.util.UUID",
                metaValues = {
                        @MetaValue(value = "Project", targetEntity = ProjectImpl.class),
                }),
})

// TODO Abstract this concept to cli option
@GenericGenerators(value = {
        @GenericGenerator(name = "UseExistingOrGenerateUUIDGenerator", strategy = "jpa.UseExistingOrGenerateUUIDGenerator")
})
package org.omg.sysml.extension.impl;

import org.hibernate.annotations.*;

// TODO Abstract this concept to cli option