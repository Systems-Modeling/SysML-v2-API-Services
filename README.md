# SysML v2 API and Services
Pilot implementation of SysML v2 API and Services using Play framework. More details coming soon...

## Setup
Configure the following system properties via the command line or specify them as properties within persistence.xml (not recommended). An asterisks indicates required properties.

* \* `kundera.nodes`: Address(es) for Cassandra nodes.
* \* `kundera.port`
* \* `kundera.username`
* \* `kundera.password`
* \* `kundera.keyspace`: The top-level namespace under which data will be persisted in Cassandra. See [CREATE NAMESPACE documentation](https://docs.datastax.com/en/cql/3.3/cql/cql_reference/cqlCreateKeyspace.html) on how to create one before running this application. Example CQL command: `create keyspace sysml2 with replication = {'class' : 'SimpleStrategy', 'replication_factor' : 1};`
* `kundera.ddl.auto.prepare`: Kundera can auto-generate schemas in Cassandra. A compatible schema is necessary and the auto-generated schema is what the application is tested against. For the first execution it is recommended to use `create` (clears any existing contents and generates schema), followed by `validate` on subsequent runs. See [Kundera's documentation](https://github.com/Impetus/Kundera/wiki/Schema-Generation) for details.
* `kundera.show.query`: Shows queries as they are executed in Cassandra. Beneficial for debugging.

The recommended way to run this project is by importing the SBT file into your IDE of choice. If you'd like to run it directly from the command line, you can use something similar to this example.

```
sbt \
    -Dkundera.nodes=localhost \
    -Dkundera.port=9160 \
    -Dkundera.username=cassandra \
    -Dkundera.password=cassandra \
    -Dkundera.keyspace=sysml2 \
    -Dkundera.ddl.auto.prepare=create \
    -Dkundera.show.query=true \
run
```

## Known Issues

#### Classpath Conflict
There is a classpath conflict between Play Framework and Kundera, likely related to Play's dynamic class reloading, that makes it such that `Entities` (in the `models` package) are not visible to Kundera during initialization. The current mitigation is to jar the `Entities` and its dependencies like the `jackson` package and including them in Play's `lib` package. This means that modifications to `models` and `jackson` in the source code will have no effect until they are manually jarred and placed in `lib`. A cleaner mitigation could be to build these classes in a separate project and make the generated jar a dependency of this project.

Play/SBT compiles classes to `target/scala-*/classes` **after dynamic compilation on first API request**. One way to do the manual step described above is to zip the relevant compiled classes from this directory: `rm ./lib/sysml2-entities.jar; cd ./target/scala-*/classes; zip -r ../../../lib/sysml2-entities.jar ./models ./jackson; cd ../../../`.