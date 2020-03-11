package jackson;

import com.fasterxml.jackson.core.JsonParser;
import org.omg.sysml.lifecycle.Commit;
import org.omg.sysml.lifecycle.Record;
import org.omg.sysml.lifecycle.impl.CommitImpl;
import org.omg.sysml.lifecycle.impl.ProjectImpl;
import org.omg.sysml.lifecycle.impl.RecordImpl;
import org.omg.sysml.lifecycle.impl.RecordImpl_;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.UUID;

public class RecordSerialization {
    public static class RecordSerializer extends JpaIdentitySerializer<Record> {

        @Override
        protected String getIdentityField() {
            return RecordImpl_.ID;
        }

        @Override
        protected Object serializeToIdentity(Record record) {
            return record != null ? record.getId() : null;
        }
    }

    public static abstract class RecordDeserializer<R extends Record> extends JpaIdentityDeserializer<R> {
        public RecordDeserializer(EntityManager entityManager) {
            super(entityManager);
        }

        public RecordDeserializer() {
            super();
        }

        protected abstract Class<R> getRecordClass();

        @Override
        protected boolean isIdentityField(String field) {
            return RecordImpl_.ID.equals(field);
        }

        @Override
        @SuppressWarnings("unchecked")
        protected R deserializeFromIdentity(JsonParser parser) throws IOException {
            UUID id = UUID.fromString(parser.getText());
            Record record = getEntityManager().find(getRecordClass(), id);
            if (record == null) {
                throw new IOException(new EntityNotFoundException("Record " + id + " not found."));
            }
            return (R) record;
        }
    }

    public static class CommitDeserializer extends RecordDeserializer<CommitImpl> {
        public CommitDeserializer(EntityManager entityManager) {
            super(entityManager);
        }

        public CommitDeserializer() {
            super();
        }

        @Override
        protected Class<CommitImpl> getRecordClass() {
            return CommitImpl.class;
        }
    }

    public static class ProjectDeserializer extends RecordDeserializer<ProjectImpl> {
        public ProjectDeserializer(EntityManager entityManager) {
            super(entityManager);
        }

        public ProjectDeserializer() {
            super();
        }

        @Override
        protected Class<ProjectImpl> getRecordClass() {
            return ProjectImpl.class;
        }
    }
}
