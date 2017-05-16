
lazy val root = (project in file("."))
  .settings(
    name := "Cafebabe",
    version := "1.2.2",
    scalaVersion := "2.12.2",
    crossScalaVersions := Seq("2.11.8", "2.12.1"),
    scalacOptions += "-deprecation",
    scalacOptions += "-unchecked",
    scalacOptions += "-Xexperimental",

    logLevel := Level.Warn,
    resolvers += Resolver.sonatypeRepo("releases"),
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0" % "test"
  )
