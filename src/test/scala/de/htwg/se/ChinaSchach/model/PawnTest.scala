package de.htwg.se.ChinaSchach.model

import de.htwg.se.ChinaSchach.util.Point
import org.scalatest.{Matchers, WordSpec}

class PawnTest extends WordSpec with Matchers {
  "A Pawn Movement" should {
    "return true" when {
      "it moves 1 or 2 spaces from starting point" in {
        val board = new Board
        board.go()
        val sourcePoint = Point(3, 1)
        var movePossible: Boolean = false

        //2-Space Move
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, Point(3, 2), board.gameBoard(sourcePoint).getPossibleMoves())
        assert(movePossible)

        //1-Space Move
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, Point(3, 3), board.gameBoard(sourcePoint).getPossibleMoves())
        assert(movePossible)

      }

      "it moves 1 space taking an Enemy-Piece " in {
        val board = new Board
        board.go()
        val sourcePoint = Point(3, 1)
        val destinationPoint = Point(4, 2)

        board.gameBoard += destinationPoint -> Pawn("b")
        var movePossible: Boolean = false

        //top right
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, destinationPoint, board.gameBoard(sourcePoint).getPossibleMoves())
        assert(movePossible)
      }
    }
  }
}
