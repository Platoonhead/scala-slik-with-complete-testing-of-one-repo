name := """slickApp"""

version := "1.0"

scalaVersion := "2.12.1"

// https://mvnrepository.com/artifact/org.scalatest/scalatest_2.11
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0" % "test"


// Uncomment to use Akka
//libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.11"

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.2.0",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.2.0",
  "org.postgresql" % "postgresql" % "9.4.1212",
  "mysql" % "mysql-connector-java" % "5.1.25"
)

// https://mvnrepository.com/artifact/com.h2database/h2
libraryDependencies += "com.h2database" % "h2" % "1.4.192"


