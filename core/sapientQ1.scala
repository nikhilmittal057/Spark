package com.scala.core
import org.apache.spark.sql.catalyst.plans.logical.Distinct

import scala.io.Source

object sapientQ1 {

  def main(args: Array[String]): Unit = {

    // val fname = "/home/nikhil/Desktop/Datasets/sapientQ1.csv"
    // val fSource = Source.fromFile(fname)

    /*val list = Source.fromFile("/home/nikhil/Desktop/Datasets/sapientQ1.csv").getLines().drop(1)
    .filter(line => !line.isEmpty)
      .map(line =>{
      val value = line.split(",")
        (value(0), value(1))
    })
      .toList*/

   //val unique = list.toSet
    //unique.foreach(println)
 //   val a = list.distinct
 // val a = Set(list: _*)*/
  Source.fromFile("/home/nikhil/Desktop/Datasets/sapientQ1.csv").getLines()
      .foldLeft(Set.empty[String])
      {
        case (seen, str) =>
        val substr = str.take(str.lastIndexOf(","))
        if (!seen(substr))
          println(str)  //send to StdOut
        seen + substr
      }

  }

}


