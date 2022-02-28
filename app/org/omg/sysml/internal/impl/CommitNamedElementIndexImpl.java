package org.omg.sysml.internal.impl;

import org.hibernate.annotations.ManyToAny;
import org.omg.sysml.internal.CommitNamedElementIndex;
import org.omg.sysml.lifecycle.Commit;
import org.omg.sysml.lifecycle.impl.CommitImpl;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.impl.ElementImpl_;

import javax.persistence.*;
import java.util.Map;
import java.util.UUID;

@Entity(name = "CommitNamedElementIndex")
public class CommitNamedElementIndexImpl implements CommitNamedElementIndex {
    private UUID id;
    private Commit commit;
    private Map<String, Element> workingNamedElement;

    @Id
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    @JoinColumn(name = "id")
    @OneToOne(targetEntity = CommitImpl.class)
    @MapsId
    public Commit getCommit() {
        return commit;
    }

    @Override
    public void setCommit(Commit commit) {
        this.commit = commit;
    }

    @ManyToAny(metaDef = "ElementMetaDef", metaColumn = @Column(name = "workingNamedElementType"), fetch = FetchType.LAZY)
    @JoinTable(name = "CommitNamedElementIndex_workingNamedElement",
            joinColumns = {@JoinColumn(name = "commitNamedElementIndexId")},
            inverseJoinColumns = {@JoinColumn(name = "workingNamedElementId")})
    @MapKeyColumn(name = ElementImpl_.QUALIFIED_NAME)
    public Map<String, Element> getWorkingNamedElement() {
        return workingNamedElement;
    }

    public void setWorkingNamedElement(Map<String, Element> workingNamedElement) {
        this.workingNamedElement = workingNamedElement;
    }
}
