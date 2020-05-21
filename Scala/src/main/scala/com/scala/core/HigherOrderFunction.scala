package com.scala.core

object HigherOrderFunction extends App {

  def sum(a:Int, b:Int) :Int = {
    a + b
  }

  def addAndMultiply(A:(Int, Int) => Int, a:Int, b:Int) : Int ={
      A(a,b)

  }

  val calculate = addAndMultiply(sum,10,20)
  println(calculate)
}
