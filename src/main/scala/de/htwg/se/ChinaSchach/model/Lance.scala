package de.htwg.se.ChinaSchach.model

case class Lance(side: String, promotable: String) extends Piece {
  //TODO: implement Lance Shogi
  val possibleMoves: List[(Int, Int)] = {
    val m: List[(Int, Int)] = List.empty
    for (x <- 1 to 8) {
      val m = :+ (0, x)
    }
    m
  }
  //TODO: promoted Lance
  val possibleMovesPromoted: List[(Int, Int)] = List((-1, 0), (0, -1), (1, 0), (1, 1), (0, 1), (-1, 1))
}
