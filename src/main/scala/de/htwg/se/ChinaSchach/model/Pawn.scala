package de.htwg.se.ChinaSchach.model

import scala.collection.mutable.ListBuffer

case class Pawn(side: String) extends Piece {
  //TODO: implement Pawn
  override def getPossibleMoves(): List[ListBuffer[(Int, Int)]] = {
    val possibleMoves1: ListBuffer[(Int, Int)] = ListBuffer((0, 1))
    val possibleMoves2: ListBuffer[(Int, Int)] = ListBuffer((0, 2))
    val possibleMoves: List[ListBuffer[(Int, Int)]] = List(possibleMoves1, possibleMoves2)
    possibleMoves
  }
}