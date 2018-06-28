package de.htwg.se.ChinaSchach.model

import de.htwg.se.ChinaSchach.util.Point
import org.scalatest.{Matchers, WordSpec}

class RookTest extends WordSpec with Matchers {
  "A Rook Movement" should {
    "return true" when {
      "it starts at the middle of the Board and moves 2 spaces to the top, 1 space bottom, 3 spaces left and 4 spaces right of the Gameboard" in {
        val board = new Board
        board.go()
        val sourcePoint = Point(3, 3)

        board.gameBoard += sourcePoint -> Rook("w")
        var movePossible: Boolean = false

        //top
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, Point(3, 5), board.gameBoard(sourcePoint).getPossibleMoves())
        assert(movePossible)

        //top
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, Point(3, 2), board.gameBoard(sourcePoint).getPossibleMoves())
        assert(movePossible)

        //bottom
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, Point(0, 3), board.gameBoard(sourcePoint).getPossibleMoves())
        assert(movePossible)

        //bottom
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, Point(7, 3), board.gameBoard(sourcePoint).getPossibleMoves())
        assert(movePossible)

      }

      "it starts at the middle of the Board and moves 3 spaces to the top of the Gameboard and takes an enemy Piece" in {
        val board = new Board
        board.go()
        val sourcePoint = Point(3, 3)

        board.gameBoard += sourcePoint -> Rook("w")
        var movePossible: Boolean = false

        //top
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, Point(3, 6), board.gameBoard(sourcePoint).getPossibleMoves())
        assert(movePossible)
      }
    }
  }

  "A Rook Movement" should {
    "return false" when {
      "it starts at the middle of the Board and moves 2 spaces to the top, 2 space bottom, 3 spaces left and 4 spaces right of the Gameboard and there`s an own Piece at destination" in {
        val board = new Board
        board.go()
        val sourcePoint = Point(3, 3)
        val destination1 = Point(3, 5)
        val destination2 = Point(3, 1)
        val destination3 = Point(7, 3)
        val destination4 = Point(0, 3)

        board.gameBoard += destination1 -> Knight("w")
        board.gameBoard += destination2 -> Pawn("w")
        board.gameBoard += destination3 -> Rook("w")
        board.gameBoard += destination4 -> Bishop("w")
        board.gameBoard += sourcePoint -> Rook("w")
        var movePossible: Boolean = false

        //top
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, destination1, board.gameBoard(sourcePoint).getPossibleMoves())
        assert(!movePossible)

        //top
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, destination2, board.gameBoard(sourcePoint).getPossibleMoves())
        assert(!movePossible)

        //bottom
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, destination3, board.gameBoard(sourcePoint).getPossibleMoves())
        assert(!movePossible)

        //bottom
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, destination4, board.gameBoard(sourcePoint).getPossibleMoves())
        assert(!movePossible)

      }

      "it starts at the middle of the Board and moves 2 spaces to the top, 2 space bottom, 3 spaces left and 4 spaces right of the Gameboard and there`s an own Piece in the way" in {
        val board = new Board
        board.go()
        val sourcePoint = Point(3, 3)
        val destination1 = Point(3, 5)
        val destination2 = Point(3, 1)
        val destination3 = Point(7, 3)
        val destination4 = Point(0, 3)
        val inTheWay1 = Point(3, 4)
        val inTheWay2 = Point(3, 2)
        val inTheWay3 = Point(6, 3)
        val inTheWay4 = Point(1, 3)

        board.gameBoard += inTheWay1 -> Knight("w")
        board.gameBoard += inTheWay2 -> Pawn("w")
        board.gameBoard += inTheWay3 -> Rook("w")
        board.gameBoard += inTheWay4 -> Bishop("w")
        board.gameBoard += sourcePoint -> Rook("w")
        var movePossible: Boolean = false

        //top
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, destination1, board.gameBoard(sourcePoint).getPossibleMoves())
        assert(!movePossible)

        //top
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, destination2, board.gameBoard(sourcePoint).getPossibleMoves())
        assert(!movePossible)

        //bottom
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, destination3, board.gameBoard(sourcePoint).getPossibleMoves())
        assert(!movePossible)

        //bottom
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, destination4, board.gameBoard(sourcePoint).getPossibleMoves())
        assert(!movePossible)

      }

      "it starts at the middle of the Board and moves 2 spaces to the top, 2 space bottom, 3 spaces left and 4 spaces right of the Gameboard and there`s an Opponent in the way" in {
        val board = new Board
        board.go()
        val sourcePoint = Point(3, 3)
        val destination1 = Point(3, 5)
        val destination2 = Point(3, 1)
        val destination3 = Point(7, 3)
        val destination4 = Point(0, 3)
        val inTheWay1 = Point(3, 4)
        val inTheWay2 = Point(3, 2)
        val inTheWay3 = Point(6, 3)
        val inTheWay4 = Point(1, 3)

        board.gameBoard += inTheWay1 -> Knight("b")
        board.gameBoard += inTheWay2 -> Pawn("b")
        board.gameBoard += inTheWay3 -> Rook("b")
        board.gameBoard += inTheWay4 -> Bishop("b")
        board.gameBoard += sourcePoint -> Rook("b")
        var movePossible: Boolean = false

        //top
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, destination1, board.gameBoard(sourcePoint).getPossibleMoves())
        assert(!movePossible)

        //top
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, destination2, board.gameBoard(sourcePoint).getPossibleMoves())
        assert(!movePossible)

        //bottom
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, destination3, board.gameBoard(sourcePoint).getPossibleMoves())
        assert(!movePossible)

        //bottom
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, destination4, board.gameBoard(sourcePoint).getPossibleMoves())
        assert(!movePossible)

      }
    }
  }
}
