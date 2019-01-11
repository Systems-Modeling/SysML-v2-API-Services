package models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

// TODO Jar after modification. See README.

@Embeddable
public class SourceTargetKey implements Serializable {
    @Column(name = "source_id", nullable = false)
    private UUID source_id;

    @Column(name = "target_id", nullable = false)
    private UUID target_id;

    public SourceTargetKey() {
    }

    public SourceTargetKey(UUID source_id, UUID target_id) {
        this.source_id = source_id;
        this.target_id = target_id;
    }

    public UUID getSourceId() {
        return source_id;
    }

    public void setSourceId(UUID sourceId) {
        this.source_id = sourceId;
    }

    public UUID getTargetId() {
        return target_id;
    }

    public void setTargetId(UUID targetId) {
        this.target_id = targetId;
    }
}
