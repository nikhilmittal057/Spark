package com.scala.core




object Monads extends App {

/*  def division(divisor:Int) : List[Int] ={
    val numbers = List(10,20,30,40)
    //val div = numbers.map(_ / divisor)
    //  div
    numbers.map(_ / divisor)
  }

  println(division(5)) */


  val numList1 = List (1,2)
  val numList2 = List (3,4)
  // println (numList1.map(x => numList2.map(y => x + y))) //List(List(4, 5), List(5, 6))
  println (numList1.flatMap(x => numList2.map(y => x + y))) //List(4, 5, 5, 6)

}
