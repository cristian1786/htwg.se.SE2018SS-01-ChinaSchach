package de.htwg.se.ChinaSchach.model

import de.htwg.se.ChinaSchach.util.Point
import org.scalatest.{ Matchers, WordSpec }

class KnightTest extends WordSpec with Matchers {
  "A Knight Movement" should {
    "return true" when {
      "it moves moves in every direction" in {
        val board = new Board
        board.go()
        val sourcePoint = Point(3, 3)
        board.gameBoard += sourcePoint -> Knight("w")
        board.gameBoard += Point(2, 1) -> EmptyField(" ")
        board.gameBoard += Point(4, 1) -> EmptyField(" ")
        var movePossible: Boolean = false

        //top-right
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, Point(4, 5), board.gameBoard(sourcePoint).getPossibleMoves())
        assert(movePossible)

        //top-left
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, Point(2, 5), board.gameBoard(sourcePoint).getPossibleMoves())
        assert(movePossible)

        //bottom-left
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, Point(2, 1), board.gameBoard(sourcePoint).getPossibleMoves())
        assert(movePossible)

        //bottom-right
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, Point(4, 1), board.gameBoard(sourcePoint).getPossibleMoves())
        assert(movePossible)

      }

      "it moves in every direction taking an enemy piece" in {
        val board = new Board
        board.go()
        val sourcePoint = Point(3, 3)
        var movePossible: Boolean = false
        board.gameBoard += sourcePoint -> Knight("w")
        board.gameBoard += Point(4, 5) -> Pawn("b")
        board.gameBoard += Point(2, 5) -> Pawn("b")
        board.gameBoard += Point(2, 1) -> Pawn("b")
        board.gameBoard += Point(4, 1) -> Pawn("b")

        //top-right
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, Point(4, 5), board.gameBoard(sourcePoint).getPossibleMoves())
        assert(movePossible)

        //top-left
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, Point(2, 5), board.gameBoard(sourcePoint).getPossibleMoves())
        assert(movePossible)

        //bottom-left
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, Point(2, 1), board.gameBoard(sourcePoint).getPossibleMoves())
        assert(movePossible)

        //bottom-right
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, Point(4, 1), board.gameBoard(sourcePoint).getPossibleMoves())
        assert(movePossible)

      }
    }
  }

  "A Knight Movement" should {
    "return false" when {
      "it moves moves in every direction and there is an own Piece at destination" in {
        val board = new Board
        board.go()
        val sourcePoint = Point(3, 3)
        var movePossible: Boolean = false
        board.gameBoard += sourcePoint -> Knight("w")
        board.gameBoard += Point(4, 5) -> Pawn("w")
        board.gameBoard += Point(2, 5) -> Pawn("w")
        board.gameBoard += Point(2, 1) -> Pawn("w")
        board.gameBoard += Point(4, 1) -> Pawn("w")

        //top-right
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, Point(4, 5), board.gameBoard(sourcePoint).getPossibleMoves())
        assert(!movePossible)

        //top-left
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, Point(2, 5), board.gameBoard(sourcePoint).getPossibleMoves())
        assert(!movePossible)

        //bottom-left
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, Point(2, 1), board.gameBoard(sourcePoint).getPossibleMoves())
        assert(!movePossible)

        //bottom-right
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, Point(4, 1), board.gameBoard(sourcePoint).getPossibleMoves())
        assert(!movePossible)

      }
    }
  }
}
