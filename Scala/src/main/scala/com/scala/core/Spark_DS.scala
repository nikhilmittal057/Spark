package com.scala.core
import org.apache.spark.sql.{SparkSession}



object Spark_DS extends App {

  val spark= SparkSession.builder().master("local[*]").appName("Data Frame Example").getOrCreate()
  import spark.implicits._
  case class Person(name:String, age:Int, place:String)
  val peopleDS= Seq(Person("Naga", 30, "Bangalore"), Person("Nikhil", 27, "Gwalior")).toDS()
  peopleDS.show
  peopleDS.printSchema
}
