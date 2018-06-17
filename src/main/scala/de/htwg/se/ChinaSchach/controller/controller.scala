package de.htwg.se.ChinaSchach.controller

import de.htwg.se.ChinaSchach.aview.Tui
import de.htwg.se.ChinaSchach.model._
import de.htwg.se.ChinaSchach.util.Point

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
  var selFigure = ""
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

  def getSelectedPoint(point: Point): Unit = {
    val testPoint = board.gameBoard.get(point)
    testPoint match {
      case None =>
        selFigure = "Empty Field"
      case Some(f) =>
        //movePiece()
    }
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

  def ifEnemy(board: Board, source: Point, destination: Point): Unit = {
    if (board.getPiece(source).getSide() != board.getPiece(destination).getSide() && board.getPiece(source).getSide() != "") {
      if (board.getPiece(source).getSide() == "w") {
        listPlayer1 -= board.getPiece(destination)
        movePiece(board, source, destination)
      }
      listPlayer2 -= board.getPiece(destination)
      movePiece(board, source, destination)
    }
  }

  def movePiece(board: Board, source: Point, destination: Point) : Unit = {
    board.gameBoard(destination) = board.getPiece(source)
    board.gameBoard(source) = EmptyField(" ")
  }

/*  def takePiece(board: Board, source: Point, destination: Point) : Unit = {
    board.gameBoard(destination._1)(destination._2) = board.getPiece(source)
  }*/

  def gameWon() : Unit = {}

  def reset(): Unit = {}

  def exit(): Unit = {}

  def notifyView(): Unit = {}

  def selectPiece(): Unit = {}


}
