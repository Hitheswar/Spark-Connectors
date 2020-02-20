package com.dama.spark.nosql
import org.apache.spark.SparkConf
import org.apache.spark.sql.{ SparkSession, SaveMode}
object mongoDB extends App {

  lazy val sparkConf = new SparkConf()
    .setAppName("MongoDB Test")
    .set("spark.mongodb.output.uri","mongodb://localhost:27098")
    .set("spark.mongodb.input.uri", "mongodb://localhost:27098")
    .setMaster("local")
    .set("spark.cores.max", "2")

  val sparkSession = SparkSession
    .builder()
    .config(sparkConf)
    .getOrCreate()



}
