package models;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

// TODO Jar after modification. See README.

@Entity
@Table(name = "source_target_query")
public class SourceTargetQuery {
    @EmbeddedId
    private SourceTargetKey key;

    @Column(name = "relationship_id", nullable = false)
    private UUID relationship_id;

    public SourceTargetKey getKey() {
        return key;
    }

    public void setKey(Element source, Element target) {
        //Kundera stores the source_id and target_id as blobs if we try and use a JoinColumn
        this.key = new SourceTargetKey(source.getId(), target.getId());
    }

    public UUID getRelationshipId() {
        return relationship_id;
    }

    public void setRelationship(Relationship relationship) {
        //Kundera stores the relationship_id as blobs if we try and use a JoinColumn
        this.relationship_id = relationship.getId();
    }
}
