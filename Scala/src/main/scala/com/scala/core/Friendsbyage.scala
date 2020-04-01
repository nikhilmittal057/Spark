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
  val lines = sc.textFile("/home/nikhil/Desktop/friends")
  val rdd = lines.map(parseLine)



  val totalsByAge = rdd.mapValues(x =>(x,1)).reduceByKey((x,y) =>(x._1+y._1,x._2+y._2))

  val averagesByAge = totalsByAge.mapValues(x=> x._1/x._2)
  val results = averagesByAge.collect()
  results.sorted.foreach(println)
}
