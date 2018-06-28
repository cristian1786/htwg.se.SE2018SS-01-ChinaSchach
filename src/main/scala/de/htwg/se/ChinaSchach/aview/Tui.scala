package de.htwg.se.ChinaSchach.aview

import de.htwg.se.ChinaSchach.controller.Controller
import de.htwg.se.ChinaSchach.model._

import scala.io.StdIn

//TODO: implement TUI
class Tui(controller: Controller, board: Board) {

  // prints an overview over the fields with the respective pieces
  //  def outputField(): Unit = {
  //    for (x <- 0 to 7) {
  //      for (y <- 0 to 7) {
  //        if (board.gameBoard(Point(x, y)).getSide() != " ") {
  //          println("Field(" + x + ", " + y + ") contains " + board.gameBoard(Point(x, y)))
  //        }
  //      }
  //    }
  //    println("")
  //    println("Field (0, 2) contains " + board.gameBoard(Point(0, 2)))
  //    println("")
  //  }
  //
  //  //prints a list with pieces for each player
  //  def outputPlayerFigures(list: ListBuffer[Piece]): Unit = {
  //    if (list.head.getSide() == "b") println("Player 1: ") else println("Player 2: ")
  //    list.foreach(println)
  //    println("")
  //  }

  // initialize TUI
  def go(): Unit = {
    readInput()
  }

  // read user input
  def readInput(): Unit = {
    println("Please input action to perform (src XY) (dest XY) (quit) (restart):")
    val input = StdIn.readLine()

    input match {
      case "quit" =>
        controller.exit()
      case "restart" =>
        controller.reset()
        controller.setRound()
      case "src" =>

      case "dest" =>

    }
  }

}
