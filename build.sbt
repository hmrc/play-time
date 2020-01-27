import sbt.{Def, _}
import sbt.Keys._
import uk.gov.hmrc.SbtAutoBuildPlugin
import uk.gov.hmrc.versioning.SbtGitVersioning
import uk.gov.hmrc.versioning.SbtGitVersioning.autoImport.majorVersion
import uk.gov.hmrc.SbtArtifactory
import uk.gov.hmrc.SbtArtifactory.autoImport.makePublicallyAvailableOnBintray

val appName = "play-time"

lazy val PlayTime = (project in file("."))
  .enablePlugins(SbtAutoBuildPlugin, SbtGitVersioning, SbtArtifactory)
  .settings(majorVersion := 0)
  .settings(makePublicallyAvailableOnBintray := true)
  .settings(
    name := appName,
    scalaVersion := "2.11.12",
    crossScalaVersions := Seq("2.11.12"),
    libraryDependencies ++= Seq(
      playJson,
      jodaTime,
      scalaTest,
      pegdown,
      mockito,
      hamcrest
    ),
    developersList()
  )

val playJson = "com.typesafe.play" %% "play-json" % "2.3.10" % "provided"
val jodaTime = "joda-time" % "joda-time" % "2.10"

val scalaTest = "org.scalatest" %% "scalatest" % "2.2.6" % scope
val mockito = "org.mockito" % "mockito-all" % "1.9.5" % scope
val pegdown = "org.pegdown" % "pegdown" % "1.5.0" % scope
val hamcrest = "org.hamcrest" % "hamcrest-all" % "1.3" % scope

lazy val scope = "test"

def developersList(): Def.Setting[List[Developer]] = developers := List[Developer]()

