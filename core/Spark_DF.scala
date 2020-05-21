package com.scala.core
import org.apache.spark._
import org.apache.spark.sql.types._
import org.apache.spark.sql.{Row, SparkSession}

object Spark_DF extends App {

  val sc = new SparkContext("local[*]","sparkcontext")
  val stockRDD = sc.textFile("file:///home/nikhil/bigdata/jobs/stocks")
  val Header = "market,stock,sdate,open,high,low,close,volume,adj_close"
  //val schema= StructType(Header.split(",").map(fieldName => StructField(fieldName, StringType, true)))
  val schema= StructType(List(StructField("market", StringType, false),
    StructField("stock",StringType, true),
    StructField("sdate", StringType, false),
    StructField("open", DoubleType, true),
    StructField("high", DoubleType, false),
    StructField("low", DoubleType, true),
    StructField("close", DoubleType, true),
    StructField("volume", DoubleType, true),
    StructField("adj_close",DoubleType, true)))
  //val rowRDD = stockRDD.map(_.split("\t")).map(x => Row(x(0), x(1), x(2), x(3), x(4), x(5) , x(6) , x(7) , x(8)))
  val rowRDD = stockRDD.map(_.split("\t")).map(x => Row(x(0), x(1), x(2), x(3).toDouble, x(4).toDouble, x(5).toDouble , x(6).toDouble , x(7).toDouble , x(8).toDouble))

  val spark = SparkSession.builder().appName("spark").config("spark.some.config.option", "some-value").getOrCreate()
  val stockDF = spark.createDataFrame(rowRDD, schema)
  val df = stockDF.withColumn("sdate",stockDF("sdate").cast("date"))
  df.createGlobalTempView("stock_View")
  spark.sql("SELECT * FROM global_temp.stock_View").show()
  df.write.saveAsTable("stocks_Test")
  df.printSchema()
  df.show()


  //OPERATIONS ON DATAFRAMES
  //stockDF.filter(stockDF("stock")=== "CA").filter(stockDF("volume") > 100000).select("stock", "volume").show


}