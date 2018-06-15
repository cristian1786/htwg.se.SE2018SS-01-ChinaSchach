package de.htwg.se.ChinaSchach.model

import scala.collection.mutable.ListBuffer

case class Bishop(side: String, promotable: String) extends Piece {
  //Bishop moves
  override val possibleMoves: ListBuffer[(Int, Int)] = ListBuffer.empty
  for (x <- 1 to 8)
    possibleMoves.+=((-x, x), (x, x), (-x, -x), (x, -x))

  //Promoted Bishop moves
  override val possibleMovesPromoted: ListBuffer[(Int, Int)] = ListBuffer((-1, 0), (0, 1), (0, -1), (1, 0))
  for (x <- 1 to 8)
    possibleMovesPromoted.+=((-x, x), (x, x), (-x, -x), (x, -x))
}