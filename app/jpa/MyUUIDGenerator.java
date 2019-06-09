package jpa;

import org.omg.sysml.metamodel.Element;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.UUIDGenerator;

import java.io.Serializable;

public class MyUUIDGenerator extends UUIDGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        Element element;
        if (object instanceof Element && (element = (Element) object).getIdentifier() != null) {
            return element.getIdentifier();
        }
        return super.generate(session, object);
    }
}
