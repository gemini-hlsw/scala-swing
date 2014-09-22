import com.typesafe.tools.mima.plugin.{MimaPlugin, MimaKeys}

scalaModuleSettings

organization := "org.scala-lang"

name                       := "scala-swing"

version := "2.0.0-SNAPSHOT"

scalaVersion := "2.10.4"

scalacOptions ++= Seq("-deprecation", "-feature", "-target:jvm-1.7")

snapshotScalaBinaryVersion := "2.10"

// important!! must come here (why?)
scalaModuleOsgiSettings

OsgiKeys.exportPackage     := Seq(s"scala.swing.*;version=${version.value}")

MimaPlugin.mimaDefaultSettings

MimaKeys.previousArtifact  := Some(organization.value % s"${name.value}_2.10" % "1.0.1")

// run mima during tests
test in Test := {
  MimaKeys.reportBinaryIssues.value
  (test in Test).value
}


// set the prompt (for this build) to include the project id.
shellPrompt in ThisBuild := { state => Project.extract(state).currentRef.project + "> " }


lazy val swing = project.in( file(".") )

lazy val examples = project.in( file("examples") )
  .dependsOn(swing)
  .settings(
    scalaVersion := "2.10.4",
    fork in run := true,
    fork := true
  )





