package de.htwg.se.ChinaSchach.model

import scala.collection.mutable.ListBuffer

case class Silver(side: String, promotable: String) extends Piece {
  //TODO: implement Silver Shogi
  // Movement unpromoted
  override val possibleMoves: ListBuffer[(Int, Int)] = ListBuffer((-1, -1), (1, -1), (1, 1), (0, 1), (-1, 1))
  //TODO: promoted Silver
  // Movement after Promotion
  override val possibleMovesPromoted: ListBuffer[(Int, Int)] = ListBuffer((-1, 0), (0, -1), (1, 0), (1, 1), (0, 1), (-1, 1))
}
