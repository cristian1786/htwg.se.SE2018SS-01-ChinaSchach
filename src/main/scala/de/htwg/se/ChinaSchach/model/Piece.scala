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
        if (board.getPiece(source).getSide() == "w" && Point(source.x + x._1, source.y + x._2) == destination) {
          trueOrFalse = true
        }
        else if (board.getPiece(source).getSide() == "b" && Point(source.x - x._1, source.y - x._2) == destination) {
          trueOrFalse = true
        }
      }
    }
    trueOrFalse
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
