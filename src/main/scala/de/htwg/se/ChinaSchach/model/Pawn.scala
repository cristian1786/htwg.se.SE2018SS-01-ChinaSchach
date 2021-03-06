package de.htwg.se.ChinaSchach.model

import com.google.inject.Inject

import scala.collection.mutable.ListBuffer

case class Pawn @Inject()(side: String) extends Piece {
  override def getPossibleMoves(): List[ListBuffer[(Int, Int)]] = {
    val possibleMoves1: ListBuffer[(Int, Int)] = ListBuffer((0, 1))
    val possibleMoves2: ListBuffer[(Int, Int)] = ListBuffer((0, 2))
    val possibleMoves3: ListBuffer[(Int, Int)] = ListBuffer((-1, 1), (1, 1))
    val possibleMoves: List[ListBuffer[(Int, Int)]] = List(possibleMoves1, possibleMoves2, possibleMoves3)
    possibleMoves
  }
}