package de.htwg.se.ChinaSchach.model

import scala.collection.mutable.ListBuffer

case class Knight(side: String, promotable: String) extends Piece {
  //TODO: implement Knight Shogi
  override val possibleMoves: ListBuffer[(Int, Int)] = ListBuffer((-1, 2), (1, 2))
  //TODO: promoted Knight
  override val possibleMovesPromoted: ListBuffer[(Int, Int)] = ListBuffer((-1, 0), (0, -1), (1, 0), (1, 1), (0, 1), (-1, 1))
}
