# SysML v2 API and Services
Pilot implementation of REST/HTTP Platform-specific model (PSM) of SysML v2 API and Services, managed by the SysML v2 Submission Team (SST).

## Usage Scenarios
There are two main usage scenarios. 

1. **Scenario 1: Accessing SysML v2 REST/HTTP API deployed on SST servers**
You do not need to clone this repository or setup anything on your local machine. You can access the REST/HTTP API on one of our deployed servers via Swagger doc, or a REST API client (e.g Postman) on your local machine. Ignore the rest of the instructions if you are interested in this scenario. Contact the SST to learn more.

2. **Scenario 2: Running the SysML v2 REST/HTTP API and Services locally on your machine**
You can clone this repository, setup the backend database (PostgreSQL), and run the pilot implementation of SysML v2 API & Services locally on your machine. The same REST API that is deployed on the SST servers will be available on localhost. Follow the instructions below if you are interested in this scenario.

## Setting up pilot implementation of SysML v2 API and Services on your local machine

### Setup PostgreSQL
This pilot implementation uses PostgreSQL as the backend database. The easiest way to setup PostgreSQL locally is to run it as a Docker container.

1. Use the `docker pull` command to download the docker image for the latest version of PostgreSQL available on Docker Hub - https://hub.docker.com/_/postgres

2. Use the `docker run` command to start a docker container from the downloaded image, as shown below. This will initialize PostgreSQL database in a Docker container and make it available at port 5432 on your local machine.

`docker run --name sysml2-postgres -p 5432:5432 -e POSTGRES_PASSWORD=mysecretpassword -e POSTGRES_DB=sysml2 -d postgres`

3. Connect to the PostgreSQL database, running as a docker container, from any PostgreSQL client to verify. Use the following connection details. 
- Host = localhost
- Port = 5432
- User = postgres
- Password = mysecretpassword

### Setup Java 11
This pilot implementation uses JDK 11 for compiling and running SysML v2 API and Services. Download and setup JDK 11 on your machine. You can use Oracle JDK 11 or OpenJDK 11.

### Setup sbt
This pilot implementation uses the Play framework (https://www.playframework.com/) for the web-application and sbt (https://www.scala-sbt.org/) as the build tool. You need to setup sbt to be able to compile and run SysML v2 API and Services. There are two options to do this:

**1. Option 1** - Use IntelliJ as your IDE and install the Scala plugin as shown here - https://www.jetbrains.com/help/idea/discover-intellij-idea-for-scala.html. This will setup sbt and related infrastructure for compiling and running the SysML v2 API and Services web-application, either from within IntelliJ or from the command line.

**2. Option 2** - Install sbt as a standalone on your machine from here - https://www.scala-sbt.org/. You will be compiling and running SysML v2 API and Services web-application from the command line.

### Clone this repository
Clone this repository (SysML-v2-API-Services). Check out the latest release/tag (e.g. 2020-10) or the develop branch.

### Run SysML v2 API & Services
1. Run `sbt clean` command to clean the project, as shown below.

2. Run `sbt run` command to compile and run the SysML v2 API and Services web-application, as shown below.

```
User@Machine MINGW64 ~/MyGitRepos/SysML-v2-API-Services (develop)
$ sbt clean
[info] Loading global plugins from C:\Users\User\.sbt\1.0\plugins
[info] Loading settings for project sysml-v2-api-services-build from plugins.sbt,scaffold.sbt ...
[info] Loading project definition from C:\Users\User\MyGitRepos\SysML-v2-API-Services\project
[info] Loading settings for project root from build.sbt ...
[info] Set current project to SysML-v2-API-Services (in build file:/C:/Users/User/MyGitRepos/SysML-v2-API-Services/)
[success] Total time: 0 s, completed Nov 11, 2020, 10:54:27 AM

User@Machine MINGW64 ~/MyGitRepos/SysML-v2-API-Services (develop)
$ sbt run
[info] Loading global plugins from C:\Users\User\.sbt\1.0\plugins
[info] Loading settings for project sysml-v2-api-services-build from plugins.sbt,scaffold.sbt ...
[info] Loading project definition from C:\Users\User\MyGitRepos\SysML-v2-API-Services\project
[info] Loading settings for project root from build.sbt ...
[info] Set current project to SysML-v2-API-Services (in build file:/C:/Users/User/MyGitRepos/SysML-v2-API-Services/)
[info] Updating ...
[info] Done updating.
[warn] There may be incompatibilities among your library dependencies; run 'evicted' to see detailed eviction warnings.

--- (Running the application, auto-reloading is enabled) ---

[info] p.c.s.AkkaHttpServer - Listening for HTTP on /0:0:0:0:0:0:0:0:9000

(Server started, use Enter to stop and go back to the console...)
```

3. Open a web-browser and go to `localhost:9000/docs/`. This will start to load the application and compile the web-application which may take a few minutes. Once loaded, you will see the Swagger doc for the SysML v2 REST/HTTP API as shown below.

![REST/HTTP API](https://gist.githubusercontent.com/manasbajaj/0635b32fcf42a75eeca79744af953732/raw/0f73c2f3c8464bc36cb9341516bf9bdfb2342163/SysML-v2-REST-HTTP-API-Swagger-Doc.png)

4. You can now make REST/HTTP requests to your local SysML v2 API and Services web application either via Swagger doc, or via any REST/HTTP client such as Postman.
