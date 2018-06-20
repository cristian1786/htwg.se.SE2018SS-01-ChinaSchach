package de.htwg.se.ChinaSchach.model

import de.htwg.se.ChinaSchach.util.Point

import scala.collection.mutable.ListBuffer


class Player(name: String) {

  // Assigns figures to the player(s)
  def setPieces(board: Board, side: String) : ListBuffer[Piece] = {
    val listPlayer: ListBuffer[Piece]= ListBuffer.empty
    for {
      x <- 0 to 7
      y <- 0 to 7
    }
      if (board.getPiece(Point(x, y)).getSide() == side) {
        listPlayer.append(board.getPiece(Point(x, y)))
      }

    listPlayer
}

  /* delete beat pieces from playerList
  / @params takes a playerList and the beat piece
  / @returns list with remaining pieces
   */
  def deletePiece(list: ListBuffer[Piece], piece: Piece): Unit = {
    if(list.contains(piece)) {
      list.-=(piece)
    } else {

    }
    //TODO: Exception
  }
}
