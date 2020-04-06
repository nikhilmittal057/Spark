package com.scala.core
import com.scala.core.wordCount
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._


object useCase1 extends App {

  val spark = SparkSession.builder().appName("spark").config("spark.some.config.option", "word-count").master("local[*]").getOrCreate()

  import spark.implicits._

  val productDF = spark.read.format("csv").option("header", "true").option("inferSchema", "true").
    load("/home/nikhil/Desktop/Datasets/Product_UseCase1.csv").cache()

  val saleDF = spark.read.format("csv").option("header", "true").option("inferSchema", "true").
    load("/home/nikhil/Desktop/Datasets/Sale_UseCase1.csv").cache()

  //val ProductDF =  originalProductDF.withColumn("id" , originalProductDF.col("id").cast(IntegerType)).withColumn("unit_price", originalProductDF.col("unit_price").cast(DoubleType))
  //val productDF = ProductsDF.selectExpr("cast(id as int) id", "cast(unit_price as double) unit_price")

  val yearWise_sale_df = saleDF.withColumn("year", year(saleDF.col("created_at"))).cache()

  val group_sale_df = yearWise_sale_df.groupBy("product_id", "year").
    agg(sum("units").alias("unit_sale_peryear")).orderBy("year").cache() // OR .orderBy(desc("colname"))

  val total_unit_df = group_sale_df.join(productDF, group_sale_df.col("product_id") === productDF.col("id")).cache()
  val total_sale_df = total_unit_df.selectExpr("product_id as productid", "name as product_Name", "year", "unit_sale_peryear * unit_price as total").orderBy("year")

  // WindowSpec which defines which records are in the same partition.https://jaceklaskowski.gitbooks.io/mastering-spark-sql/spark-sql-WindowSpec.html
  val windowspec = Window.partitionBy(total_sale_df.col("year")).orderBy(total_sale_df.col("total").desc)
  val ranked_df = total_sale_df.withColumn("Rank", rank().over(windowspec)).cache()

  val filtered_rank_df = ranked_df.where(ranked_df.col("Rank") === 1).cache()
  val final_df = filtered_rank_df.groupBy("year", "total").agg(concat_ws(",", sort_array(collect_list("product_Name"))).alias("top")).cache()
  val full_final_df = final_df.selectExpr("year", "top", "total").cache()


  /* val quoteStr = udf((topStr: String ) =>
    if(topStr.split(",").length>1)
    {
      "\""+topStr+"\""
    }
  )

  val output_df = full_final_df.withColumn("top", quoteStr($"top")) */


  //val upper: String => String = _.toUpperCase
  //val upperUDF = udf((A: String )
  //full_final_df.withColumn("upper", upperUDF(col("top")))


  /*val output_rdd = full_final_df.rdd.map(_.mkString(",")).cache()
  output_rdd.collect().foreach(x => println(x))
  val splitrdd = output_rdd.map(record => record.split(",")).persist()
  val nikhil = splitrdd.map(arr => {
    val a = arr(0).asInstanceOf[Int]
    val b = arr(1)
    val c = arr(2).asInstanceOf[Int]
    splitrdd.map(tuple => (a,b,c))
    for(i <- 0 to 8 ) {
      if(b.split(",").length>1)
     println(a, "\""+b+"\"", c) */

  val rdd = full_final_df.rdd.cache()
  val List = rdd.map(tuple => (tuple.get(0).asInstanceOf[Int], tuple.get(1).asInstanceOf[String], tuple.get(2).asInstanceOf[Long])).collect()
  println("year," + "top," + "total")

  for (tuple <- List)
  {
   var  top = tuple._2
    if (tuple._2.toString.split(",").length>1)
      top =  "\"" + tuple._2 + "\""
     println(tuple._1 + "," + top + "," + tuple._3)
  }


  //})

/*.map(x => {
    x.get(0)
    x.getAs(1)
    x.get(2)
  })
  println("year," + "top," + "total")

 */
}
