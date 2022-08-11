FROM phoenixintegration/mccdev-base:22.2.0-ubuntu.focal.openjdk11.SNAPSHOT

ARG SBT_VERSION=1.7.1

# Install sbt. Obtained from https://www.scala-sbt.org/1.x/docs/Installing-sbt-on-Linux.html
RUN apt-get update && \
    apt-get -y install apt-transport-https curl gnupg -yqq && \
    curl -L -o sbt-$SBT_VERSION.deb https://repo.scala-sbt.org/scalasbt/debian/sbt-$SBT_VERSION.deb && \
    dpkg -i sbt-$SBT_VERSION.deb && \
    rm sbt-$SBT_VERSION.deb && \
    apt-get update && \
    apt-get -y install sbt

# Copy the whole API Service base so we can compile and run in the docker container.
COPY . /sysml2-server

# Overwrite the persistence.xml with the docker specific persistence.xml.
COPY ./conf/META-INF/persistence-docker.xml /sysml2-server/conf/META-INF/persistence.xml

RUN cd /sysml2-server && sbt clean

EXPOSE 9000/tcp

WORKDIR /sysml2-server
ENTRYPOINT ["sbt", "run"]