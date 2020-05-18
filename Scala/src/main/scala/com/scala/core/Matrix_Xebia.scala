package com.scala.core

import Array.ofDim

object Matrix_Xebia extends App {

    var Matrix_A= Array(Array(1,2,3), Array(4,5,6), Array(7,8,9))
    var Matrix_B= Array(Array(4,5,6), Array(7,8,9), Array(1,2,3))
    var Matrix_C = ofDim[Int](3,3)

 /* Matrix_A(0)(0) = 1
  Matrix_A(0)(1) = 2
  Matrix_A(0)(2) = 3
  Matrix_A(1)(0) = 4
  Matrix_A(1)(1) = 5

  Matrix_B(0)(0) = 1
  Matrix_B(0)(1) = 8
  Matrix_B(1)(0) = 3
  Matrix_B(1)(1) = 9*/


  /* for(i<-0 to 1; j<-0 until 2)
   {
     print(i, j)
     //Accessing the elements
     println("=" + Matrix_A(i)(j))
     }*/

  /*for(i<-0 to 1)
  {
    for(j<- 0 to 1)
    {
      // Accessing the values
      print(" "+Matrix_a(i)(j))

    }
    println()
  }*/



    for(i <- 0 to 2) {
      for(j <- 0 to 2) {
        if(Matrix_A(i)(j) <= Matrix_B(i)(j))
          Matrix_C(i)(j) = Matrix_B(i)(j)
          else
          Matrix_C(i)(j) = Matrix_A(i)(j)
        print(" "+Matrix_C(i)(j))
      }
      println()
    }


}
