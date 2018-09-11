package services;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.utils.UUIDs;
import dao.CassandraSessionBuilder;

import models.Relationship;
import play.Logger;
import play.Logger.*;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author Manas Bajaj
 *
 * Main service that provides CRUD operations for all SysML v2 elements
 */

public class RelationshipService {
    @Inject private CassandraSessionBuilder sessionBuilder;

    final Logger.ALogger logger = Logger.of(this.getClass());

    public Set<Relationship> getAll() {
        Set<Relationship> relations = new HashSet<>();
        ResultSet resultSet = sessionBuilder.getSession().execute("select identifier, name, description, parent_model, " +
                "type, source_element_role, source_element, target_element_role, target_element from sysml2.relationships;");
        for(Row r: resultSet)
            relations.add(new Relationship(r.getUUID(0), r.getString(1), r.getString(2), r.getUUID(3), r.getString(4),
                    r.getString(5), r.getUUID(6), r.getString(7), r.getUUID(8) ));

        return relations;
    }

    public Relationship getById(UUID identifier) {
        ResultSet resultSet = sessionBuilder.getSession().execute("select identifier, name, description, parent_model, " +
                "type, source_element_role, source_element, target_element_role, target_element from sysml2.relationships where identifier = " + identifier + ";");

        Row result = resultSet.one();
        if(result!=null)
            return new Relationship(result.getUUID(0), result.getString(1), result.getString(2), result.getUUID(3), result.getString(4),
                    result.getString(5), result.getUUID(6), result.getString(7), result.getUUID(8) );
        else
            return null;
    }

    public Relationship create(Relationship relation) {
        if(relation!=null) {
            UUID identifier = relation.identifier;
            if(identifier == null) identifier = UUIDs.timeBased();

            String cqlCommand = String.format("INSERT INTO sysml2.relationships(identifier,name,description, parent_model, type, source_element_role, source_element, target_element_role, target_element)" +
                            " VALUES (%s,%s,%s, %s, %s, %s, %s, %s, %s);", identifier, relation.name, relation.description, relation.parent_model, relation.type,
                            relation.source_element_role, relation.source_element, relation.target_element_role, relation.target_element);

            sessionBuilder.getSession().execute(cqlCommand);
            return getById(identifier);
        }
        else
            return null;
    }
}
