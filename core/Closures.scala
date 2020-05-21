package com.scala.core

object Closures extends App {
  val factor = 10

  def multiply(a:Int):Int = {
    a*factor //here value of 'a' depends on 'factor' variable which is defined outside of multiply function that's why
             // this 'multiply' function known as Closure Function.

  }

  println(multiply(100))
}
