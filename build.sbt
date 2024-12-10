ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.4"

lazy val root = (project in file("."))
  .settings(
    name := "match-prefix",
    idePackagePrefix := Some("com.dinso")
  )

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.19" % Test
libraryDependencies += "com.typesafe" % "config" % "1.4.3" // Use the latest version
