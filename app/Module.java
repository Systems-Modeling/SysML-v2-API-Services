import com.google.inject.AbstractModule;
import dao.ElementDao;
import dao.ModelDao;
import dao.RelationshipDao;
import dao.impl.jpa.JpaElementDao;
import dao.impl.jpa.JpaModelDao;
import dao.impl.jpa.JpaRelationshipDao;
import jackson.databind.ObjectMapperFactory;
import jackson.databind.impl.HibernateObjectMapperFactory;
import jpa.manager.JPAManager;
import jpa.manager.impl.HibernateManager;

public class Module extends AbstractModule {

    @Override
    protected void configure() {
        bind(JPAManager.class).to(HibernateManager.class).asEagerSingleton();
        bind(ObjectMapperFactory.class).to(HibernateObjectMapperFactory.class).asEagerSingleton();
        bind(ElementDao.class).to(JpaElementDao.class);
        bind(ModelDao.class).to(JpaModelDao.class);
        bind(RelationshipDao.class).to(JpaRelationshipDao.class);
    }
}