package de.htwg.se.ChinaSchach.model

import scala.collection.mutable.ListBuffer

case class King(side: String) extends Piece {
  //TODO: implement King Shogi
  override def getPossibleMoves(): List[ListBuffer[(Int, Int)]] = {
    val possibleMoves1: ListBuffer[(Int, Int)] = ListBuffer((-1, 0), (-1, -1), (0, -1), (1, -1), (1, 0), (1, 1), (0, 1), (-1, 1))
    val possibleMoves: List[ListBuffer[(Int, Int)]] = List(possibleMoves1)
    possibleMoves
  }
}
