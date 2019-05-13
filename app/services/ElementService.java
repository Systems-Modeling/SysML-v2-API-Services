package services;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.utils.UUIDs;
import dao.CassandraSessionBuilder;
import models.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author Manas Bajaj
 *
 * Main service that provides CRUD operations for all SysML v2 elements
 */

@Singleton
public class ElementService {

    @Inject private CassandraSessionBuilder sessionBuilder;

    public Set<Element> getAll() {
        Set<Element> elements = new HashSet<>();
        ResultSet resultSet = sessionBuilder.getSession().execute("select identifier, name, description, parent_model, type from sysml2.elements;");
        for(Row r: resultSet)
            elements.add(new Element(r.getUUID(0), r.getString(1), r.getString(2), r.getUUID(3), r.getString(4)));

        return elements;
    }

    public Element getById(UUID identifier) {
        ResultSet resultSet = sessionBuilder.getSession().execute("select identifier, name, description, parent_model, type from sysml2.elements where identifier = " + identifier);
        Row result = resultSet.one();
        if(result!=null)
            return new Element(result.getUUID(0), result.getString(1), result.getString(2), result.getUUID(3),result.getString(4));
        else
            return null;
    }

    public Element getById(UUID modelId, UUID elementId) {
        Element element = getById(elementId);
        if(element.parent_model.equals(modelId))
            return element;
        else
            return null;
    }

    public Set<Element> getByModelId(UUID modelId) {
        Set<Element> elements = new HashSet<>();
        ResultSet resultSet = sessionBuilder.getSession().execute("select identifier, name, description, parent_model, type from sysml2.elements where parent_model = " + modelId + " allow filtering;");
        for(Row r: resultSet)
            elements.add(new Element(r.getUUID(0), r.getString(1), r.getString(2), r.getUUID(3), r.getString(4)));

        return elements;
    }

    public Element create(Element elem) {
        if(elem!=null) {
            UUID elementIdentifier = elem.identifier;
            if(elementIdentifier == null) elementIdentifier = UUIDs.timeBased();

            if(elem.name.startsWith("'"))
                elem.name = "'" + elem.name;

            if(elem.name.endsWith("'"))
                elem.name = elem.name + "'";

            String cqlCommand = String.format("INSERT INTO sysml2.elements(identifier,name,description, parent_model, type) " +
                            "VALUES (%s,'%s','%s',%s,'%s')", elementIdentifier, elem.name, elem.description, elem.parent_model, elem.type);

            sessionBuilder.getSession().execute(cqlCommand);
            return getById(elementIdentifier);
        }
        else
            return null;
    }


}
