package dao;

import org.omg.sysml.lifecycle.Commit;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.query.Query;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface ElementDao extends Dao<Element> {

    Set<Element> findAllByCommit(Commit commit);

    Optional<Element> findByCommitAndId(Commit commit, UUID id);

    Set<Element> findRootsByCommit(Commit commit);

    Set<Element> findByCommitAndQuery(Commit commit, Query query);
}
