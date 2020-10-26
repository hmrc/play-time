import sbt.{Def, _}
import sbt.Keys._
import uk.gov.hmrc.SbtAutoBuildPlugin
import uk.gov.hmrc.versioning.SbtGitVersioning
import uk.gov.hmrc.versioning.SbtGitVersioning.autoImport.majorVersion
import uk.gov.hmrc.SbtArtifactory
import uk.gov.hmrc.SbtArtifactory.autoImport.makePublicallyAvailableOnBintray

val appName = "play-time"

val scala2_12 = "2.12.11"

lazy val PlayTime = (project in file("."))
  .enablePlugins(SbtAutoBuildPlugin, SbtGitVersioning, SbtArtifactory)
  .settings(
    name := appName,
    majorVersion := 0,
    scalaVersion := scala2_12,
    makePublicallyAvailableOnBintray := true,
    libraryDependencies ++= Seq(
      playJson,
      jodaTime,
      scalaTest,
      pegdown,
      mockito,
      hamcrest
    )
  )

val playJson = "com.typesafe.play" %% "play-json" % "2.6.14" % "provided"
val jodaTime = "joda-time" % "joda-time" % "2.10.6"

val scalaTest = "org.scalatest" %% "scalatest" % "3.0.8" % scope
val mockito = "org.mockito" % "mockito-all" % "1.10.19" % scope
val pegdown = "org.pegdown" % "pegdown" % "1.6.0" % scope
val hamcrest = "org.hamcrest" % "hamcrest-all" % "1.3" % scope

lazy val scope = "test"
