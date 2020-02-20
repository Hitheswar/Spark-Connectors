package com.dama.spark.nosql

import org.apache.spark.SparkConf
import org.apache.spark.sql._
import com.dama.spark.util.Constants._
import org.apache.log4j.{Level, Logger}

import com.datastax.spark.connector._
import com.datastax.spark.connector.cql.{CassandraConnector, _}
import com.datastax.spark.connector.rdd.CassandraTableScanRDD
import org.apache.spark.SparkContext
import org.apache.spark.sql.cassandra._
import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}

object Cassandra extends App{

  println("hello world")

  lazy val sparkConf = new SparkConf()
    .set("spark.cassandra.connection.host", cassandra_host)
    .set("spark.cassandra.auth.username", cassandra_user)
    .set("spark.cassandra.auth.password", cassandra_pass)
    .setAppName("Cassandra Test")
    .setMaster("local")
    .set("spark.cores.max", "2")
  val sparkSession = SparkSession
    .builder()
    .config(sparkConf)
    .getOrCreate()

  val rootLogger = Logger.getRootLogger()
  rootLogger.setLevel(Level.ERROR)
  rootLogger.setLevel(Level.INFO)
  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)


  var cas_df = sparkSession.read.format("org.apache.spark.sql.cassandra")
    .options(Map("table" -> "customers", "keyspace" -> "cloudcdc")).load()

  cas_df.show()
  println(cas_df.count())

  cas_df.write.cassandraFormat("customers_new", "cloudcdc")
    .mode(SaveMode.Overwrite).option("confirm.truncate", "true").save()

}


