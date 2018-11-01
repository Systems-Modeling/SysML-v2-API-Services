import com.google.inject.AbstractModule;
import kundera.JPAManager;
import kundera.JPAManagerImpl;

public class Module extends AbstractModule {

    @Override
    protected void configure() {
        bind(JPAManager.class).to(JPAManagerImpl.class);
    }
}