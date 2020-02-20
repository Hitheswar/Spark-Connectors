package com.dama.spark.services

import java.util.Properties

import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}


object IgniteService {
  def readThroughIgniteThinClient(spark: SparkSession, connection_url: String,tableName:String): DataFrame={
    val connectionProperties = new Properties()
    connectionProperties.put("url", connection_url)
    spark.read.format("jdbc").option("url", connection_url).option("fetchsize",100).option("dbtable", tableName).load().cache()
  }
  def writeThroughIgniteThinClient(df:DataFrame,tableName:String,connection_url:String)={
    val connectionProperties = new Properties()
    connectionProperties.put("url", connection_url)
    df.write.mode(SaveMode.Append).jdbc(connection_url, tableName,connectionProperties)
  }

}
