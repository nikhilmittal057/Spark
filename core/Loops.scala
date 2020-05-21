package com.scala.core

import scala.util.control.Breaks

object Loops extends App {
  /*
      While Loop
   */
        var n=0
//  while(n <= 10) {
//    println(n)
//    n+=1
//  }

  /*
      For Loop
   */
  val numbers = Array(1,2,3,4,5,6)
  numbers.foreach(println)
//  for (number <- numbers){
//       println(number)
          //  OR
//  numbers.foreach(println)
//
//    for (i <- 0 until 10){
//      println(i)
//    }

  val squares = numbers.map(num => num * num)
  val sqrs = for(number <- numbers) yield number * number
  // val evenSqrs = for (number <- numbers if number %2 == 0) yield  number * number
  val evenSqrs = numbers.filter(_ % 2 ==0).map(num => num * num)
  //val oddSqrs = for (number <- numbers if number % 2 !=0) yield number * number
  val oddSqrs = numbers.filter(num => num % 2 !=0).map(num => num * num)
 // val oddSqrs = numbers.filter(_ % 2 !=0).map(num => num * num)

  println("_________________________")
  println(squares.toList)
  println(sqrs.toList)
  println(evenSqrs.toList)
  println(oddSqrs.toList)


  val persons = Array(Array("Naga" , "Mangalore"), Array("Hari", "Bangalore"))
  for (person <- persons) {
    for (item <- person) {
      print(item)
      print("\t")
    }
    println()
  }
    for (i <- 1 to 10) {
      println(i)
      if (i % 5 ==0)
        Breaks.break()
    }

}
