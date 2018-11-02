# SysML v2 API and Services
Pilot implementation of SysML v2 API and Services using Play framework. More details coming soon...

# Setup
Configure the following system properties via the command line or specify them as properties within persistence.xml (not recommended)

* kundera.nodes
* kundera.port
* kundera.username
* kundera.password
* kundera.keyspace

The recommended way to run this project is by importing the SBT file into your IDE of choice. If you'd like to run it directly from the command line, you can use something similar to this example.

```java -Dkundera.nodes=127.0.0.1 -Dkundera.port=9160 -Dkundera.keyspace=yourkeyspace -Dkundera.username=cassandra -Dkundera.password=cassandra -classpath "/Users/user1/Library/Application Support/IntelliJIdea2018.2/Scala/launcher/sbt-launch.jar" xsbt.boot.Boot "project root" run```