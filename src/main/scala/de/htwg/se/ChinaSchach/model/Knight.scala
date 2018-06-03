package de.htwg.se.ChinaSchach.model

case class Knight(side: String, promotable: String) extends Piece {
  //TODO: implement Knight Shogi
  val possibleMoves: List[(Int, Int)] = List((-1, 2), (1, 2))
  //TODO: promoted Knight
  val possibleMovesPromoted: List[(Int, Int)] = List((-1, 0), (0, -1), (1, 0), (1, 1), (0, 1), (-1, 1))
}
