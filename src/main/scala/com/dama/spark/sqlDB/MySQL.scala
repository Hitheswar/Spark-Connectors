package com.dama.spark.sqlDB

import com.dama.spark.core.SparkApp
import com.dama.spark.persist.MySqlDB
import org.apache.spark.sql.SaveMode
import com.dama.spark.util.Constants.{mysql_url,mysql_usr,mysql_pass}

object MySQL extends App with SparkApp{

  val mysqlObj = MySqlDB.withConnection(mysql_url, mysql_usr,mysql_pass)

  val mysql_df = mysqlObj.read(spark,"mysql", "sli_attr").limit(1000)
  mysql_df.show(5)
  println(" mysql dataframe count "+mysql_df.count())

  mysqlObj.save(spark,mysql_df,"mysql","sli_attrs",SaveMode.Overwrite)


}
