package de.htwg.se.ChinaSchach.model

import scala.collection.mutable.ListBuffer

case class Pawn(side: String) extends Piece {
  //TODO: implement Pawn Shogi
  override def getPossibleMoves(): ListBuffer[(Int, Int)] = {
    val possibleMoves: ListBuffer[(Int, Int)] = ListBuffer((0, 1))
    possibleMoves
  }
}