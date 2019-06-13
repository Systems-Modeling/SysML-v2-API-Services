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

import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.AnyMetaDefs;
import org.hibernate.annotations.MetaValue;

// TODO Abstract this concept to cli option
import org.hibernate.annotations.GenericGenerators;
import org.hibernate.annotations.GenericGenerator;