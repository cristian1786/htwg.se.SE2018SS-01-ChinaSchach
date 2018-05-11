package de.htwg.se.yourgame

import de.htwg.se.yourgame.model.Student

object Hello {
  def main(args: Array[String]): Unit = {
    val student = Student("Cris")
    println("Hello, " + student.name)
    var x = 2
    var y = x + 5
    println("Hello I love big butts: " + y)
    println(student.f(7))
  }
}