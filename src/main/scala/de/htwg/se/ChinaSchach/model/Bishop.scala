package de.htwg.se.ChinaSchach.model

case class Bishop(side: String, promotable: String) extends Piece {
  //Bishop moves
  val possibleMoves: List[(Int, Int)] = {
    val m: List[(Int, Int)] = List.empty
    for (x <- 1 to 8) {
      val m = :+ ((-x, x), (x, x), (-x, -x), (x, -x))
    }
    m
  }
  //Promoted Bishop moves
  val possibleMovesPromoted: List[(Int, Int)] = {
    val m: List[(Int, Int)] = List((-1, 0), (0, 1), (0, -1), (1, 0))
    for (x <- 1 to 8) {
      val m = :+ ((-x, x), (x, x), (-x, -x), (x, -x))
    }
    m
  }
}