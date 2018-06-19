package de.htwg.se.ChinaSchach.model

import scala.collection.mutable.ListBuffer

case class Rook(side: String) extends Piece {
  //TODO: implement Rook Shogi
  override def getPossibleMovesQBR(): List[ListBuffer[(Int, Int)]] = {
    val possibleMoves1: ListBuffer[(Int, Int)] = ListBuffer.empty
    for (x <- 1 to 8)
      possibleMoves1.+=((0, x))
    val possibleMoves2: ListBuffer[(Int, Int)] = ListBuffer.empty
    for (x <- 1 to 8)
      possibleMoves2.+=((0, -x))
    val possibleMoves3: ListBuffer[(Int, Int)] = ListBuffer.empty
    for (x <- 1 to 8)
      possibleMoves3.+=((x, 0))
    val possibleMoves4: ListBuffer[(Int, Int)] = ListBuffer.empty
    for (x <- 1 to 8)
      possibleMoves4.+=((-x, 0))
    val possibleMoves: List[ListBuffer[(Int, Int)]] = List(possibleMoves1, possibleMoves2, possibleMoves3, possibleMoves4)
    possibleMoves
  }
}
