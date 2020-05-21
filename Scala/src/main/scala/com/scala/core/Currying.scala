package com.scala.core

object Currying extends App {

  def add(a:Int, b:Int, c:Int) : Int ={
    a+b+c
  }

  val  sum = add(10,20,30)
 // println(sum)

  def addition (a:Int)(b:Int)(c:Int) : Int ={
    a+b+c
  }

 val result= addition(10)_
  val result1 = result(20)
  val result2 = result1(30)
  println(result2)
}
