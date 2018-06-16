package de.htwg.se.ChinaSchach.model

import scala.collection.mutable.ListBuffer

case class Rook(side: String, promotable: String) extends Piece {
  //TODO: implement Rook Shogi

  override val possibleMoves: ListBuffer[(Int, Int)] = ListBuffer.empty
  for (x <- 1 to 8)
    possibleMoves.+=((0, x), (0, -x), (x, 0), (-x, 0))
}
