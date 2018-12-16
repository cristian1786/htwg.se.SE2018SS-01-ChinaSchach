package de.htwg.se.ChinaSchach.model

import com.google.inject.Inject

import scala.collection.mutable.ListBuffer

case class Queen @Inject() (side: String) extends Piece {
  override def getPossibleMoves(): List[ListBuffer[(Int, Int)]] = {
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
    val possibleMoves5: ListBuffer[(Int, Int)] = ListBuffer.empty
    for (x <- 1 to 8)
      possibleMoves5.+=((-x, x))
    val possibleMoves6: ListBuffer[(Int, Int)] = ListBuffer.empty
    for (x <- 1 to 8)
      possibleMoves6.+=((x, x))
    val possibleMoves7: ListBuffer[(Int, Int)] = ListBuffer.empty
    for (x <- 1 to 8)
      possibleMoves7.+=((-x, -x))
    val possibleMoves8: ListBuffer[(Int, Int)] = ListBuffer.empty
    for (x <- 1 to 8)
      possibleMoves8.+=((x, -x))
    val possibleMoves: List[ListBuffer[(Int, Int)]] = List(possibleMoves1, possibleMoves2, possibleMoves3,
      possibleMoves4, possibleMoves5, possibleMoves6, possibleMoves7, possibleMoves8)
    possibleMoves
  }
}
