package de.htwg.se.ChinaSchach.model

import scala.collection.mutable.ListBuffer

trait Piece {

  //TODO: something
  def promotable: String
  def side: String
  def possibleMoves: ListBuffer[(Int, Int)] = ListBuffer.empty
  def possibleMovesPromoted: ListBuffer[(Int, Int)] = ListBuffer.empty
//  def setSide(side: String) : Unit = this.side = side
//
//  def setPromotable(promotable: String) : Unit = this.promotable = promotable

  def getSide() : String = side

  def getPromotable() : String = promotable

  def getPossibleMoves() : ListBuffer[(Int, Int)] = possibleMoves

  def getPossibleMovesPromoted() : ListBuffer[(Int, Int)] = possibleMovesPromoted

  def checkValidPoss(poss: (Int, Int)): Boolean = {
    var isValid: Boolean = false
    if (poss._1 >= 0 && poss._1 <= 8 && poss._2 >= 0 && poss._2 <= 8) {
      isValid = true
    }
    isValid
  }

  def movesAllowed(board: Board, source: (Int, Int), destination: (Int, Int), possibleMoves: List[(Int, Int)]): Boolean = {
    if (checkValidPoss(destination) && board.get(source).getSide() != board.get(destination).getSide()) {
      for (x <- possibleMoves) {
        val checkMove = (source._1 + x._1, source._2 + x._2)
        if (destination == checkMove) {
          true
        }
      }
    }
    false
  }

  def movesAllowed(player: Player, board: Board, source: (Int, Int), destination: (Int, Int), possibleMoves: List[(Int, Int)]): Boolean = {
    if (checkValidPoss(destination) && board.get(source).getSide() != board.get(destination).getSide()) {
      for (x <- possibleMoves) {
        val checkMove = (source._1 + x._1, source._2 + x._2)
        if (destination == checkMove) {
          true
        }
      }
    }
    false
  }
}
