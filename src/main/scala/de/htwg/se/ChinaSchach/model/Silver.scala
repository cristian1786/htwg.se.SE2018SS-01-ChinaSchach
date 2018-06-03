package de.htwg.se.ChinaSchach.model

case class Silver(side: String, promotable: String) extends Piece {
  //TODO: implement Silver Shogi
  // Movement unpromoted
  val possibleMoves: List[(Int, Int)] = List((-1, -1), (1, -1), (1, 1), (0, 1), (-1, 1))
  //TODO: promoted Silver
  // Movement after Promotion
  val possibleMovesPromoted: List[(Int, Int)] = List((-1, 0), (0, -1), (1, 0), (1, 1), (0, 1), (-1, 1))
}
