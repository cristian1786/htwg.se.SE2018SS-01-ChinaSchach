package de.htwg.se.ChinaSchach.model

import de.htwg.se.ChinaSchach.util.Point
import org.scalatest._

class BishopTest extends WordSpec with Matchers {
  "A Bishop Movement" should {
    "return true" when {
      "it starts at the middle of the Board and moves 2 spaces diagonally to the top right, top left and 1 move bottom right, bottom left of the Gameboard" in {
        val board = new Board
        board.go()
        val sourcePoint = Point(3, 3)

        board.gameBoard += sourcePoint -> Bishop("w")
        var movePossible: Boolean = false

        //top right
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, Point(5, 5), board.gameBoard(sourcePoint).getPossibleMoves())
        assert(movePossible)

        //top left
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, Point(1, 5), board.gameBoard(sourcePoint).getPossibleMoves())
        assert(movePossible)

        //bottom right
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, Point(4, 2), board.gameBoard(sourcePoint).getPossibleMoves())
        assert(movePossible)

        //bottom left
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, Point(2, 2), board.gameBoard(sourcePoint).getPossibleMoves())
        assert(movePossible)

      }

      "it moves 3 spaces and there is an Enemy on the destination Point " in {
        val board = new Board
        board.go()
        val sourcePoint = Point(3, 3)
        val destinationPoint = Point(6, 6)

        board.gameBoard += sourcePoint -> Bishop("w")
        var movePossible: Boolean = false

        //top right
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, destinationPoint, board.gameBoard(sourcePoint).getPossibleMoves())
        assert(movePossible)
      }
    }
  }

  "A Bishop Movement" should {
    "return false" when {
      "it starts in the middle of the Board and moves diagonally to the top right, top left,  bottom right and bottom left of the Gameboard but there's an own Piece in the way" in {
        val board = new Board
        board.go()
        val sourcePoint = Point(3, 3)
        val inTheWay1 = Point(5, 5)
        val inTheWay2 = Point(0, 5)
        val inTheWay3 = Point(5, 0)
        val inTheWay4 = Point(1, 1)

        board.gameBoard += inTheWay1 -> Bishop("w")
        board.gameBoard += inTheWay2 -> Pawn("w")
        board.gameBoard += inTheWay3 -> Rook("w")
        board.gameBoard += inTheWay4 -> Queen("w")
        var movePossible: Boolean = false

        //top right
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, Point(7, 7), board.gameBoard(sourcePoint).getPossibleMoves())
        assert(!movePossible)

        //top left
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, Point(0, 7), board.gameBoard(sourcePoint).getPossibleMoves())
        assert(!movePossible)

        //bottom right
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, Point(7, 0), board.gameBoard(sourcePoint).getPossibleMoves())
        assert(!movePossible)

        //bottom left
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, Point(0, 0), board.gameBoard(sourcePoint).getPossibleMoves())
        assert(!movePossible)

      }

      "it starts in the middle of the Board and moves diagonally to the top right, top left,  bottom right and bottom left of the Gameboard but there's an own Piece at the destination" in {
        val board = new Board
        board.go()
        val sourcePoint = Point(3, 3)
        val inTheWay1 = Point(5, 5)
        val inTheWay2 = Point(0, 5)
        val inTheWay3 = Point(5, 0)
        val inTheWay4 = Point(1, 1)

        board.gameBoard += inTheWay1 -> Bishop("w")
        board.gameBoard += inTheWay2 -> Pawn("w")
        board.gameBoard += inTheWay3 -> Rook("w")
        board.gameBoard += inTheWay4 -> Queen("w")
        var movePossible: Boolean = false

        //top right
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, inTheWay1, board.gameBoard(sourcePoint).getPossibleMoves())
        assert(!movePossible)

        //top left
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, inTheWay2, board.gameBoard(sourcePoint).getPossibleMoves())
        assert(!movePossible)

        //bottom right
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, inTheWay3, board.gameBoard(sourcePoint).getPossibleMoves())
        assert(!movePossible)

        //bottom left
        movePossible = board.gameBoard(sourcePoint).movesAllowed(board, sourcePoint, inTheWay4, board.gameBoard(sourcePoint).getPossibleMoves())
        assert(!movePossible)

      }
    }
  }
}
