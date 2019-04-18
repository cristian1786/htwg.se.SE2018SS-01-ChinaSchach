name          := "htwg-se-SE2018SS-01-ChinaSchach"
organization  := "de.htwg.se"
version       := "0.0.1"
scalaVersion  := "2.11.8"
scalacOptions := Seq("-unchecked", "-feature", "-deprecation", "-encoding", "utf8")

resolvers += Resolver.jcenterRepo

libraryDependencies ++= {
  val scalaTestV       = "3.0.0-M15"
  val scalaMockV       = "3.2.2"
  Seq(
    "org.scalatest" %% "scalatest"                   % scalaTestV       % "test",
    "org.scalamock" %% "scalamock-scalatest-support" % scalaMockV       % "test"
  )
}


libraryDependencies := {
  libraryDependencies.value ++ Seq(
    "junit" % "junit" % "4.8" % "test",
    "org.scala-lang" % "scala-swing" % "2.11+",
    "org.apache.commons" % "commons-lang3" % "3.4",
    "org.apache.commons" % "commons-io" % "1.3.2",
    "org.scala-lang.modules" %% "scala-xml" % "1.0.6",
    "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.4",
    "org.scala-lang.modules" %% "scala-swing" % "2.0.0-M2")
}

coverageExcludedPackages := "src\\main\\scala\\htwg\\se\\ChinaSchach\\aview"

fork in run := true

trapExit := false
