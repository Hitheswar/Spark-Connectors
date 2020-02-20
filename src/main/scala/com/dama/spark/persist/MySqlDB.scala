package com.dama.spark.persist

import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}
object MySqlDB{
  def withConnection(url: String, user: String, password: String ): MySqlDB = {
    new MySqlDB(url, user, password)
  }
}
class MySqlDB(url: String, user: String, password: String) extends Serializable {

  def read(spark:SparkSession, database: String, table: String):DataFrame = {
    spark.read.format("jdbc").option("url",url).option("dbtable",database+"."+table).option("user",user).option("password",password).load()
  }

  def save(spark:SparkSession,df:DataFrame,database:String,table:String,saveMode: SaveMode): Unit ={
    df.write.format("jdbc").option("url",url).option("dbtable",database+"."+table).option("user",user).option("password",password)
      .mode(saveMode).save()
  }
}