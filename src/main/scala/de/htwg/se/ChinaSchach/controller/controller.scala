package de.htwg.se.ChinaSchach.controller

import de.htwg.se.ChinaSchach.aview.Tui
import de.htwg.se.ChinaSchach.model.{Board, Player}

//TODO: implement Controller
class Controller(name1: String, name2: String) {

  var board = new Board

  val tui = new Tui(board)

  val player1 = new Player(name1)
  val player2 = new Player(name2)

  var listPlayer1 = _
  var listPlayer2 = _

  //initializes playing board
  def boardInit() : Unit = {
    //TODO: initialze Field
    board.go()
    tui.outputField()
    playerInit("Peter", "Hannes")

  }

  //initialize player
  def playerInit(name1: String, name2: String) : Unit = {

    listPlayer1 = player1.setFigures(board, "top")
    listPlayer2 = player2.setFigures(board, "bottom")

    tui.outputPlayerFigures(listPlayer1)
    tui.outputPlayerFigures(listPlayer2)

  }

  //TODO: check for valid/invalid moves
  def checkMove(): Unit = {
  }
}
