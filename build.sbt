import sbt._
import sbt.Keys._
import uk.gov.hmrc.SbtAutoBuildPlugin
import uk.gov.hmrc.versioning.SbtGitVersioning.autoImport.majorVersion

val appName = "play-time"

val scala2_13 = "2.13.12"

lazy val PlayTime = (project in file("."))
  .enablePlugins(SbtAutoBuildPlugin)
  .settings(
    name := appName,
    majorVersion := 0,
    scalaVersion := scala2_13,
    isPublicArtefact := true,
    libraryDependencies ++= Seq(
      playJson,
      scalaTest,
      pegdown,
      mockito,
      hamcrest
    )
  )

val playJson = "com.typesafe.play" %% "play-json" % "2.9.4" % "provided"

val scalaTest = "org.scalatest" %% "scalatest" % "3.0.8" % scope
val mockito = "org.mockito" % "mockito-all" % "1.10.19" % scope
val pegdown = "org.pegdown" % "pegdown" % "1.6.0" % scope
val hamcrest = "org.hamcrest" % "hamcrest-all" % "1.3" % scope

lazy val scope = "test"
