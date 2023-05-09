resolvers ++= Seq("hmrc-releases" at "https://artefacts.tax.service.gov.uk/artifactory/hmrc-releases/")
resolvers += Resolver.url("hmrc-sbt-plugin-releases", url("https://artefacts.tax.service.gov.uk/artifactory/hmrc-sbt-plugin-releases"))(Resolver.ivyStylePatterns)

addSbtPlugin("uk.gov.hmrc" % "sbt-auto-build" % "3.9.0")
