package com.scala.core

import org.apache.spark.sql.SparkSession
import scala.math.min


object MinTemperatures extends App {

  val spark = SparkSession.builder().appName("spark").config("spark.some.config.option", "MinTemperatures").master("local[*]").getOrCreate()

  val lines = spark.sparkContext.textFile("/home/nikhil/Desktop/Datasets/1800.csv").cache()
 val parsedLines =  lines.map(line =>
     {
         val fields =    line.split(",")
        (fields(0), fields(2), fields(3).toFloat * 0.1f *(9.0f/5.0f) + 32.0f)
     }
  )

     val minTemps = parsedLines.filter(x => x._2 == "TMIN")
     val stationTemps = minTemps.map(x => (x._1, x._3))
  //  val minTempsByStation = stationTemps.groupBy(x => x._1).mapValues(_.minBy(_._2)).values
   //  val test = stationTemps.groupBy(x => x._1).mapValues(x => x.minBy(y => y._2)).values
     val minTempsByStation = stationTemps.reduceByKey((x,y) => min(x,y)).collect
   for(result <- minTempsByStation) {
      val station = result._1
      val temp = result._2
      val formattedTemp = f"$temp%.2f F"
    println(s"$station : $formattedTemp" )
  }
}
