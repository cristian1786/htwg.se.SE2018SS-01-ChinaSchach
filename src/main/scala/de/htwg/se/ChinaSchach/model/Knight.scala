package de.htwg.se.ChinaSchach.model

import scala.collection.mutable.ListBuffer

case class Knight(side: String) extends Piece {
  //TODO: implement Knight Shogi
  override val possibleMoves: ListBuffer[(Int, Int)] = ListBuffer((-1, 2), (1, 2))
}
