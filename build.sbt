import sbtrelease.ReleaseStateTransformations._

lazy val root = (project in file(".")).
settings(
    inThisBuild(List(
        organization    := "liquidarmour",
        scalaVersion    := "2.12.2"
    )),
    name := "play-time",
    libraryDependencies ++= Seq(
      "joda-time" % "joda-time" % "2.9.9",
      "org.scalatest"     %% "scalatest"         % "3.0.1"         % Test,
      "org.mockito" % "mockito-all" % "1.9.5" % Test,
      "org.pegdown" % "pegdown" % "1.5.0" % Test,
      "org.hamcrest" % "hamcrest-all" % "1.3" % Test
    )
)


releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,              // : ReleaseStep
  inquireVersions,                        // : ReleaseStep
  runTest,                                // : ReleaseStep
  setReleaseVersion,                      // : ReleaseStep
  commitReleaseVersion,                   // : ReleaseStep, performs the initial git checks
  tagRelease,                             // : ReleaseStep
  setNextVersion,                         // : ReleaseStep
  commitNextVersion,                      // : ReleaseStep
  pushChanges                             // : ReleaseStep, also checks that an upstream branch is properly configured
)

pomExtra :=
  <licenses>
    <license>
      <name>Apache 2</name>
      <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>https://github.com/liquidarmour/play-time</url>
    <connection>git@github.com:liquidarmour/play-time.git</connection>
  </scm>
    <repositories>
      <repository>
        <id>liquidarmourreleases</id>
        <name>liquid-armour-releases</name>
        <url>https://dl.bintray.com/liquid-armour/releases/</url>
        <layout>default</layout>
      </repository>
    </repositories>
    <developers>
      <developer>
        <id>liquidarmour</id>
        <name>James Williams</name>
        <email>james@liquid-armour.co.uk</email>
        <url>https://github.com/liquidarmour</url>
      </developer>
    </developers>
