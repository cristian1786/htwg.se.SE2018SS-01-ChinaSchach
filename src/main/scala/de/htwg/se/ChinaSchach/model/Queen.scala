package de.htwg.se.ChinaSchach.model

import scala.collection.mutable.ListBuffer

case class Queen(side: String) extends Piece {
  //TODO: Implementation of Gold Shogi
  override def getPossibleMoves(): ListBuffer[(Int, Int)] = {
    val possibleMoves: ListBuffer[(Int, Int)] = ListBuffer.empty
    for (x <- 1 to 8)
      possibleMoves.+=((0, x), (0, -x), (x, 0), (-x, 0), (-x, x), (x, x), (-x, -x), (x, -x))
    possibleMoves
  }
}
