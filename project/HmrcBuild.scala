import sbt.{Def, _}
import sbt.Keys._
import uk.gov.hmrc.SbtAutoBuildPlugin
import uk.gov.hmrc.versioning.SbtGitVersioning

object HmrcBuild extends Build {

  import BuildDependencies._
  import uk.gov.hmrc.DefaultBuildSettings._

  val appName = "play-time"

  lazy val PlayTime = (project in file("."))
    .enablePlugins(SbtAutoBuildPlugin, SbtGitVersioning)
    .settings(
      name := appName,
      scalaVersion := "2.11.11",
      crossScalaVersions := Seq("2.11.11"),
      libraryDependencies ++= Seq(
        Compile.playJson,
        Test.scalaTest,
        Test.pegdown,
        Test.mockito,
        Test.hamcrest
      ),
      Developers()
    )
}

private object BuildDependencies {

  object Compile {
    val playJson = "com.typesafe.play" %% "play-json" % "2.3.10" % "provided"
  }

  sealed abstract class Test(scope: String) {
    val scalaTest = "org.scalatest" %% "scalatest" % "2.2.6" % scope
    val mockito = "org.mockito" % "mockito-all" % "1.9.5" % scope
    val pegdown = "org.pegdown" % "pegdown" % "1.5.0" % scope
    val hamcrest = "org.hamcrest" % "hamcrest-all" % "1.3" % scope
  }

  object Test extends Test("test")

}

object Developers {

  def apply(): Def.Setting[List[Developer]] = developers := List[Developer]()
}
