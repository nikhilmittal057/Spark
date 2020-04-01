package com.scala.core
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{DoubleType, FloatType, StringType, StructField, StructType}
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.max
import org.apache.spark.sql.functions._

object wordCount extends App {

  val spark = SparkSession.builder().appName("spark").config("spark.some.config.option", "word-count").master("local[*]").getOrCreate()
  import spark.implicits._
  val newschema= StructType(List(
    StructField("name", StringType, true),
    StructField("mfr",StringType, true),
    StructField("type", StringType, true),
    StructField("calories",DoubleType, true),
    StructField("protein", DoubleType, true),
    StructField("fat", DoubleType, true),
    StructField("sodium", DoubleType, true),
    StructField("fiber", FloatType, true),
    StructField("carbo", FloatType, true),
    StructField("sugars", DoubleType, true),
    StructField("potass", DoubleType, true),
    StructField("vitamin", DoubleType, true),
    StructField("shelf", DoubleType, true),
    StructField("weight", FloatType, true),
    StructField("cups", FloatType, true),
    StructField("rating", FloatType, true)))

  //SNIPPET

 /* val cerealDF = spark.read.format("csv").schema(newschema).option("delimiter",";").option("header","true").load("/home/nikhil/Desktop/Datasets/cereal.csv")
  cerealDF.show()
  //val transformDF = cerealDF.select("name", "mfr", "calories", "fiber")
  val transformDF = cerealDF.select("name")
  val  junk = transformDF.first()
  val finalDF = transformDF.filter(x => x != junk).show(10,truncate = false)*/
 // val counted=finalDF.groupBy("name").count
  //counted.show() */

  //WORD COUNT USING DATAFRAME

  val cerealDF = spark.read.format("csv").load("/home/nikhil/Desktop/Datasets/cereal.csv").cache()
  val wordsDF = cerealDF.select(split(cerealDF("_c0"),";").alias("words")).cache()

  // Spark SQL "explode" function is used to split an array or DataFrame columns to rows
  val wordDF = wordsDF.select(explode(wordsDF("words")).alias("word")).cache()
  val wordCountDF = wordDF.groupBy("word").count.cache()
  //wordCountDF.where($"word" === "K").show()  // to print a particular string with its count.
  //wordCountDF.where($"count" >= 10).show() //it prints all the field of the words having count >= 10
  //wordCountDF.where($"count" >= 10).select(wordCountDF("word")).show(truncate = false) //it will print the words having count more than or equal to 10

  //below line need correction
//  val value = wordCountDF.withColumn("MaxCount", max(col("count")).over(Window.orderBy("word"))).filter(col("count") === col("Maxcount"))

  // to get the word having max count value.
   val maxValue = wordCountDF.agg(max("count")).map(_(0).asInstanceOf[Long]).collect().toList(0) // it will give the max count value and convert it to Int
   wordCountDF.where($"count" === maxValue).show()

  //Using Window Functions (Finding Rank) rank and dense_rank
  //val denseRank = wordCountDF.withColumn("Rank", dense_rank().over(Window.orderBy($"count".desc))) // 100-1, 99-2, 99-2, 98-3
  val ranked = wordCountDF.withColumn("Rank", rank().over(Window.orderBy($"count".desc)))  // 100-1, 99-2, 99-2, 98-4



  //DIFFERENT OPERATION THAT CAN BE PERFORM ON DATAFRAMES

  //wordCountDF.show(truncate = false)
  //wordCountDF.orderBy(desc("count")).show(truncate=false)
  // wordCountDF.filter($"word".contains("name")).show(truncate = false) // it shows all the strings contains "name" word.
  //wordCountDF.agg(count(when($"word" === "1", 1)).as("cnt_test")).show // it checks whether given string exists or not
  // transformDF.withColumn("Index",).filter('Index > 2).drop("Index") -- to skip multiple rows
  // val collection = finalDF.select("name").rdd.map(r => r(0)).collect() -- to convert a DF column into List

  //WORDCOUNT PROGRAM USING RDD

   val cerealRDD = spark.sparkContext.textFile("/home/nikhil/Desktop/Datasets/cereal.txt")
  val counts = cerealRDD.flatMap(line => line.split(";")).map(word => (word,1)).reduceByKey(_+_)
  val finalRDD = counts.collect()
  println(finalRDD.toList) // it prints the hashcode
 // counts.coalesce(1).saveAsTextFile("/home/nikhil/Desktop/output")


}


