package de.htwg.se.ChinaSchach.model

import scala.collection.mutable.ListBuffer

class Player(name: String) {

  //TODO: figures
  def setFigures(board: Board, side: String) : ListBuffer[Piece] = {
    val listPlayer= ListBuffer.empty[Piece]
    for (x <- 0 to 8) {
      for (y <- 0 to 8) {
        if (board.get(x, y) != null && board.get(y, y).getSide() == side) {
          listPlayer.append(board.get(x, y))
        }
      }
    }
    listPlayer
  }

}
