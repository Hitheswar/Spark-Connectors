package com.dama.spark.util
object test
{
  def main(args: Array[String])
  {
    val name = Map("Nidhi" -> "author",
      "Geeta" -> "coder")
    val x = name.get("Nidhi")
    val y = name.get("Rahul")

    println(x)
    println(y)
  }
}