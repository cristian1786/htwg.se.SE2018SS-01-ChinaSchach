package de.htwg.se.ChinaSchach.model

import scala.collection.mutable.ListBuffer

case class Gold(side: String, promotable: String) extends Piece {
  //TODO: Implementation of Gold Shogi
  override val possibleMoves: ListBuffer[(Int, Int)] = ListBuffer((-1, 0), (0, -1), (1, 0), (1, 1), (0, 1), (-1, 1))
}
