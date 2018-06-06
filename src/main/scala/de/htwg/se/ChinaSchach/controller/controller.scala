package de.htwg.se.ChinaSchach.controller

import de.htwg.se.ChinaSchach.aview.Tui
import de.htwg.se.ChinaSchach.model.{Board, Piece, Player}
import scala.collection.mutable.ListBuffer


//TODO: implement Controller
class Controller(name1: String, name2: String) {

  var board = new Board
  val tui = new Tui(board)
  val player1 = new Player(name1)
  val player2 = new Player(name2)
  var listPlayer1: ListBuffer[Piece] = _
  var listPlayer2: ListBuffer[Piece] = _

  //initializes playing board
  def boardInit() : Unit = {
    board.go()
    tui.outputField()
    playerInit("Peter", "Hannes")

  }

  //initialize player
  def playerInit(name1: String, name2: String) : Unit = {

    listPlayer1 = player1.setPieces(board, "top")
    listPlayer2 = player2.setPieces(board, "bottom")

    tui.outputPlayerFigures(listPlayer1)
    tui.outputPlayerFigures(listPlayer2)

  }

  //TODO: remove beat Piece from playerList and board
  def beatEnemyPieces(): Unit = {
    //if beat piece from player1 => deletePiece(listPlayer1, ...)
    //if beat piece from player2 => deletePiece(listPlayer2, ...)

    //delete piece from board
  }

  //TODO: check for valid/invalid moves
  def checkMove(): Unit = {
  }

  def ifEnemy(player: Player, board: Board, source: (Int, Int), destination: (Int, Int)): Unit = {
    if (board.get(source).getSide() != board.get(destination).getSide() && board.get(source).getSide() != "") {
      if (board.get(source).getSide() == "top") {
        player.deletePiece(listPlayer1, board.get(destination))
      }
      player.deletePiece(listPlayer2, board.get(destination))
    }
  }
}
