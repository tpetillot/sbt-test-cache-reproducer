scalaVersion := "3.8.4"

lazy val root = rootProject
  .settings(
    name := "sbt-cache-reproducer",
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.2.19" % Test
    )
  )
