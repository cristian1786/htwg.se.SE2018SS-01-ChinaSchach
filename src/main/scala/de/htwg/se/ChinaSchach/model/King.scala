package de.htwg.se.ChinaSchach.model

import scala.collection.mutable.ListBuffer

case class King(side: String) extends Piece {
  //TODO: implement King Shogi

  override val possibleMoves: ListBuffer[(Int, Int)] = ListBuffer((-1, 0), (-1, -1), (0, -1), (1, -1), (1, 0), (1, 1), (0, 1), (-1, 1))
}
