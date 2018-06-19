package de.htwg.se.ChinaSchach.model

import scala.collection.mutable.ListBuffer

case class Knight(side: String) extends Piece {
  //TODO: implement Knight Shogi
  override def getPossibleMoves(): List[ListBuffer[(Int, Int)]] = {
    val x = 2
    val possibleMoves1: ListBuffer[(Int, Int)] = ListBuffer((-1, x), (1, x), (1, -x), (-1, -x))
    val possibleMoves: List[ListBuffer[(Int, Int)]] = List(possibleMoves1)
    possibleMoves
  }
}