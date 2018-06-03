package de.htwg.se.ChinaSchach.model

case class Pawn(side: String, promotable: String) extends Piece {
  //TODO: implement Pawn Shogi
  val possibleMoves: List[(Int, Int)] = List((0, 1))
  val possibleMovesPromoted: List[(Int, Int)] = List((-1, 0), (0, -1), (1, 0), (1, 1), (0, 1), (-1, 1))
}
