package com.dama.spark.common

import com.dama.spark.core.SparkApp

object local extends App with SparkApp{


  var df = spark.read.format("com.databricks.spark.csv").option("inferSchema", "false").option("header", true)
    .option("delimiter", ",")
    .load("H:\\Extras\\DaTA\\ipldata\\matches.csv")
  df.show()
  df.count()

  df.repartition(1).write.format("com.databricks.spark.csv").mode("overwrite")
    .option("delimiter", ",")
    .option("header", "true")
    .option("quote", "")
    .save("H:\\STudy\\test\\csv\\matches.csv")
}
