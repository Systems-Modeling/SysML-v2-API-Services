package dao;

import org.omg.sysml.extension.Project;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.versioning.Commit;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface ElementDao extends Dao<Element> {
    Optional<Element> persist(Element element);

    Optional<Element> update(Element element);

    Optional<Element> findById(UUID id);

    List<Element> findAll();

    void delete(Element element);

    void deleteAll();

    List<Element> findAllByProject(Project project);

    Set<Element> findAllByCommit(Commit commit);

    Optional<Element> findByProjectAndId(Project project, UUID id);

    Optional<Element> findByCommitAndId(Commit commit, UUID id);
}
