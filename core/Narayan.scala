package com.scala.core
import org.apache.spark.sql.{Row, SparkSession}

object Narayan extends App {

  //UseCase 1 USING RDD

  def useCase1():Unit = {
    val spark = SparkSession.builder().appName("spark").config("spark.some.config.option", "word-count").master("local[*]").getOrCreate()
    val Numrdd = spark.sparkContext.parallelize(2000 to 3200).filter(x => x % 7 == 0 & x % 5 != 0).collect().foreach(x => print((x + ",")))
    /* val df = spark.range(1).toDF().cache()
       val originaldf = df.withColumn("range",  lit(2000 to 3200 toArray)).cache()*/

    //USING DATAFRAME
    import spark.implicits._
    val Numdf = spark.sparkContext.parallelize(2000 to 3200).toDF("Numbers").cache()
    val final_df = Numdf.where($"Numbers" % 7 === 0 && $"Numbers" % 5 != 0).show(truncate = false)
  }


  //UseCase 2

  }

