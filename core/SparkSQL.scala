package com.scala.core

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object SparkSQL extends App {

  val conf = new SparkConf()
   conf.set("hive.metastore.uris", "thrift://localhost:9083")
  val spark = SparkSession.builder().appName("spark SQL").master("local")
    .config(conf).enableHiveSupport().getOrCreate()

  import spark.implicits._ //use for converting Scala objects (incl. RDDs) into a Dataset , DataFrame.

  val stocksDF = spark.sql("select stock, sum(volume) as aggvolume from stocks group by stock")
  stocksDF.show() // this is also working no need of view on table

  stocksDF.createOrReplaceTempView("volume")
  val top10DF = spark.sql("select * from volume order by aggvolume desc limit 10")
  top10DF.show()
  top10DF.write.saveAsTable("TOP_Volume")
}
