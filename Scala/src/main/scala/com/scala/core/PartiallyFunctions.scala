package com.scala.core

import java.util.Date

object PartiallyFunctions extends App {

  def logDate(appName:String, date:Date, message:String): Unit ={

    println(date +"  " +appName+ " "+message)
  }
  val date = new Date()
  val appName = "Spark"
  val message = "App Logged in"

 /* val loggerInfo = logDate(_:String, _:Date, _:String)
    loggerInfo(appName, date, message) */
    val loggerInfo = logDate(appName, _:Date, _:String)
  loggerInfo(date, message)
}
