package com.dama.spark.nosqlDB

import com.dama.spark.core.SparkApp
import org.apache.spark.sql.SaveMode
import java.util.Properties

import com.dama.spark.services.IgniteService
import com.dama.spark.util.Constants.{ignite_pass, ignite_url, ignite_usr}

object Ignite extends App with SparkApp {

  val connectionProperties = new Properties()
  val url = ignite_url+ ";user=" + ignite_usr + ";password=" + ignite_pass
  connectionProperties.put("url", url)

  var df = IgniteService.readTh

  var df = spark.read.format("jdbc").option("url", url).option("fetchsize",100).option("dbtable", "SITE").load()
  println(df.count())
  df = df.limit(50)
   df.write.mode(SaveMode.Append).jdbc(url, "SITES",connectionProperties)


}
