name := "Cafebabe"

version := "1.2.1"

scalaVersion := "2.12.1"

crossScalaVersions := Seq("2.11.8", "2.12.1")

scalacOptions += "-deprecation"

scalacOptions += "-unchecked"

scalacOptions += "-Xexperimental"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0" % "test"
