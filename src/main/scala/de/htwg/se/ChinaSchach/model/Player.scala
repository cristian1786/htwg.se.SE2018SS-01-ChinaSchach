package de.htwg.se.ChinaSchach.model

import java.util.NoSuchElementException

import de.htwg.se.ChinaSchach.util.Point

import scala.collection.mutable.ListBuffer


class Player() {
  val rows = 7
  val cols = 7

  // Assigns figures to the player(s)
  def setPieces(board: Board, side: String) : ListBuffer[Piece] = {
    val listPlayer: ListBuffer[Piece]= ListBuffer.empty
    for {
      x <- 0 to rows
      y <- 0 to cols
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
//      throw NoSuchElementException
    }
  }
}
