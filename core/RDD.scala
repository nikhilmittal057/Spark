package com.scala.core
import org.apache.spark._
//import org.apache.spark.SparkContext._
//import org.apache.log4j._

object RDD extends App{

  val sc = new SparkContext("local", "RatingCounter")
  val lines = sc.textFile("/home/nikhil/Desktop/Ratings")
  val rating = lines.map(x => x.toString().split("\t")(2))
  val results = rating.countByValue()
  val sortedResults = results.toSeq.sortBy(_._1)
  sortedResults.foreach(println)


}
