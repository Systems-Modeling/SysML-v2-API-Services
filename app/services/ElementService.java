package services;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.utils.UUIDs;
import dao.CassandraSessionBuilder;
import models.Element;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author Manas Bajaj
 *
 * Main service that provides CRUD operations for all SysML v2 elements
 */

public class ElementService {
    @Inject private CassandraSessionBuilder sessionBuilder;

    public Set<Element> getAll() {
        Set<Element> elements = new HashSet<>();
        ResultSet resultSet = sessionBuilder.getSession().execute("select * from sysml2.elements;");
        resultSet.forEach( r -> {
            if(r!=null) elements.add(new Element(r.getUUID(0), r.getString(1)));
        });

        return elements;
    }

    public Element getById(UUID identifier) {
        ResultSet resultSet = sessionBuilder.getSession().execute("select * from sysml2.elements where id = " + identifier);
        Row result = resultSet.one();
        if(result!=null)
            return new Element(result.getUUID(0), result.getString(1));
        else
            return null;
    }

    public Element create(Element elem) {
        if(elem!=null) {
            UUID elementIdentifier = elem.getIdentifier();
            if(elementIdentifier == null) elementIdentifier = UUIDs.timeBased();
            String cqlCommand = String.format("INSERT INTO sysml2.elements(id,name) VALUES (%s,'%s');",elementIdentifier, elem.getName());
            sessionBuilder.getSession().execute(cqlCommand);
            return getById(elem.getIdentifier());
        }
        else
            return null;
    }
}
