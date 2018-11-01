name := """SysML-v2-API-Services"""
organization := "org.omg"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.7"

libraryDependencies += guice
libraryDependencies += "com.impetus.kundera.core" % "kundera-core" % "3.13"
libraryDependencies += "com.impetus.kundera.client" % "kundera-cassandra" % "3.13"

libraryDependencies += "javax.persistence" % "javax.persistence-api" % "2.2"

libraryDependencies += "com.fasterxml.jackson.core" % "jackson-core" % "2.9.7"
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-annotations" % "2.9.7"
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % "2.9.7"