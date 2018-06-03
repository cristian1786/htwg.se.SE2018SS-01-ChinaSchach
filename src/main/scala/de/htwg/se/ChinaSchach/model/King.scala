package de.htwg.se.ChinaSchach.model

case class King(side: String, promotable: String) extends Piece {
  //TODO: implement King Shogi

  val possibleMoves: List[(Int, Int)] = List((-1, 0), (-1, -1), (0, -1), (1, -1), (1, 0), (1, 1), (0, 1), (-1, 1))
}
