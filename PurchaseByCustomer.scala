package com.scala
import org.apache.spark._
object PurchaseByCustomer extends App {

  val sc = new SparkContext("local[*]","Purchase")
  val rdd = sc.textFile("/home/nikhil/Desktop/SparkScala/customer-orders")

}
