package de.htwg.se.ChinaSchach.model

import scala.collection.mutable.ListBuffer

case class Knight(side: String) extends Piece {
  //TODO: implement Knight Shogi
  override def getPossibleMoves(): ListBuffer[(Int, Int)] = {
    val x = 2
    val possibleMoves: ListBuffer[(Int, Int)] = ListBuffer((-1, x), (1, x), (1, -x), (-1, -x))
    possibleMoves
  }
}