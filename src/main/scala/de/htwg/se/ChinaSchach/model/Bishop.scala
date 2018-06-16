package de.htwg.se.ChinaSchach.model

import scala.collection.mutable.ListBuffer

case class Bishop(side: String) extends Piece {
  //Bishop moves
  override def getPossibleMoves(): ListBuffer[(Int, Int)] = {
    val possibleMoves: ListBuffer[(Int, Int)] = ListBuffer.empty
    for (x <- 1 to 8)
      possibleMoves.+=((-x, x), (x, x), (-x, -x), (x, -x))
    possibleMoves
  }
}