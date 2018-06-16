package de.htwg.se.ChinaSchach.controller

import de.htwg.se.ChinaSchach.aview.Tui
import de.htwg.se.ChinaSchach.model._

import scala.collection.mutable.ListBuffer


//TODO: implement Controller
class Controller(name1: String, name2: String) {

  var board = new Board
  val tui = new Tui(board)
  val player1 = new Player(name1)
  val player2 = new Player(name2)
  var listPlayer1: ListBuffer[Piece] = _
  var listPlayer2: ListBuffer[Piece] = _
  var topKingDead = false
  var bottomKingDead = false
  var selected = false
  var winner = ""
  var gameOver = false

  //initializes playing board
  def boardInit() : Unit = {
    board.go()
    tui.outputField()
    playerInit("Peter", "Hannes")

  }

  //initialize player
  def playerInit(name1: String, name2: String) : Unit = {

    listPlayer1 = player1.setPieces(board, "w")
    listPlayer2 = player2.setPieces(board, "b")

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

  def ifEnemy(board: Board, source: (Int, Int), destination: (Int, Int)): Unit = {
    if (board.get(source).getSide() != board.get(destination).getSide() && board.get(source).getSide() != "") {
      if (board.get(source).getSide() == "top") {
        listPlayer1 -= board.get(destination)
        movePiece(board, source, destination)
      }
      listPlayer2 -= board.get(destination)
      movePiece(board, source, destination)
    }
  }

  def movePiece(board: Board, source: (Int, Int), destination: (Int, Int)) : Unit = {
    board.gameBoard(destination._1)(destination._2) = board.get(source)
    board.gameBoard(source._1)(source._2) = EmptyField(" ")
  }

/*  def takePiece(board: Board, source: (Int, Int), destination: (Int, Int)) : Unit = {
    board.gameBoard(destination._1)(destination._2) = board.get(source)
  }*/

  def gameWon() : Unit = {}

  def reset(): Unit = {}

  def exit(): Unit = {}

  def notifyView(): Unit = {}

  def selectPiece(): Unit = {}


}
