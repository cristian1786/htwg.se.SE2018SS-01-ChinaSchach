package de.htwg.se.ChinaSchach.model

import de.htwg.se.ChinaSchach.util.Point
import scala.collection.mutable.ListBuffer

class Player() {
  val rows = 7
  val cols = 7
  var Turn: Boolean = false

  // Assigns figures to the player(s)
  def setPieces(board: Board, side: String): ListBuffer[Piece] = {
    val listPlayer: ListBuffer[Piece] = ListBuffer.empty
    for {
      x <- 0 to rows
      y <- 0 to cols
    } if (board.gameBoard(Point(x, y)).getSide() == side) {
      listPlayer.append(board.gameBoard(Point(x, y)))
    }
    listPlayer
  }
}
