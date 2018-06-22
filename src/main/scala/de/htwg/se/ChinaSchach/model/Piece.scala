package de.htwg.se.ChinaSchach.model

import scala.collection.mutable.ListBuffer
import de.htwg.se.ChinaSchach.util.Point
import javax.print.attribute.standard.Destination

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
          val tmpTuple: (Int, Int) = (source.x + y._1, source.y + y._2)
          if (board.getPiece(source).getSide() == "w" && Point(tmpTuple._1, tmpTuple._2) == destination) {
            trueOrFalse = caseQRB(board, source, tmpTuple, x)
          }
          else if (board.getPiece(source).getSide() == "b" && Point(tmpTuple._1, tmpTuple._2) == destination) {
            trueOrFalse = caseQRB(board, source, tmpTuple, x)
          }
        }
      }
    }
    trueOrFalse
  }

  def caseQRB(board: Board, source: Point, destination: (Int, Int), possibleMoves: ListBuffer[(Int, Int)]): Boolean = {
    if (board.getPiece(source).toString == "Queen" || board.getPiece(source).toString == "Rook" || board.getPiece(source).toString == "Bishop") {
      val listToCheck: ListBuffer[(Int, Int)] = {
        val idx = possibleMoves.indexOf(destination)
        possibleMoves.take(idx - 1)
      }
      for(z <- listToCheck) {
        if (board.getPiece(Point(source.x - z._1, source.y - z._2)).toString != "EmptyField") {
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
