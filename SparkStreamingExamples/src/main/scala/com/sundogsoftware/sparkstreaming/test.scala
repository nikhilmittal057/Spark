package com.sundogsoftware.sparkstreaming

object test extends App {
  val shipMap = Map("Kirk" -> "Enterprise", "Picard" -> "Enterprise-D", "Sisko" -> "Deep Space Nine", "Janeway" -> "Voyager")
  println(shipMap("Janeway"))
}
