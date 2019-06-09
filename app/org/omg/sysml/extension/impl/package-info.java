@AnyMetaDefs(value = {
        @AnyMetaDef(name = "ModelMetaDef", metaType = "string", idType = "java.util.UUID",
                metaValues = {
                        @MetaValue(value = "Model", targetEntity = ModelImpl.class),
                }),
})
package org.omg.sysml.extension.impl;

import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.AnyMetaDefs;
import org.hibernate.annotations.MetaValue;