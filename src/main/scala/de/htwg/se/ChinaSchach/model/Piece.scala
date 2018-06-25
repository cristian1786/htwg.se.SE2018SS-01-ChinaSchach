package de.htwg.se.ChinaSchach.model

import scala.collection.mutable.ListBuffer
import de.htwg.se.ChinaSchach.util.Point

trait Piece {

  //TODO: something
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
    if (checkValidPoss(destination) && board.getPiece(source).getSide() != board.getPiece(destination).getSide()) {
      for (x <- possibleMoves) {
        for (y <- x) {
          if (board.getPiece(source).getSide() == "w" && Point(source.x + y._1, source.y + y._2) == destination
          || board.getPiece(source).getSide() == "b" && Point(source.x - y._1, source.y - y._2) == destination) {
            val tmpTuple: (Int, Int) = (y._1, y._2)
            trueOrFalse = caseQRB(board, source, tmpTuple, x)
          }
          /*else if (board.getPiece(source).getSide() == "b" && Point(source.x - y._1, source.y - y._2) == destination) {
            val tmpTuple: (Int, Int) = (y._1, y._2)
            trueOrFalse = caseQRB(board, source, tmpTuple, x)
          }*/
        }
      }
    }
    trueOrFalse
  }

  def caseQRB(board: Board, source: Point, move: (Int, Int), possibleMoves: ListBuffer[(Int, Int)]): Boolean = {
    var bool = true
    if (board.getPiece(source).toString == "Queen(w)" || board.getPiece(source).toString == "Rook(w)" || board.getPiece(source).toString == "Bishop(w)"
      || board.getPiece(source).toString == "Queen(b)" || board.getPiece(source).toString == "Rook(b)" || board.getPiece(source).toString == "Bishop(b)") {
      val listToCheck: ListBuffer[(Int, Int)] = {
        val idx = possibleMoves.indexOf(move)
        possibleMoves.take(idx)
      }
      println("Here List" + listToCheck)
      for(z <- listToCheck) {
        if (board.getPiece(source).getSide() == "w" && board.getPiece(Point(source.x + z._1, source.y + z._2)).toString != "EmptyField( )"
        || board.getPiece(source).getSide() == "b" && board.getPiece(Point(source.x - z._1, source.y - z._2)).toString != "EmptyField( )") {
          bool = false
        }
      }
    }
    bool
  }

  def movesAllowedP(board: Board, source: Point, destination: Point, possibleMoves: List[ListBuffer[(Int, Int)]]): Boolean = {
    var trueOrFalse = false
    if (checkValidPoss(destination) && board.getPiece(source).getSide() != board.getPiece(destination).getSide()) {
      if(board.getPiece(destination).getSide() != " ") {
        for (x <- possibleMoves(2)) {
          if (board.getPiece(source).getSide() == "w" && Point(source.x + x._1, source.y + x._2) == destination) {
            trueOrFalse = true
          }
          else if (board.getPiece(source).getSide() == "b" && Point(source.x - x._1, source.y - x._2) == destination) {
            trueOrFalse = true
          }
        }
      }
      else if(board.getPiece(destination).getSide() == " ") {
        trueOrFalse = elsePawn(board, source, destination)
      }
    }
    trueOrFalse
  }

  def elsePawn(board: Board, source: Point, destination: Point): Boolean = {
    var bool = false
    val rowTwo = List((0, 1), (1, 1), (2, 1), (3, 1), (4, 1), (5, 1), (6, 1), (7, 1))
    val rowSix = List((0, 6), (1, 6), (2, 6), (3, 6), (4, 6), (5, 6), (6, 6), (7, 6))
    if(board.getPiece(source).getSide() == "w" && rowTwo.contains((source.x, source.y))) {
      bool = repeatedIfDb(board, source, destination, "w" )
    }
    else if(board.getPiece(source).getSide() == "b" && rowSix.contains((source.x, source.y))) {
      bool = repeatedIfDb(board, source, destination, "b" )
    }
    else if(board.getPiece(source).getSide() == "w" && !rowTwo.contains((source.x, source.y))) {
      bool = repeatedIfSg(board, source, destination, "w")
    }
    else if(board.getPiece(source).getSide() == "b" && !rowSix.contains((source.x, source.y))) {
      bool = repeatedIfSg(board, source, destination, "b")
    }
    bool
  }

  def repeatedIfDb(board: Board, source: Point, destination: Point, side: String): Boolean = {
    var bool = false
    if(side == "w") {
      if (Point(source.x, source.y + 1) == destination || Point(source.x, source.y + 2) == destination) {
        bool = true
      }
    }
    else if(side == "b") {
      if (Point(source.x, source.y - 1) == destination || Point(source.x, source.y - 2) == destination) {
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
    val rowOne = ((1, 0), (2,0), (3, 0), (4,0), (5, 0), (6,0), (7, 0))
    if((board.getPiece(piecePoint).toString.contains("King") &&
      board.getPiece(point).toString.contains("Rook") && piecePoint == Point(3, 0) || piecePoint == Point(3, 7) && point == Point(0, 0)
      || point == Point(7, 0) || point == Point(0, 7) || point == Point(7, 7)) || (board.getPiece(piecePoint).toString.contains("Rook") && board.getPiece(point).toString.contains("King") &&
      piecePoint == Point(0, 0) || piecePoint == Point(7, 0) || piecePoint == Point(0, 7) || piecePoint == Point(7, 7) && point == Point(3, 0) || point == Point(3, 7))) {
      println("BooooooooYAAAAAAA MOFO!!!!!!")
      
    }
    bool
  }

  /*  def movesAllowed(player: Player, board: Board, source: Point, destination: Point, possibleMoves: List[(Int, Int)]): Boolean = {
      if (checkValidPoss(destination) && board.get(source).getSide() == "bottom" && board.get(source).getSide() != board.get(destination).getSide()) {
        for (x <- possibleMoves) {
          val checkMove = (source._1 + x._1, source._2 + x._2)
          if (destination == checkMove) {
            true
          }
        }
      }
      false
    }*/

/*  def ifTop(board: Board, source: Point, destination: Point, possibleMoves: List[(Int, Int)]) : Boolean = {
    if (checkValidPoss(destination) && board.get(source).getSide() == "top" && board.get(source).getSide() != board.get(destination).getSide()) {
      for (x <- possibleMoves) {
        val checkMove = (source._1 - x._1, source._2 - x._2)
        if (destination == checkMove) {
          true
        }
      }
    }
    false
  }

  def ifBottom(board: Board, source: Point, destination: Point, possibleMoves: List[(Int, Int)]) : Boolean = {
    if (checkValidPoss(destination) && board.get(source).getSide() == "bottom" && board.get(source).getSide() != board.get(destination).getSide()) {
      for (x <- possibleMoves) {
        val checkMove = (source._1 + x._1, source._2 + x._2)
        if (destination == checkMove) {
          true
        }
      }
    }
    false
  }*/
}
