package de.htwg.se.ChinaSchach.model

import scala.collection.mutable.ListBuffer
import de.htwg.se.ChinaSchach.util.Point

trait Piece {
  def side: String
  def possibleMoves: List[ListBuffer[(Int, Int)]] = List.empty
//  def setSide(side: String) : Unit = this.side = side
//
//  def setPromotable(promotable: String) : Unit = this.promotable = promotable

  def getSide() : String = side

  def getPossibleMoves(): List[ListBuffer[(Int, Int)]] = possibleMoves

  def checkValidPoss(poss: Point): Boolean = {
    var isValid: Boolean = false
    if (poss.x >= 0 && poss.x <= 8 && poss.y >= 0 && poss.y <= 8) {
      isValid = true
    }
    isValid
  }

  def movesAllowed(board: Board, source: Point, destination: Point, possibleMoves: List[ListBuffer[(Int, Int)]]): Boolean = {
    var trueOrFalse = false
    if (checkValidPoss(destination) && board.gameBoard(source).getSide() != board.gameBoard(destination).getSide()) {
      for (x <- possibleMoves) {
        for (y <- x) {
          if (board.gameBoard(source).getSide() == "w" && Point(source.x + y._1, source.y + y._2) == destination
          || board.gameBoard(source).getSide() == "b" && Point(source.x - y._1, source.y - y._2) == destination) {
            val tmpTuple: (Int, Int) = (y._1, y._2)
            trueOrFalse = caseQRB(board, source, tmpTuple, x)
          }
        }
      }
    }
    trueOrFalse
  }

  def caseQRB(board: Board, source: Point, move: (Int, Int), possibleMoves: ListBuffer[(Int, Int)]): Boolean = {
    var bool = true
    if (board.gameBoard(source).toString == "Queen(w)" || board.gameBoard(source).toString == "Rook(w)" || board.gameBoard(source).toString == "Bishop(w)"
      || board.gameBoard(source).toString == "Queen(b)" || board.gameBoard(source).toString == "Rook(b)" || board.gameBoard(source).toString == "Bishop(b)") {
      val listToCheck: ListBuffer[(Int, Int)] = {
        val idx = possibleMoves.indexOf(move)
        possibleMoves.take(idx)
      }
      println("Here List" + listToCheck)
      for(z <- listToCheck) {
        if (board.gameBoard(source).getSide() == "w" && board.gameBoard(Point(source.x + z._1, source.y + z._2)).toString != "EmptyField( )"
        || board.gameBoard(source).getSide() == "b" && board.gameBoard(Point(source.x - z._1, source.y - z._2)).toString != "EmptyField( )") {
          bool = false
        }
      }
    }
    bool
  }

  def movesAllowedP(board: Board, source: Point, destination: Point, possibleMoves: List[ListBuffer[(Int, Int)]]): Boolean = {
    var trueOrFalse = false
    if (checkValidPoss(destination) && board.gameBoard(source).getSide() != board.gameBoard(destination).getSide()) {
      if(board.gameBoard(destination).getSide() != " ") {
        for (x <- possibleMoves(2)) {
          if (board.gameBoard(source).getSide() == "w" && Point(source.x + x._1, source.y + x._2) == destination) {
            trueOrFalse = true
          }
          else if (board.gameBoard(source).getSide() == "b" && Point(source.x - x._1, source.y - x._2) == destination) {
            trueOrFalse = true
          }
        }
      }
      else if(board.gameBoard(destination).getSide() == " ") {
        trueOrFalse = elsePawn(board, source, destination)
      }
    }
    trueOrFalse
  }

  def elsePawn(board: Board, source: Point, destination: Point): Boolean = {
    var bool = false
    val rowTwo = List((0, 1), (1, 1), (2, 1), (3, 1), (4, 1), (5, 1), (6, 1), (7, 1))
    val rowSix = List((0, 6), (1, 6), (2, 6), (3, 6), (4, 6), (5, 6), (6, 6), (7, 6))
    if(board.gameBoard(source).getSide() == "w" && rowTwo.contains((source.x, source.y))) {
      bool = repeatedIfDb(board, source, destination, "w" )
    }
    else if(board.gameBoard(source).getSide() == "b" && rowSix.contains((source.x, source.y))) {
      bool = repeatedIfDb(board, source, destination, "b" )
    }
    else if(board.gameBoard(source).getSide() == "w" && !rowTwo.contains((source.x, source.y))) {
      bool = repeatedIfSg(board, source, destination, "w")
    }
    else if(board.gameBoard(source).getSide() == "b" && !rowSix.contains((source.x, source.y))) {
      bool = repeatedIfSg(board, source, destination, "b")
    }
    bool
  }

  def repeatedIfDb(board: Board, source: Point, destination: Point, side: String): Boolean = {
    var bool = false
    if(side == "w") {
      if (Point(source.x, source.y + 1) == destination || Point(source.x, source.y + 2 ) == destination && board.gameBoard(Point(source.x, source.y + 1)).toString == "EmptyField( )") {
        bool = true
      }
    }
    else if(side == "b") {
      if (Point(source.x, source.y - 1) == destination || Point(source.x, source.y - 2) == destination && board.gameBoard(Point(source.x, source.y - 1)).toString == "EmptyField( )") {
        bool = true
      }
    }
    bool
  }

  def repeatedIfSg(board: Board, source: Point, destination: Point, side: String): Boolean = {
    var bool = false
    if(side == "w") {
      if (Point(source.x, source.y + 1) == destination) {
        bool = true
      }
      else {
        bool = false
      }
    }
    else if(side == "b") {
      if (Point(source.x, source.y - 1) == destination) {
        bool = true
      }
      else {
        bool = false
      }
    }
    bool
  }

  def testRochade(board: Board, piecePoint: Point, point: Point): Boolean = {
    var bool = false
    if(board.gameBoard(piecePoint).toString.contains("King") && board.gameBoard(point).toString.contains("Rook")
      && (piecePoint == Point(4, 0) || piecePoint == Point(4, 7))) {
      bool = rochadeIf(board, point)
      println("ROCHADE OK " + bool)
    }
    else if(board.gameBoard(piecePoint).toString.contains("Rook") && board.gameBoard(point).toString.contains("King")
      && (point == Point(4, 0) || point == Point(4, 7))) {
      bool = rochadeIf(board, piecePoint)
      println("ROCHADE OK " + bool)
    }
    bool
  }

  def rochadeIf(board: Board, toTest: Point): Boolean = {
    var bool = false
    val ifList = List(Point(0, 0), Point(7, 0), Point(0, 7), Point(7, 7))
    if(toTest == ifList(0) && board.gameBoard(Point(1, 0)).toString.contains("EmptyField") &&
      board.gameBoard(Point(2, 0)).toString.contains("EmptyField") &&
      board.gameBoard(Point(3, 0)).toString.contains("EmptyField"))  {
      bool = true
    }
    else if(toTest == ifList(1) && board.gameBoard(Point(5, 0)).toString.contains("EmptyField") &&
      board.gameBoard(Point(6, 0)).toString.contains("EmptyField")) {
      bool = true
    }
    else if(toTest == ifList(2) && board.gameBoard(Point(1, 7)).toString.contains("EmptyField") &&
      board.gameBoard(Point(2, 7)).toString.contains("EmptyField")
      && board.gameBoard(Point(3, 7)).toString.contains("EmptyField")) {
      bool = true
    }
    else if(toTest == ifList(3) && board.gameBoard(Point(5, 7)).toString.contains("EmptyField") &&
      board.gameBoard(Point(6, 7)).toString.contains("EmptyField")) {
      bool = true
    }
    bool
  }
}
