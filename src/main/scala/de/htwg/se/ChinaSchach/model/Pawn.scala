package de.htwg.se.ChinaSchach.model

import scala.collection.mutable.ListBuffer

case class Pawn(side: String, promotable: String) extends Piece {
  //TODO: implement Pawn Shogi
  override val possibleMoves: ListBuffer[(Int, Int)] = ListBuffer((0, 1))
  override val possibleMovesPromoted: ListBuffer[(Int, Int)] = ListBuffer((-1, 0), (0, -1), (1, 0), (1, 1), (0, 1), (-1, 1))
}
