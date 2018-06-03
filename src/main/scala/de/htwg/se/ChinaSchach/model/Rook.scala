package de.htwg.se.ChinaSchach.model

case class Rook(side: String, promotable: String) extends Piece {
  //TODO: implement Rook Shogi
  val possibleMoves: List[(Int, Int)] = {
    val m: List[(Int, Int)] = List.empty
    for (x <- 1 to 8) {
      val m = :+ ((0, x), (0, -x), (x, 0), (-x, 0))
    }
    m
  }

  //TODO: promoted Rook
  val possibleMovesPromoted: List[(Int, Int)] = {
    val m: List[(Int, Int)] = List((-1, 0), (0, 1), (0, -1), (1, 0))
    for (x <- 1 to 8) {
      val m = :+ ((0, x), (0, -x), (x, 0), (-x, 0))
    }
    m
  }

}
