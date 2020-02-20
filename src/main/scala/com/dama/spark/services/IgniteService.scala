package com.dama.spark.services

import java.util.Properties

import org.apache.ignite.spark.IgniteDataFrameSettings.{FORMAT_IGNITE, OPTION_CONFIG_FILE, OPTION_TABLE,OPTION_STREAMER_ALLOW_OVERWRITE}
import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}


object IgniteService {
  def readThroughIgniteThickClient(spark: SparkSession, tableName: String, config: String): DataFrame = {
    spark.read.format(FORMAT_IGNITE).option(OPTION_CONFIG_FILE, config).option(OPTION_TABLE, tableName).load().cache()
  }
  def writeThroughIgniteThickClient(spark:SparkSession,finalDF:DataFrame,tableName:String,config:String,saveMode: SaveMode): Unit ={

    if (saveMode == SaveMode.Append) {
      finalDF.write.format(FORMAT_IGNITE).option(OPTION_CONFIG_FILE, config).option(OPTION_TABLE, tableName).option(OPTION_STREAMER_ALLOW_OVERWRITE, true).mode(saveMode).save()
    }
    else if(saveMode == SaveMode.Overwrite){

    }
    else{

    }
  }

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
