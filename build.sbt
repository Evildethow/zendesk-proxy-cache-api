lazy val commonSettings = Seq(
  organization := "org.evildethow",
  version := "0.0.1-SNAPSHOT",
  scalaVersion := "2.11.8",
  scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")
)

lazy val core = project.
  settings(commonSettings: _*).
  settings(
    name := "zendesk-proxy-cache-api-core"
  )

lazy val server = project.
  settings(commonSettings: _*).
  settings(
    name := "zendesk-proxy-cache-api-server",
    libraryDependencies ++= {
      val akkaVersion = "2.4.7"
      val json4sVersion = "3.4.0"
      val slickVersion = "3.1.1"

      Seq(
        // akka dependencies
        "com.typesafe.akka" %% "akka-actor" % akkaVersion,
        "com.typesafe.akka" %% "akka-stream" % akkaVersion,
        "com.typesafe.akka" %% "akka-http-experimental" % akkaVersion,
        "com.typesafe.akka" %% "akka-http-core" % akkaVersion,

        // database dependencies
        "com.typesafe.slick" %% "slick" % slickVersion,
        "com.typesafe.slick" %% "slick-hikaricp" % slickVersion,
        "com.h2database" % "h2" % "1.3.175",
        "org.postgresql" % "postgresql" % "9.4-1201-jdbc41",
        "org.flywaydb" % "flyway-core" % "3.2.1",

        // json dependencies
        "de.heikoseeberger" %% "akka-http-json4s" % "1.7.0",
        "org.json4s" %% "json4s-native" % json4sVersion,
        "org.json4s" %% "json4s-jackson" % json4sVersion,

        // logging dependencies
        "ch.qos.logback" % "logback-classic" % "1.1.3",
        "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,

        // zendesk dependencies
        //"com.cloudbees.thirdparty" % "zendesk-java-client" % "0.5.0",

        // test dependencies
        "com.typesafe.akka" %% "akka-http-testkit" % akkaVersion % "test",
        "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test",
        "org.scalatest" %% "scalatest" % "2.2.6" % "test",
        "ru.yandex.qatools.embed" % "postgresql-embedded" % "1.13" % "test"
      )
    }
  ).dependsOn(core)

lazy val client = project.
  settings(commonSettings: _*).
  settings(
    name := "zendesk-proxy-cache-api-client"
  ).dependsOn(core)

enablePlugins(JavaAppPackaging)
enablePlugins(DockerPlugin)

dockerExposedPorts := Seq(9000)
dockerEntrypoint := Seq("bin/%s" format executableScriptName.value, "-Dconfig.resource=docker.conf")