package de.htwg.se.ChinaSchach.model

import scala.collection.mutable.ListBuffer

case class Pawn(side: String) extends Piece {
  //TODO: implement Pawn Shogi
  override val possibleMoves: ListBuffer[(Int, Int)] = ListBuffer((0, 1))
}
