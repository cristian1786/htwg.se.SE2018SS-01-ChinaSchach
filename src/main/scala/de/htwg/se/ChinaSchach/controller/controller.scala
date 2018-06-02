package de.htwg.se.ChinaSchach.controller

import de.htwg.se.ChinaSchach.aview.Tui
import de.htwg.se.ChinaSchach.model.{Board, Piece, Player}

import scala.collection.mutable.ListBuffer

//TODO: implement Controller
class Controller(name1: String, name2: String) {

  var board = new Board
  val tui = new Tui(board)

  def boardInit() : Unit = {
    //TODO: initialze Field
    board.go()
    tui.outputField()
    playerInit("Peter", "Hannes")

  }

  //TODO: initialize player
  def playerInit(name1: String, name2: String) : Unit = {
    val player1 = new Player(name1)
    val player2 = new Player(name2)

    var listPlayer1 = ListBuffer.empty[Piece]
    var listPlayer2 = ListBuffer.empty[Piece]

    var counter = 0

    for (x <- 0 to 8) {
      for (y <- 0 to 8) {
        if (board.get(x, y) != null && board.get(y, y).getSide() == "top") {
          listPlayer1.append(board.get(x, y))
        }
        if (board.get(x, y) != null && board.get(y, y).getSide() == "bottom") {
          listPlayer2.append(board.get(x, y))
        }
      }
    }

    tui.outputPlayerFigures(listPlayer1)
    println("")
    tui.outputPlayerFigures(listPlayer2)

  }

  //TODO: check for valid/invalid moves
  def checkMove(): Unit = {
  }
}
