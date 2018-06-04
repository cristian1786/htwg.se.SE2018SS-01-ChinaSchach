package de.htwg.se.ChinaSchach.model

import scala.collection.mutable.ListBuffer

case class Bishop(side: String, promotable: String) extends Piece {
  //Bishop moves
  val possibleMoves: ListBuffer[(Int, Int)] = {
    val m: ListBuffer[(Int, Int)] = ListBuffer.empty
    for (x <- 1 to 8) {
      m.+=((-x, x), (x, x), (-x, -x), (x, -x))
    }
    m
  }
  //Promoted Bishop moves
  val possibleMovesPromoted: ListBuffer[(Int, Int)] = {
    val m: ListBuffer[(Int, Int)] = ListBuffer((-1, 0), (0, 1), (0, -1), (1, 0))
    for (x <- 1 to 8) {
      m.+=((-x, x), (x, x), (-x, -x), (x, -x))
    }
    m
  }
}