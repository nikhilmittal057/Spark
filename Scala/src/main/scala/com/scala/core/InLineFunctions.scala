package com.scala.core

object InLineFunctions extends App {
//
//  val sum = (a:Int, b:Int) => a + b
//  println(sum(100,200))
//
//  def toUpper(string: String) : String = string.toUpperCase

  val names = List("naga", "siva", "hari")
//  names.map(toUpper).foreach(println)
//
//  names.map(name => name.toUpperCase).foreach(println)

//  names.map(name => {
//    val nameInUpperCase = name.toUpperCase()
//    val nameInCap = name.capitalize
//    nameInCap
//  }).foreach(println)

  val x = names.map(name => {
    val nameInUpperCase = name.toUpperCase()
    val nameInCap = name.capitalize
    (nameInCap,name)
  })
println(x)
}
