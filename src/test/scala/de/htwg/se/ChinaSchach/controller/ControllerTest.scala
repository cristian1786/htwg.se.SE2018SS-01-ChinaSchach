package de.htwg.se.ChinaSchach.controller

import de.htwg.se.ChinaSchach.model.{EmptyField, Queen}
import de.htwg.se.ChinaSchach.util.Point
import org.scalatest.{Matchers, WordSpec}

class ControllerTest extends WordSpec with Matchers {
  "Controller" should {
    "return true" when {
      "Pawn moves 1 space forward" in {
        val c = new Controller()
        c.boardInit()

        c.getSelectedPoint(Point(0, 1))
        c.getSelectedPoint(Point(0, 2))
        assert(c.board.gameBoard(Point(0, 2)).toString == "Pawn(w)")
      }

      "Pawn moves 2 space forward" in {
        val c = new Controller()
        c.boardInit()

        c.getSelectedPoint(Point(0, 1))
        c.getSelectedPoint(Point(0, 3))
        assert(c.board.gameBoard(Point(0, 3)).toString == "Pawn(w)")
      }

      "Knight moves forward" in {
        val c = new Controller()
        c.boardInit()

        c.getSelectedPoint(Point(1, 0))
        c.getSelectedPoint(Point(2, 2))
        assert(c.board.gameBoard(Point(2, 2)).toString == "Knight(w)")
      }

      "Blacks can move after whites" in {
        val c = new Controller()
        c.boardInit()

        c.getSelectedPoint(Point(0, 1))
        c.getSelectedPoint(Point(0, 2))
        c.getSelectedPoint(Point(1, 6))
        c.getSelectedPoint(Point(1, 4))
        assert(c.board.gameBoard(Point(0, 2)).toString == "Pawn(w)")
        assert(c.board.gameBoard(Point(1, 4)).toString == "Pawn(b)")
      }

      "Big rochade works when no pieces inbetween" in {
        val c = new Controller()
        c.boardInit()
        c.board.gameBoard += Point(1, 0) -> EmptyField(" ")
        c.board.gameBoard += Point(2, 0) -> EmptyField(" ")
        c.board.gameBoard += Point(3, 0) -> EmptyField(" ")

        c.getSelectedPoint(Point(4, 0))
        c.getSelectedPoint(Point(0, 0))
        assert(c.board.gameBoard(Point(2, 0)).toString == "King(w)")
        assert(c.board.gameBoard(Point(3, 0)).toString == "Rook(w)")
      }

      "Small rochade works when no pieces inbetween" in {
        val c = new Controller()
        c.boardInit()
        c.board.gameBoard += Point(5, 0) -> EmptyField(" ")
        c.board.gameBoard += Point(6, 0) -> EmptyField(" ")

        c.getSelectedPoint(Point(4, 0))
        c.getSelectedPoint(Point(7, 0))
        assert(c.board.gameBoard(Point(6, 0)).toString == "King(w)")
        assert(c.board.gameBoard(Point(5, 0)).toString == "Rook(w)")
      }

      "Whites win after taking black King" in {
        val c = new Controller()
        c.boardInit()
        c.board.gameBoard += Point(4, 6) -> Queen("w")

        c.getSelectedPoint(Point(4, 6))
        c.getSelectedPoint(Point(4, 7))
        assert(c.topKingDead == true)
        assert(c.board.gameBoard(Point(4, 7)).toString == "Queen(w)")
        assert(c.board.gameBoard(Point(4, 6)).toString == "EmptyField( )")
      }

      "Blacks win after taking white King" in {
        val c = new Controller()
        c.boardInit()
        c.board.gameBoard += Point(4, 1) -> Queen("b")

        c.getSelectedPoint(Point(4, 1))
        c.getSelectedPoint(Point(4, 0))
        assert(c.bottomKingDead == true)
        assert(c.board.gameBoard(Point(4, 0)).toString == "Queen(b)")
        assert(c.board.gameBoard(Point(4, 1)).toString == "EmptyField( )")
      }

      "White Queen is not on the board anymore" in {
        val c = new Controller()
        c.boardInit()
        c.board.gameBoard += Point(3, 1) -> Queen("b")

        c.getSelectedPoint(Point(3, 1))
        c.getSelectedPoint(Point(3, 0))
        assert(!c.listPlayer1.contains(Queen("w")))
        assert(c.listKillPlayer1.contains(Queen("w")))
      }
    }
  }
}
