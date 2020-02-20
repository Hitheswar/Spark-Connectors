name := "Spark-Connectors"

version := "0.1"

scalaVersion := "2.11.8"

lazy val sparkVersion = "2.4.4"

resolvers ++= Seq(
  "apache-snapshots" at "http://repository.apache.org/snapshots/"
)

resolvers ++= Seq(
  "apache-snapshots" at "https://repo1.maven.org/maven2/"
)

//spark
libraryDependencies += "org.apache.spark" %% "spark-core" % sparkVersion
libraryDependencies += "org.apache.spark" %% "spark-sql" % sparkVersion

//cassandra
libraryDependencies += "com.datastax.spark" %% "spark-cassandra-connector" % "2.4.1"
libraryDependencies += "com.twitter" % "jsr166e" % "1.1.0"

//MongoDB
libraryDependencies += "org.mongodb.spark" %% "mongo-spark-connector" % "2.4.1"

//Mysql
libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.17"
