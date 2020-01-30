package com.dama.spark.fileSystem

import com.dama.spark.core.SparkApp
import com.dama.spark.util.Constants._
object hdfsConnection extends App with SparkApp{

  val file_path = "/Data/Sale_record.csv"
  var hdfs_path = hdfs_url+hdfs_host+colon+hdfs_port+file_path
  var df = spark.read.format("com.databricks.spark.csv")
    .option("inferSchema", "false")
    .option("header", true)
    .option("delimiter", ",")
    .option("encoding", "ISO-8859-1")
    .option("nullValue", "")
    .option("escape", "\"")
    .option("quote", "\"")
    .option("multiline", "true")
    .load(hdfs_path)

  df.show()

  df.count()

  val target_path = "/Data/new/Sales_records.csv"
  val target_hdfs =  hdfs_url+hdfs_host+colon+hdfs_port+target_path
  df.repartition(1).write.format("com.databricks.spark.csv")
    .mode("overwrite")
    .option("delimiter", ",")
    .option("header", "true")
    .option("quote", "")
    .save(target_hdfs)

  //.option("treatEmptyValuesAsNulls", "true")
  //.option("ignoreLeadingWhiteSpace", "true")
  //.option("ignoreTrailingWhiteSpace", "true")
}
