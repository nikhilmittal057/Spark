package com.scala.core

object NestedFunctions extends App {

  val empBonus = getBonus("1234")
  println(empBonus)

    def getBonus(empId:String): Double = {
      val empDetails = ("Nikhil", "SSE", 50000.50)

      def calculateBonus(role: String, salary: Double): Double = {
        role match {
          case "SE" =>
            val bonus = if (salary > 50000) 5000 else salary * .25
            bonus
          case "SSE" =>
            val bonus = if (salary > 70000) 5000 else salary * .15
            bonus
          case _ =>
            val bonus = if (salary > 10000) 5000 else salary * .20
            bonus
        } // end of Pattern matching
      } // end of calculateBonus
      val Bonus = calculateBonus(empDetails._2, empDetails._3)
      Bonus
    } //end of getBonus
}//end of NestedFunctions
