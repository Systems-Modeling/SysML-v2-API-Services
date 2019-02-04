name := """SysML-v2-API-Services"""
organization := "org.omg"

version := "2019-02-04"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.6"

libraryDependencies += guice
libraryDependencies += "com.google.code.gson" % "gson" % "2.8.5"
libraryDependencies += "com.datastax.cassandra" % "cassandra-driver-core" % "3.3.0"
libraryDependencies += "com.datastax.cassandra" % "cassandra-driver-extras" % "3.3.0"
libraryDependencies += "io.swagger" % "swagger-play2_2.12" % "1.6.0"

