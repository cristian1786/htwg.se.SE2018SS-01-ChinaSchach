package de.htwg.se.ChinaSchach.model

import de.htwg.se.ChinaSchach.util.Point
import org.scalatest.{ Matchers, WordSpec }

class KingTest extends WordSpec with Matchers {
  "A King Movement" should {
    "return true" when {
      "it moves moves in every direction" in {
        val board = new Board
        board.go()
        val sourcePoint = Point(3, 3)
        board.gameBoard += sourcePoint -> King("w")
        var movePossible: Boolean = false

        //top
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, Point(3, 4), board.gameBoard(sourcePoint).getPossibleMoves())
        assert(movePossible)

        //bottom
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, Point(3, 2), board.gameBoard(sourcePoint).getPossibleMoves())
        assert(movePossible)

        //left
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, Point(2, 3), board.gameBoard(sourcePoint).getPossibleMoves())
        assert(movePossible)

        //right
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, Point(4, 3), board.gameBoard(sourcePoint).getPossibleMoves())
        assert(movePossible)

      }

      "it moves in every direction taking an enemy piece" in {
        val board = new Board
        board.go()
        val sourcePoint = Point(3, 3)
        var movePossible: Boolean = false
        board.gameBoard += sourcePoint -> King("w")
        board.gameBoard += Point(3, 4) -> Pawn("b")
        board.gameBoard += Point(3, 2) -> Pawn("b")
        board.gameBoard += Point(2, 3) -> Pawn("b")
        board.gameBoard += Point(4, 3) -> Pawn("b")

        //top
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, Point(3, 4), board.gameBoard(sourcePoint).getPossibleMoves())
        assert(movePossible)

        //bottom
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, Point(3, 2), board.gameBoard(sourcePoint).getPossibleMoves())
        assert(movePossible)

        //left
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, Point(2, 3), board.gameBoard(sourcePoint).getPossibleMoves())
        assert(movePossible)

        //right
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, Point(4, 3), board.gameBoard(sourcePoint).getPossibleMoves())
        assert(movePossible)

      }

      "it performs a big and small rochade without any piece inbetween" in {
        val board = new Board
        board.go()
        var movePossible: Boolean = false
        board.gameBoard += Point(1, 0) -> EmptyField(" ")
        board.gameBoard += Point(2, 0) -> EmptyField(" ")
        board.gameBoard += Point(3, 0) -> EmptyField(" ")
        board.gameBoard += Point(5, 0) -> EmptyField(" ")
        board.gameBoard += Point(6, 0) -> EmptyField(" ")

        //big rochade
        movePossible = board.gameBoard(Point(4, 0)).testRochade(board, Point(4, 0), Point(0, 0))
        assert(movePossible)

        //small rochade
        movePossible = board.gameBoard(Point(4, 0)).testRochade(board, Point(4, 0), Point(7, 0))
        assert(movePossible)
      }
    }
  }

  "A King Movement" should {
    "return false" when {
      "it moves in every direction taking an enemy piece" in {
        val board = new Board
        board.go()
        val sourcePoint = Point(3, 3)
        var movePossible: Boolean = false
        board.gameBoard += sourcePoint -> King("w")
        board.gameBoard += Point(3, 4) -> Pawn("w")
        board.gameBoard += Point(3, 2) -> Pawn("w")
        board.gameBoard += Point(2, 3) -> Pawn("w")
        board.gameBoard += Point(4, 3) -> Pawn("w")

        //top
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, Point(3, 4), board.gameBoard(sourcePoint).getPossibleMoves())
        assert(!movePossible)

        //bottom
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, Point(3, 2), board.gameBoard(sourcePoint).getPossibleMoves())
        assert(!movePossible)

        //left
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, Point(2, 3), board.gameBoard(sourcePoint).getPossibleMoves())
        assert(!movePossible)

        //right
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, Point(4, 3), board.gameBoard(sourcePoint).getPossibleMoves())
        assert(!movePossible)

      }

      "it performs a big and small rochade with pieces inbetween" in {
        val board = new Board
        board.go()
        var movePossible: Boolean = false

        //big rochade
        movePossible = board.gameBoard(Point(4, 0)).testRochade(board, Point(4, 0), Point(0, 0))
        assert(!movePossible)

        //small rochade
        movePossible = board.gameBoard(Point(4, 0)).testRochade(board, Point(4, 0), Point(7, 0))
        assert(!movePossible)
      }
    }
  }
}
