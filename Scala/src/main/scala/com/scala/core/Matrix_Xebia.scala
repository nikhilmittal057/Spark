package com.scala.core

import Array.ofDim

object Matrix_Xebia extends App {

  var Matrix_A = ofDim[Int](2,2)
  var Matrix_B = ofDim[Int](2,2)
  var Matrix_C = ofDim[Int](2,2)

  Matrix_A(0)(0) = 2
  Matrix_A(0)(1) = 7
  Matrix_A(1)(0) = 3
  Matrix_A(1)(1) = 4

  Matrix_B(0)(0) = 1
  Matrix_B(0)(1) = 8
  Matrix_B(1)(0) = 3
  Matrix_B(1)(1) = 9

//  var Matrix_a= Array(Array(2,7),
  //  Array(3,4))

  //var Matrix_b = Array(Array(1,8), Array(3,9))
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


    for(i <- 0 to 1) {
      for(j <- 0 to 1) {
        if(Matrix_A(i)(j) <= Matrix_B(i)(j))
          Matrix_C(i)(j) = Matrix_B(i)(j)
          else
          Matrix_C(i)(j) = Matrix_A(i)(j)
        print(" "+Matrix_C(i)(j))
      }//end of j loop
      println()
    }//end of i loop


}
