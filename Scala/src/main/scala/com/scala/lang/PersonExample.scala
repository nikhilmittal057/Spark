package com.scala.lang

object PersonExample {

  def main(args:Array[String]) : Unit = {
    val person = new Person(name = "Gufran", age = 40)
    println("Person Name is :" +person.getName())
    println("Person age is :" +person.getAge())
  }

}
