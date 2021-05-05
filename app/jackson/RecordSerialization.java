/*
 * SysML v2 REST/HTTP Pilot Implementation
 * Copyright (C) 2020  InterCAX LLC
 * Copyright (C) 2020  California Institute of Technology ("Caltech")
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 * @license LGPL-3.0-or-later <http://spdx.org/licenses/LGPL-3.0-or-later>
 */

package jackson;

import com.fasterxml.jackson.core.JsonParser;
import org.omg.sysml.lifecycle.impl.BranchImpl;
import org.omg.sysml.lifecycle.impl.CommitImpl;
import org.omg.sysml.lifecycle.impl.ProjectImpl;
import org.omg.sysml.record.Record;
import org.omg.sysml.record.impl.RecordImpl_;

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

    public static class BranchDeserializer extends RecordDeserializer<BranchImpl> {
        public BranchDeserializer(EntityManager entityManager) {
            super(entityManager);
        }

        public BranchDeserializer() {
            super();
        }

        @Override
        protected Class<BranchImpl> getRecordClass() {
            return BranchImpl.class;
        }
    }
}
