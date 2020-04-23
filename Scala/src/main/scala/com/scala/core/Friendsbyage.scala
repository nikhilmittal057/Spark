package com.scala.core
import org.apache.spark._
import org.apache.spark.SparkContext._
object Friendsbyage extends App {

  def parseLine(line: String) = {
    val fields = line.split(",")
    val age = fields(2).toInt
    val numFriends = fields(3).toInt
    (age, numFriends)
  }
  val sc = new SparkContext("local[*]", "RatingCounter")
  val linesRDD = sc.textFile("/home/nikhil/Desktop/Datasets/friends").cache()
  val tuple_rdd = linesRDD.map(parseLine)


  val totalsByAge = tuple_rdd.mapValues(x => (x,1)).reduceByKey((x,y) =>(x._1+y._1,x._2+y._2)).cache()

//  val averagesByAge = totalsByAge.mapValues(x=> x._1/x._2).cache()
 val averagesByAge = totalsByAge.map(x=> (x._1, x._2._1/x._2._2)).cache()
  val results = averagesByAge.collect()
  println("Age,"+ "Average Of Friends")
  for (data <- results.sorted)
    println(data._1+",  "+data._2)
  //results.sorted.foreach(println)
}


