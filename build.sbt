name := "uuid-maker"

version := "0.0.1"

scalaVersion := "2.12.4"

libraryDependencies += "org.scalafx" %% "scalafx" % "8.0.144-R12"

fork := true

mainClass in assembly := Some("tech.grom.uuidMaker.UuidMaker")