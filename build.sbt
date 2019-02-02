name := """SysML-v2-API-Services"""
organization := "org.omg"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.6"

libraryDependencies += guice
libraryDependencies += "org.hibernate" % "hibernate-core" % "5.4.1.Final"
libraryDependencies += "org.hibernate" % "hibernate-jpamodelgen" % "5.4.1.Final"
libraryDependencies += "org.postgresql" % "postgresql" % "42.2.5"
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-annotations" % "2.9.8"
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % "2.9.8"
libraryDependencies += "com.fasterxml.jackson.datatype" % "jackson-datatype-hibernate5" % "2.9.8"

javacOptions ++= Seq("-s", "app")