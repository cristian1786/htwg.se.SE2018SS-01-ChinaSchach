package de.htwg.se.ChinaSchach.model

import com.google.inject.Inject

import scala.collection.mutable.ListBuffer

case class Bishop @Inject()(side: String) extends Piece {
  //Bishop moves
  override def getPossibleMoves(): List[ListBuffer[(Int, Int)]] = {
    val possibleMoves1: ListBuffer[(Int, Int)] = ListBuffer.empty
    for (x <- 1 to 8)
      possibleMoves1.+=((-x, x))
    val possibleMoves2: ListBuffer[(Int, Int)] = ListBuffer.empty
    for (x <- 1 to 8)
      possibleMoves2.+=((x, x))
    val possibleMoves3: ListBuffer[(Int, Int)] = ListBuffer.empty
    for (x <- 1 to 8)
      possibleMoves3.+=((-x, -x))
    val possibleMoves4: ListBuffer[(Int, Int)] = ListBuffer.empty
    for (x <- 1 to 8)
      possibleMoves4.+=((x, -x))
    val possibleMoves: List[ListBuffer[(Int, Int)]] = List(possibleMoves1, possibleMoves2, possibleMoves3, possibleMoves4)
    possibleMoves
  }
}