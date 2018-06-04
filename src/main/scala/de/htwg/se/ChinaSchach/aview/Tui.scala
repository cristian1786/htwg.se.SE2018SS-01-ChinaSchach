package de.htwg.se.ChinaSchach.aview

import de.htwg.se.ChinaSchach.model._
import scala.collection.mutable.ListBuffer


//TODO: implement TUI
class Tui(board: Board) {

  // prints an overview over the fields with the respective pieces
  def outputField(): Unit = {
    for (x <- 0 to 8) {
      for (y <- 0 to 8) {
        if (board.get(y, y).getSide() != " " && board.get(x, y).getPromotable() != " ") {
          println("Field(" + x + ", " + y + ") contains " + board.get(x, y))
        }
      }
    }
    println("")
  }

  //prints a list with pieces for each player
  def outputPlayerFigures(list: ListBuffer[Piece]): Unit = {
    if (list.head.getSide() == "top") println("Player 1: ") else println("Player 2: ")
    list.foreach(println)
    println("")
  }

  //TODO: which Round
}
