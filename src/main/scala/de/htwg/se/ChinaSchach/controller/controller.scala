package de.htwg.se.ChinaSchach.controller

import de.htwg.se.ChinaSchach.aview.Tui
import de.htwg.se.ChinaSchach.model.{Board, Player}

//TODO: implement Controller
class Controller(name1: String, name2: String) {

  var board = new Board

  def boardInit() : Unit = {
    //TODO: initialze Field
    board.go()
    val tui = new Tui(board)
    tui.outputField()

  }

  //TODO: initialize player
  def playerInit(name1: String, name2: String) : Unit = {
    val player1 = new Player(name1)
    val player2 = new Player(name2)

    



  }

  //TODO: check for valid/invalid moves
  def checkMove(): Unit = {
  }
}
