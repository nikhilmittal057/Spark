package com.scala.core
import org.apache.spark.sql.functions._
import org.apache.spark.sql.SparkSession

object SocieteGeneral extends App {

  val spark = SparkSession.builder().appName("spark").config("spark.some.config.option", "SocieteGeneral").master("local[*]").getOrCreate()
  import spark.implicits._

  val graphDF = spark.read.format("csv").option("header", "true").option("inferSchema", "true").load("/home/nikhil/Desktop/Datasets/edges.csv").cache()
  val src_from_df =  graphDF.groupBy("from").agg(count("*").alias("connecting_count")).withColumnRenamed("from","vertices").cache()
  val dest_to_df = graphDF.groupBy("to").agg(count("*").alias("connecting_count")).withColumnRenamed("to", "vertices").cache()
  val degrees = src_from_df.union(dest_to_df).groupBy("vertices").agg(sum("connecting_count").alias("degree")).cache()

  val e = graphDF.count()
  val n = degrees.count()
  val AverageDegree =  2*(e/n)

  val finalDF=  degrees.where($"degree" >= AverageDegree).cache()
  val sorted_rdd = finalDF.sort($"degree".desc, $"vertices".asc).rdd
// val sorted_rdd =  finalDF.sort(desc("degree"), asc("vertices")).rdd
  val pairedRDD = sorted_rdd.map(tuple => (tuple.get(0).asInstanceOf[Int], tuple.get(1).asInstanceOf[Long])).collect()
  println("vertex," + "degree")
  for(tuple <- pairedRDD)
  println(tuple._1 +"," +tuple._2)
}
