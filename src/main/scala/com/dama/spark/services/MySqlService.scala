package com.dama.spark.services

import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}
object MySqlService{
  def withConnection(url: String, user: String, password: String ): MySqlService = {
    new MySqlService(url, user, password)
  }
}
class MySqlService(url: String, user: String, password: String) extends Serializable {

  def read(spark:SparkSession, database: String, table: String):DataFrame = {
    spark.read.format("jdbc").option("url",url).option("dbtable",database+"."+table).option("user",user).option("password",password).load()
  }

  def save(spark:SparkSession,df:DataFrame,database:String,table:String,saveMode: SaveMode): Unit ={
    df.write.format("jdbc").option("url",url).option("dbtable",database+"."+table).option("user",user).option("password",password)
      .mode(saveMode).save()
  }
}