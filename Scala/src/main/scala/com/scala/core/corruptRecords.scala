package com.scala.core

import org.apache.spark.sql.SparkSession
//We have three ways to handle this type of data-
//To include this data in a separate column
//To ignore all bad records
//Throws an exception when it meets corrupted records


object corruptRecords extends App {

  val spark = SparkSession.builder().appName("spark").config("spark.some.config.option", "word-count").master("local[*]").getOrCreate()
  import spark.implicits._
  def corrupt(): Unit = {

    val data = spark.sparkContext.parallelize("""{"a": 1, "b":2, "c":3}|{"a": 1, "b":2, "c":3}|{"a": 1, "b, "c":10}""".split("|")).toDS().cache()
    val corrupt_df = spark.read.option("mode","PERMISSIVE").option("columnNameOfCorruptRecord","corrupt_record").json(data)
      spark.read.option("MODE","DROPMALFORMED").json(data).show(false)
    corrupt_df.show()


  }
}
