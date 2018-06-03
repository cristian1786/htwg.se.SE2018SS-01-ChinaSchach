package de.htwg.se.ChinaSchach.model

case class Gold(side: String, promotable: String) extends Piece {
  //TODO: Implementation of Gold Shogi
  val possibleMoves: List[(Int, Int)] = List((-1, 0), (0, -1), (1, 0), (1, 1), (0, 1), (-1, 1))
}
