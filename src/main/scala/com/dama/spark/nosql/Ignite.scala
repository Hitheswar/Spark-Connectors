package com.dama.spark.nosql

import com.dama.spark.core.SparkApp
import org.apache.spark.sql.{DataFrame, SaveMode}
import java.util.Properties

import com.dama.spark.services.IgniteService
import com.dama.spark.util.Constants.{config, ignite_pass, ignite_url, ignite_usr}

object Ignite extends App with SparkApp {

  //ThinClient
  /*val connection_url = ignite_url+ ";user=" + ignite_usr + ";password=" + ignite_pass
  var df = IgniteService.readThroughIgniteThinClient(spark,connection_url,"SITE")
  df.show(10)
  val count = df.count()
  println("count  "+count)
  IgniteService.writeThroughIgniteThinClient(df,"SITES",connection_url)*/

  //ThickClient
  var dataFrame:DataFrame = IgniteService.readThroughIgniteThickClient(spark,"SITE",config)
  dataFrame.show()
  dataFrame.createOrReplaceTempView("site")
  dataFrame.printSchema()
  println(dataFrame.count())
  spark.sql("select * from site order by asc").show(5)

  IgniteService.writeThroughIgniteThickClient(spark,dataFrame,"sites",config,SaveMode.Append)

}
