package services;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.utils.UUIDs;
import dao.CassandraSessionBuilder;
import models.Element;
import models.Model;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author Manas Bajaj
 *
 * Main service that provides CRUD operations for all SysML v2 models
 */

public class ModelService {
    @Inject private CassandraSessionBuilder sessionBuilder;

    public Set<Model> getAll() {
        Set<Model> models = new HashSet<>();
        ResultSet resultSet = sessionBuilder.getSession().execute("select identifier, name, description from sysml2.models;");
        for(Row r: resultSet)
            models.add(new Model(r.getUUID(0), r.getString(1), r.getString(2)));

        return models;
    }

    public Model getById(UUID identifier) {
        ResultSet resultSet = sessionBuilder.getSession().execute("select identifier, name, description from sysml2.models where identifier = " + identifier);
        Row result = resultSet.one();
        if(result!=null)
            return new Model(result.getUUID(0), result.getString(1), result.getString(2));
        else
            return null;
    }

    public Model create(Model model) {
        if(model!=null) {
            UUID modelIdentifier = model.identifier;
            if(modelIdentifier == null) modelIdentifier = UUIDs.timeBased();

            String cqlCommand = String.format("INSERT INTO sysml2.models(identifier,name,description) VALUES (%s,'%s','%s');",
                    modelIdentifier, model.name, model.description);

            sessionBuilder.getSession().execute(cqlCommand);
            return getById(modelIdentifier);
        }
        else
            return null;
    }
}
