name := """SysML-v2-API-Services"""
organization := "org.omg"

version := "2024-07"

scalaVersion := "2.12.20"

lazy val root = (project in file("."))
  .enablePlugins(PlayJava)
  .settings(
    libraryDependencies ++= Seq(
      "com.google.inject" % "guice" % "5.1.0",
      "com.typesafe.play" %% "play-guice" % "2.8.20",
      "com.typesafe.play" %% "play" % "2.8.20", // Core Play dependency
      "org.hibernate" % "hibernate-core" % "5.4.1.Final",
      "org.hibernate" % "hibernate-jpamodelgen" % "5.4.1.Final",
      "org.postgresql" % "postgresql" % "42.2.5",
      "com.fasterxml.jackson.core" % "jackson-annotations" % "2.9.8",
      "com.fasterxml.jackson.core" % "jackson-databind" % "2.9.8",
      "com.fasterxml.jackson.datatype" % "jackson-datatype-hibernate5" % "2.9.8",
      "io.swagger" % "swagger-play2_2.12" % "1.6.0",
      "org.reflections" % "reflections" % "0.9.10",
      "com.typesafe.play" %% "twirl-api" % "1.5.1",
      "org.scala-lang.modules" %% "scala-xml" % "2.1.0"
    )
  )

dependencyOverrides += "org.scala-lang.modules" %% "scala-xml" % "2.1.0"

ThisBuild / libraryDependencySchemes += "org.scala-lang.modules" %% "scala-xml" % "early-semver"

javacOptions ++= Seq("-source", "11", "-target", "11", "-Xlint")
fork in run := true

javaOptions ++= Seq(
  "--add-opens", "java.base/java.lang=ALL-UNNAMED",
  "--add-opens", "java.base/java.lang.reflect=ALL-UNNAMED",
  "--add-opens", "java.base/sun.reflect.annotation=ALL-UNNAMED"
)
