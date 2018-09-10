package dao;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.QueryOptions;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.exceptions.AuthenticationException;
import com.datastax.driver.core.exceptions.NoHostAvailableException;
import play.api.Configuration;
import play.Logger;
import play.inject.ApplicationLifecycle;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;

public class CassandraSessionBuilder {

    private Configuration configuration;
    private ApplicationLifecycle lifecycle;
    private Session session = null;
    private Cluster cluster = null;
    private QueryOptions qo = new QueryOptions();
    String host;
    String username;
    String password;
    Integer port;
    String keyspace;

    final Logger.ALogger logger = Logger.of(this.getClass());

    @Inject
    public CassandraSessionBuilder(Configuration configuration, ApplicationLifecycle lifecycle) {
        this.configuration = configuration;
        this.lifecycle = lifecycle;
        this.host = configuration.underlying().getString("db.host");
        this.username = configuration.underlying().getString("db.username");
        this.password = configuration.underlying().getString("db.password");
        this.port = configuration.underlying().getInt("db.port");
        this.keyspace = configuration.underlying().getString("db.keyspace");

        lifecycle.addStopHook(() -> {
            Logger.info("Shutting down database connection");
            stop();
            return CompletableFuture.completedFuture(null);
        });
    }

    public Session getSession() {
        try {
            if (cluster == null) {
                cluster = Cluster.builder().addContactPoint(host).withPort(port)
                        .withCredentials(username, password)
                        .withQueryOptions(qo)
                        .build();
            }

            if (session == null) {
                session = cluster.connect(keyspace);
                logger.info("Cassandra session successfully created.");
            }

            return session;

        } catch (NoHostAvailableException | AuthenticationException e) {
            logger.error(e.getMessage(),e);
            return null;
        }
    }

    private void stop() {
        if (session != null)
            session.close();

        if (cluster != null)
            cluster.close();

        logger.info("Closed Cassandra session and cluster.");
    }

}
