import com.google.inject.AbstractModule;
import jpa.JPAManager;
import jpa.JPAObjectMapper;
import jpa.KunderaManager;

public class Module extends AbstractModule {

    @Override
    protected void configure() {
        bind(JPAManager.class).to(KunderaManager.class);
        bind(JPAObjectMapper.class).asEagerSingleton();
    }
}