package de.htwg.se.ChinaSchach.model

import de.htwg.se.ChinaSchach.util.Point
import org.scalatest.{ Matchers, WordSpec }

class PawnTest extends WordSpec with Matchers {
  "A Pawn Movement" should {
    "return true" when {
      "it moves 1 or 2 spaces from starting point" in {
        val board = new Board
        board.go()
        val sourcePoint = Point(3, 1)
        var movePossible: Boolean = false

        //2-Space Move
        movePossible = board.gameBoard(sourcePoint).movesAllowedP(board, sourcePoint, Point(3, 2), board.gameBoard(sourcePoint).getPossibleMoves())
        assert(movePossible)

        //1-Space Move
        movePossible = board.gameBoard(sourcePoint).movesAllowedP(board, sourcePoint, Point(3, 3), board.gameBoard(sourcePoint).getPossibleMoves())
        assert(movePossible)

      }

      "it moves 1 space taking an Enemy-Piece " in {
        val board = new Board
        board.go()
        val sourcePoint = Point(3, 1)
        val destinationPoint = Point(4, 2)

        board.gameBoard += destinationPoint -> Pawn("b")
        var movePossible: Boolean = false

        movePossible = board.gameBoard(sourcePoint).movesAllowedP(board, sourcePoint, destinationPoint, board.gameBoard(sourcePoint).getPossibleMoves())
        assert(movePossible)
      }
    }
  }

  "A Pawn Movement" should {
    "return false" when {
      "it moves 2 spaces from starting point and theres another piece in the way" in {
        val board = new Board
        board.go()
        val sourcePoint = Point(3, 1)
        val inTheWay = Point(3, 2)
        board.gameBoard += inTheWay -> Pawn("b")

        var movePossible: Boolean = false

        //2-Space Move
        movePossible = board.gameBoard(sourcePoint).movesAllowedP(board, sourcePoint, Point(3, 3), board.gameBoard(sourcePoint).getPossibleMoves())
        assert(!movePossible)
      }

      "it moves 1 space with another Piece standing before it" in {
        val board = new Board
        board.go()
        val sourcePoint = Point(3, 1)
        val destinationPoint = Point(3, 2)

        board.gameBoard += destinationPoint -> Pawn("b")
        var movePossible: Boolean = false

        movePossible = board.gameBoard(sourcePoint).movesAllowedP(board, sourcePoint, destinationPoint, board.gameBoard(sourcePoint).getPossibleMoves())
        assert(!movePossible)
      }

      "it moves 1 space trying to take an Own-Piece " in {
        val board = new Board
        board.go()
        val sourcePoint = Point(3, 1)
        val destinationPoint = Point(4, 2)

        board.gameBoard += destinationPoint -> Pawn("w")
        var movePossible: Boolean = false

        movePossible = board.gameBoard(sourcePoint).movesAllowedP(board, sourcePoint, destinationPoint, board.gameBoard(sourcePoint).getPossibleMoves())
        assert(!movePossible)
      }

      "it moves 2 space when not on start point" in {
        val board = new Board
        board.go()
        val sourcePoint = Point(3, 2)
        val destinationPoint = Point(3, 4)

        var movePossible: Boolean = false

        movePossible = board.gameBoard(sourcePoint).movesAllowedP(board, sourcePoint, destinationPoint, board.gameBoard(sourcePoint).getPossibleMoves())
        assert(!movePossible)
      }
    }
  }
}
