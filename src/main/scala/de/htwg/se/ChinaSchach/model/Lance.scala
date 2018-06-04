package de.htwg.se.ChinaSchach.model

import scala.collection.mutable.ListBuffer

case class Lance(side: String, promotable: String) extends Piece {
  //TODO: implement Lance Shogi
  val possibleMoves: ListBuffer[(Int, Int)] = {
    val m: ListBuffer[(Int, Int)] = ListBuffer.empty[(Int, Int)]
    for (x <- 1 to 8) {
      m.+=((0, x))
    }
    m
  }
  //TODO: promoted Lance
  val possibleMovesPromoted: List[(Int, Int)] = List((-1, 0), (0, -1), (1, 0), (1, 1), (0, 1), (-1, 1))
}
