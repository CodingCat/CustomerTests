// Your sbt build file. Guides on how to write one can be found at
// http://www.scala-sbt.org/0.13/docs/index.html

name := "CustomerTests"

organization := "com.microsoft"

scalaVersion := "2.11.8"

val sparkVersion = "2.1.0"

libraryDependencies ++= Seq("org.slf4j" % "slf4j-api" % "1.7.5",
  "com.github.scopt" %% "scopt" % "3.3.0",
  "com.twitter" %% "util-jvm" % "6.23.0" % "provided",
  "org.scalatest" %% "scalatest" % "2.2.1" % "test",
  "org.yaml" % "snakeyaml" % "1.17",
  "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "2.1.2",
  "org.scalatest" % "scalatest_2.11" % "3.0.1",
  "org.scala-lang" % "scala-library" % s"${scalaVersion.value}",
  "org.apache.spark" % "spark-sql_2.11" % s"$sparkVersion",
  "org.apache.spark" % "spark-mllib_2.11" % s"$sparkVersion",
  "org.apache.spark" % "spark-hive_2.11" % s"$sparkVersion",
  "net.java.dev.jets3t" % "jets3t" % "0.9.4",
  "org.apache.hadoop" % "hadoop-aws" % "2.7.3",
  "com.amazonaws" % "aws-java-sdk-s3" % "1.11.95")

fork := true

licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0"))

assemblyMergeStrategy  in assembly <<= (mergeStrategy in assembly) { (old) => {
  case x if Assembly.isConfigFile(x) =>
    MergeStrategy.concat
  case PathList(ps@_*) if Assembly.isReadme(ps.last) || Assembly.isLicenseFile(ps.last) =>
    MergeStrategy.rename
  case PathList("META-INF", xs@_*) =>
    (xs map {
      _.toLowerCase
    }) match {
      case ("manifest.mf" :: Nil) | ("index.list" :: Nil) | ("dependencies" :: Nil) =>
        MergeStrategy.discard
      case ps@(x :: xs) if ps.last.endsWith(".sf") || ps.last.endsWith(".dsa") =>
        MergeStrategy.discard
      case "plexus" :: xs =>
        MergeStrategy.discard
      case "services" :: xs =>
        MergeStrategy.filterDistinctLines
      case ("spring.schemas" :: Nil) | ("spring.handlers" :: Nil) =>
        MergeStrategy.filterDistinctLines
      case _ => MergeStrategy.first
    }
  case _ => MergeStrategy.first
}
}
