package de.htwg.se.ChinaSchach.model

import scala.collection.mutable.ListBuffer

case class Rook(side: String, promotable: String) extends Piece {
  //TODO: implement Rook Shogi
  val possibleMoves: ListBuffer[(Int, Int)] = {
    val m: ListBuffer[(Int, Int)] = ListBuffer.empty
    for (x <- 1 to 8) {
      m.+=((0, x), (0, -x), (x, 0), (-x, 0))
    }
    m
  }

  //TODO: promoted Rook
  val possibleMovesPromoted: ListBuffer[(Int, Int)] = {
    val m: ListBuffer[(Int, Int)] = ListBuffer((-1, 0), (0, 1), (0, -1), (1, 0))
    for (x <- 1 to 8) {
      m.+=((0, x), (0, -x), (x, 0), (-x, 0))
    }
    m
  }

}
