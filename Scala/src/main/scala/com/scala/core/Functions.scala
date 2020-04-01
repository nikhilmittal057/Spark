package com.scala.core

object Functions extends App {

  def welcome (message:String) : Unit = {
    println(" welcome to scala functions - " + message)
  }
  welcome("Rajesh")

  def addition(a:Int, b:Int) : Int = {
    val sum = a + b
    sum
  }

  val sum = addition(100,200)
  println("Sum is " +sum)

  def printCityNames(cities:String*): Unit = {
    cities.foreach(println)
  }
     printCityNames("Mangalore","Bangalore", "Gwalior")


  def printIntegers(Integer:Int*): Unit = {
    Integer.foreach(println)
  }
  printIntegers(10, 20, 30, 40)
}
