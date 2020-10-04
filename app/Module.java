import com.google.inject.AbstractModule;
import config.MetamodelProvider;
import config.impl.JPAMetamodelProvider;
import dao.*;
import dao.impl.jpa.*;
import jackson.databind.ObjectMapperFactory;
import jackson.databind.impl.HibernateObjectMapperFactory;
import jpa.manager.JPAManager;
import jpa.manager.impl.HibernateManager;

public class Module extends AbstractModule {

    @Override
    protected void configure() {
        bind(MetamodelProvider.class).to(JPAMetamodelProvider.class).asEagerSingleton();
        bind(JPAManager.class).to(HibernateManager.class).asEagerSingleton();
        bind(ObjectMapperFactory.class).to(HibernateObjectMapperFactory.class).asEagerSingleton();
        bind(ElementDao.class).to(JpaElementDao.class);
        bind(ProjectDao.class).to(JpaProjectDao.class);
        bind(RelationshipDao.class).to(JpaRelationshipDao.class);
        bind(CommitDao.class).to(JpaCommitDao.class);
        bind(QueryDao.class).to(JpaQueryDao.class);
    }
}