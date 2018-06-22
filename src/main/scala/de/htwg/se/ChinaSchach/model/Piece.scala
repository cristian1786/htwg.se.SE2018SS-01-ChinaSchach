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
          if (board.getPiece(source).getSide() == "w" && Point(source.x + y._1, source.y + y._2) == destination) {
            val tmpTuple: (Int, Int) = (source.x + y._1, source.y + y._2)
            trueOrFalse = caseQRB(board, source, tmpTuple, x)
          }
          else if (board.getPiece(source).getSide() == "b" && Point(source.x - y._1, source.y - y._2) == destination) {
            val tmpTuple: (Int, Int) = (source.x - y._1, source.y - y._2)
            trueOrFalse = caseQRB(board, source, tmpTuple, x)
          }
        }
      }
    }
    trueOrFalse
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
      else {
        trueOrFalse = elsePawn(board, source, destination)
      }
    }
    trueOrFalse
  }

  def elsePawn(board: Board, source: Point, destination: Point): Boolean = {
    var bool = false
    val rowTwo = List.range((0, 1), (0, 7))
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
      bool = repeatedIfSg(board, source, destination, "w")
    }
    bool
  }

  def repeatedIfDb(board: Board, source: Point, destination: Point, side: String): Boolean = {
    var bool = false
    if(side == "w") {
      if (Point(source.x, source.y + 1) == destination || Point(source.x, source.y + 2) == destination) {
        bool = true
      }
      else {
        bool = false
      }
    }
    else if(side == "b") {
      if (Point(source.x, source.y - 1) == destination || Point(source.x, source.y - 2) == destination) {
        bool = true
      }
      else {
        bool = false
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

  def caseQRB(board: Board, source: Point, destination: (Int, Int), possibleMoves: ListBuffer[(Int, Int)]): Boolean = {
    if (board.getPiece(source).toString == "Queen(w)" || board.getPiece(source).toString == "Rook(w)" || board.getPiece(source).toString == "Bishop(w)"
     || board.getPiece(source).toString == "Queen(b)" || board.getPiece(source).toString == "Rook(b)" || board.getPiece(source).toString == "Bishop(b)") {
      val listToCheck: ListBuffer[(Int, Int)] = {
        val idx = possibleMoves.indexOf(destination)
        possibleMoves.take(idx - 1)
      }
      for(z <- listToCheck) {
        if (board.getPiece(Point(source.x - z._1, source.y - z._2)).toString != "EmptyField( )") {
          false
        }
      }
    }
    true
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
