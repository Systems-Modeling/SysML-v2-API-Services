name := """SysML-v2-API-Services"""
organization := "org.omg"

version := "2022-11-rc1"

javacOptions ++= Seq("-source", "11", "-target", "11", "-Xlint")

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.6"

libraryDependencies += guice
libraryDependencies += "org.hibernate" % "hibernate-core" % "5.4.1.Final"
libraryDependencies += "org.hibernate" % "hibernate-jpamodelgen" % "5.4.1.Final"
libraryDependencies += "org.postgresql" % "postgresql" % "42.2.5"
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-annotations" % "2.9.8"
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % "2.9.8"
libraryDependencies += "com.fasterxml.jackson.datatype" % "jackson-datatype-hibernate5" % "2.9.8"
libraryDependencies += "io.swagger" % "swagger-play2_2.12" % "1.6.0"
libraryDependencies += "org.reflections" % "reflections" % "0.9.10"

javacOptions ++= Seq("-s", "app")

// https://stackoverflow.com/questions/42568234/intellij-idea-support-for-immutables-with-sbt
// tell sbt (and by extension IDEA) that there is source code in target/generated_sources
managedSourceDirectories in Compile += baseDirectory.value / "generated"
// before compilation happens, create the target/generated_sources directory
compile in Compile := (compile in Compile).dependsOn(Def.task({
  (baseDirectory.value / "generated").mkdirs()
})).value
// tell the java compiler to output generated source files to target/generated_sources
javacOptions in Compile ++= Seq("-s", "generated")

sources in(Compile, doc) := Seq.empty
publishArtifact in(Compile, packageDoc) := false

// https://github.com/playframework/playframework/issues/8286#issuecomment-488733669
// hopefully fixed in Play 2.8
PlayKeys.devSettings += "play.server.http.idleTimeout" -> "infinite"
