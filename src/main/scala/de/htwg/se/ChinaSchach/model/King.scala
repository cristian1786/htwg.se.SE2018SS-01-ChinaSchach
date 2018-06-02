package de.htwg.se.ChinaSchach.model

case class King(side: String, promotable: String) extends Piece {
  //TODO: implement King Shogi

  def movesAllowed(source: (Int, Int), destination: (Int, Int)): Boolean = {
    val allowed = false

    allowed
  }

  def movesPossible(source: (Int, Int), destination: (Int, Int)): Boolean = {
    val possible = false

    possible
  }

}
