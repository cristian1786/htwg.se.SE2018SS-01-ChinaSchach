package de.htwg.se.ChinaSchach.controller.controllerComponent.controllerImpl

import de.htwg.se.ChinaSchach.aview._
import de.htwg.se.ChinaSchach.controller.controllerComponent.ControllerInterface
import de.htwg.se.ChinaSchach.controller.{UndoManager, UndoManagerImpl}
import de.htwg.se.ChinaSchach.model.FileIOComponent.FileIOImpl.FileIO
import de.htwg.se.ChinaSchach.model._
import de.htwg.se.ChinaSchach.observer.Observable
import de.htwg.se.ChinaSchach.util.Point

import scala.collection.mutable.{ListBuffer, Map}


class Controller() extends Observable with ControllerInterface {

  var board: Board = new Board
  var gui: Gui = _
  var tui: Tui = _
  var player1: Player = _
  var player2: Player = _
  var listPlayer1: ListBuffer[Piece] = _
  var listPlayer2: ListBuffer[Piece] = _
  var listKillPlayer1: ListBuffer[Piece] = _
  var listKillPlayer2: ListBuffer[Piece] = _
  var topKingDead: Boolean = _
  var bottomKingDead: Boolean = _
  var moveDone: Boolean = _
  var message = ""
  var sourcePiece: Piece = _
  var sourcePoint: Point = _
  var rochadeDoneW: Boolean = _
  var rochadeDoneB: Boolean = _
  var undoManager: UndoManager = _
  var canMove: Boolean = _
  val fileIO = new FileIO


  // initialize controller
  def controllerInit(): Unit = {
    boardInit()
    guiInit()
    //tuiInit()
  }

  def tuiInit(): Unit = {
    tui = new Tui(this)
    tui.go()
  }

  // initialize gui
  def guiInit(): Unit = {
    gui = new Gui(this)
    gui.go()
    gui.frame.visible = true
  }

  //initializes playing board
  def boardInit(): Unit = {
    board.go()
    moveDone = false
    topKingDead = false
    bottomKingDead = false
    rochadeDoneW = false
    rochadeDoneB = false
    canMove = true
    playerInit()
    undoManager = new UndoManagerImpl(board)
  }

  //initialize player
  def playerInit(): Unit = {
    player1 = new Player
    player1.Turn = true
    player2 = new Player

    listPlayer1 = ListBuffer.empty
    listPlayer2 = ListBuffer.empty
    listKillPlayer1 = ListBuffer.empty
    listKillPlayer2 = ListBuffer.empty

    listPlayer1.appendAll(player1.setPieces(board, "w"))
    listPlayer2.appendAll(player2.setPieces(board, "b"))
  }

  // check playerturn for black
  def playerTurnCheck(point: Point): Boolean = {
    if (player1.Turn == true && board.gameBoard(point).getSide() == "w" && !moveDone && canMove) {
      true
    } else if (player2.Turn == true && board.gameBoard(point).getSide() == "b" && !moveDone && canMove) {
      true
    } else {
      false
    }
  }

  def playerTurnCheckDest: Boolean = {
    if (player1.Turn && board.gameBoard(sourcePoint).getSide() == "w" && moveDone && canMove) {
      //      player1.Turn = false
      //      player2.Turn = true
      true
    } else if (player2.Turn && board.gameBoard(sourcePoint).getSide() == "b" && moveDone && canMove) {
      //      player2.Turn = false
      //      player1.Turn = true
      true
    } else {
      false
    }
  }

  def resetPlayerTurn: Unit = {
    if (player1.Turn) {
      player1.Turn = false
      player2.Turn = true
      canMove = true
    } else if (player2.Turn) {
      player1.Turn = true
      player2.Turn = false
      canMove = true
    }
    notifyObservers()
  }

  def getSelectedPoint(point: Point): Unit = {
    board.gameBoard.get(point) match {
      case None =>
        message = "Nothing selected"
      case Some(x) =>
        val justIf = {
          if (sourcePiece.toString == "Pawn(w)" || sourcePiece.toString == "Pawn(b)") {
            sourcePiece.movesAllowedP(board, sourcePoint, point, sourcePiece.getPossibleMoves())
          } else {
            sourcePiece.movesAllowed(board, sourcePoint, point, sourcePiece.getPossibleMoves())
          }
        }
        val ifRochade = {
          if (sourcePiece.getSide() == board.gameBoard(point).getSide() && sourcePiece != board.gameBoard(point)
            && !rochadeDoneW || !rochadeDoneB) {
            sourcePiece.testRochade(board, sourcePoint, point)
          } else {
            false
          }
        }
        if (ifRochade) {
          doRochade(sourcePoint, point)
        } else if (!justIf) {
          moveDone = false
          notifyObservers()
        } else if (justIf) {
          ifEnemy(sourcePoint, point)
        }
    }
  }

  def savePiecePoint(point: Point): Unit = {
    sourcePiece = board.gameBoard(point)
    sourcePoint = point
    moveDone = true
    message = "Please select destination"
  }

  def ifEnemy(source: Point, destination: Point): Unit = {
    if (board.gameBoard(destination).getSide() != " ") {
      if (board.gameBoard(source).getSide() == "w") {
        listPlayer2.-=(board.gameBoard(destination))
        listKillPlayer2.+=(board.gameBoard(destination))
        movePiece(source, destination)
      } else {
        listPlayer1.-=(board.gameBoard(destination))
        listKillPlayer1.+=(board.gameBoard(destination))
        movePiece(source, destination)
      }
    } else {
      movePiece(source, destination)
    }
  }

  def movePiece(source: Point, destination: Point): Unit = {
    gameWon(destination)

    undoManager.undoStack = ((source, board.gameBoard(source)), (destination, board.gameBoard(destination))) :: undoManager.undoStack
    undoManager.redoStack = ((source, EmptyField(" ")), (destination, board.gameBoard(source))) :: undoManager.redoStack

    board.gameBoard += destination -> board.gameBoard(source)
    board.gameBoard += source -> EmptyField(" ")

    board.round += 1

    if (sourcePiece.toString == "Pawn(w)" || sourcePiece.toString == "Pawn(b)") {
      rowOneEight(destination)
    }
    canMove = false
    moveDone = false
    notifyObservers()
  }

  def undo: Unit = {
    undoManager.undoMove
    canMove = true
    notifyObservers()
  }

  def redo: Unit = {
    undoManager.redoMove
    canMove = false
    notifyObservers()
  }

  def rowOneEight(destination: Point): Unit = {
    val rowOne: List[(Int, Int)] = List((0, 0), (1, 0), (2, 0), (3, 0), (4, 0), (5, 0), (6, 0), (7, 0))
    val rowEight: List[(Int, Int)] = List((0, 7), (1, 7), (2, 7), (3, 7), (4, 7), (5, 7), (6, 7), (7, 7))
    if (sourcePiece.toString == "Pawn(w)") {
      pawnReplace(destination, rowEight)
    } else {
      pawnReplace(destination, rowOne)
    }
  }

  def pawnReplace(destination: Point, list: List[(Int, Int)]): Unit = {
    if (list.contains((destination.x, destination.y)) && list.head == (0, 0)) {
      if (listKillPlayer2.isEmpty) {
        board.gameBoard += destination -> Pawn("b")
      } else {
        val piece: Piece = gui.promotePawnDialog(listKillPlayer2, "b")
        board.gameBoard += destination -> piece
        listKillPlayer2.-=(piece)
      }
    } else if (list.contains((destination.x, destination.y)) && list.head == (0, 7)) {
      if (listKillPlayer1.isEmpty) {
        board.gameBoard += destination -> Pawn("w")
      } else {
        val piece: Piece = gui.promotePawnDialog(listKillPlayer1, "w")
        board.gameBoard += destination -> piece
        listKillPlayer1.-=(piece)
      }
    }
  }

  def gameWon(destination: Point): Unit = {
    if (board.gameBoard(destination).toString == "King(w)") {
      bottomKingDead = true
    } else if (board.gameBoard(destination).toString == "King(b)") {
      topKingDead = true
    }
  }

  def reset(): Unit = {
    board.gameBoard = Map.empty[Point, Piece]
    boardInit()
    setRound()
    if (gui != null && tui != null) {
      tui.go()
      gui.go()
      gui.frame.visible = true

    } else if (tui != null) {
      tui.go()
    } else if (gui != null) {
      gui.go()
      gui.frame.visible = true
    }
    notifyObservers()
  }

  def exit(): Unit = {
    sys.exit(0)
  }

  def setRound(): Unit = {
    board.round = 0
  }

  def doRochade(sourcePoint: Point, point: Point): Unit = {
    if (sourcePoint == Point(0, 0) || sourcePoint == Point(0, 7) || point == Point(0, 0)
      || point == Point(0, 7)) {
      bigRochade(sourcePoint, point)
    } else if (sourcePoint == Point(7, 0) || sourcePoint == Point(7, 7) || point == Point(7, 0)
      || point == Point(7, 7)) {
      smallRochade(sourcePoint, point)
    }
  }

  def bigRochade(source: Point, point: Point): Unit = {
    if (sourcePiece.toString.contains("King")) {
      bigRochadeMove(point, source)
    } else {
      bigRochadeMove(source, point)
    }
  }

  def smallRochade(source: Point, point: Point): Unit = {
    if (sourcePiece.toString.contains("King")) {
      smallRochadeMove(point, source)
    } else {
      smallRochadeMove(source, point)
    }
  }

  def bigRochadeMove(source: Point, point: Point): Unit = {
    if (board.gameBoard(source).getSide() == "w" && !rochadeDoneW) {
      rochadeDoneW = true
    } else if (board.gameBoard(source).getSide() == "b" && !rochadeDoneB) {
      rochadeDoneB = true
    }
    board.gameBoard += Point(source.x + 3, source.y) -> board.gameBoard(source)
    board.gameBoard += Point(point.x - 2, point.y) -> board.gameBoard(point)
    board.gameBoard += source -> EmptyField(" ")
    board.gameBoard += point -> EmptyField(" ")
    moveDone = false
    canMove = false
    board.round += 1
    notifyObservers()
  }

  def smallRochadeMove(source: Point, point: Point): Unit = {
    if (board.gameBoard(source).getSide() == "w" && !rochadeDoneW) {
      rochadeDoneW = true
    } else if (board.gameBoard(source).getSide() == "b" && !rochadeDoneB) {
      rochadeDoneB = true
    }
    board.gameBoard += Point(source.x - 2, source.y) -> board.gameBoard(source)
    board.gameBoard += Point(point.x + 2, point.y) -> board.gameBoard(point)
    board.gameBoard += source -> EmptyField(" ")
    board.gameBoard += point -> EmptyField(" ")
    moveDone = false
    canMove = false
    board.round += 1
    notifyObservers()
  }

  def save(path: String): Unit = {
    fileIO.save(this, path)
  }

  def load(path: String): Unit = {
    fileIO.load(this, path)
    notifyObservers()
  }
}
