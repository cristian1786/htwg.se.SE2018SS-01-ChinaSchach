package de.htwg.se.ChinaSchach.controller

import de.htwg.se.ChinaSchach.model.{EmptyField, Pawn, Queen}
import de.htwg.se.ChinaSchach.util.Point
import org.scalatest.{Matchers, WordSpec}

class ControllerTest extends WordSpec with Matchers {
  "Controller" should {
    "return true" when {
      "Pawn moves 1 space forward" in {
        val c = new Controller()
        c.boardInit()

        c.savePiecePoint(Point(0, 1))
        c.getSelectedPoint(Point(0, 2))
        assert(c.board.gameBoard(Point(0, 2)).toString == "Pawn(w)")
      }

      "Pawn moves 2 space forward" in {
        val c = new Controller()
        c.boardInit()

        c.savePiecePoint(Point(0, 1))
        c.getSelectedPoint(Point(0, 3))
        assert(c.board.gameBoard(Point(0, 3)).toString == "Pawn(w)")
      }

      "Knight moves forward right/left, backwards right/left, left right/left and right left/right" in {
        val c = new Controller()
        c.boardInit()
        //forward right
        c.savePiecePoint(Point(1, 0))
        c.getSelectedPoint(Point(2, 2))
        assert(c.board.gameBoard(Point(2, 2)).toString == "Knight(w)")
        //forward left
        c.savePiecePoint(Point(1, 7))
        c.getSelectedPoint(Point(2, 5))
        assert(c.board.gameBoard(Point(2, 5)).toString == "Knight(b)")
        //right up
        c.savePiecePoint(Point(2, 2))
        c.getSelectedPoint(Point(4, 3))
        assert(c.board.gameBoard(Point(4, 3)).toString == "Knight(w)")
        //left up
        c.savePiecePoint(Point(2, 5))
        c.getSelectedPoint(Point(4, 4))
        assert(c.board.gameBoard(Point(4, 4)).toString == "Knight(b)")
        //right down
        c.savePiecePoint(Point(4, 3))
        c.getSelectedPoint(Point(6, 2))
        assert(c.board.gameBoard(Point(6, 2)).toString == "Knight(w)")
        //left down
        c.savePiecePoint(Point(4, 4))
        c.getSelectedPoint(Point(6, 5))
        assert(c.board.gameBoard(Point(6, 5)).toString == "Knight(b)")
        //forward left
        c.savePiecePoint(Point(6, 2))
        c.getSelectedPoint(Point(5, 4))
        assert(c.board.gameBoard(Point(5, 4)).toString == "Knight(w)")
        //forward right
        c.savePiecePoint(Point(6, 5))
        c.getSelectedPoint(Point(5, 3))
        assert(c.board.gameBoard(Point(5, 3)).toString == "Knight(b)")
        //backwards left
        c.savePiecePoint(Point(5, 4))
        c.getSelectedPoint(Point(4, 2))
        assert(c.board.gameBoard(Point(4, 2)).toString == "Knight(w)")
        //backwards right
        c.savePiecePoint(Point(5, 3))
        c.getSelectedPoint(Point(6, 5))
        assert(c.board.gameBoard(Point(6, 5)).toString == "Knight(b)")
      }

      "Blacks can move after whites" in {
        val c = new Controller()
        c.boardInit()

        c.savePiecePoint(Point(0, 1))
        c.getSelectedPoint(Point(0, 2))
        c.savePiecePoint(Point(1, 6))
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
        c.board.gameBoard += Point(1, 7) -> EmptyField(" ")
        c.board.gameBoard += Point(2, 7) -> EmptyField(" ")
        c.board.gameBoard += Point(3, 7) -> EmptyField(" ")

        c.savePiecePoint(Point(4, 0))
        c.getSelectedPoint(Point(0, 0))

        c.savePiecePoint(Point(4, 7))
        c.getSelectedPoint(Point(0, 7))

        assert(c.board.gameBoard(Point(2, 0)).toString == "King(w)")
        assert(c.board.gameBoard(Point(3, 0)).toString == "Rook(w)")

        assert(c.board.gameBoard(Point(2, 7)).toString == "King(b)")
        assert(c.board.gameBoard(Point(3, 7)).toString == "Rook(b)")
      }

      "Small rochade works when no pieces inbetween" in {
        val c = new Controller()
        c.boardInit()
        c.board.gameBoard += Point(5, 0) -> EmptyField(" ")
        c.board.gameBoard += Point(6, 0) -> EmptyField(" ")
        c.board.gameBoard += Point(5, 7) -> EmptyField(" ")
        c.board.gameBoard += Point(6, 7) -> EmptyField(" ")

        c.savePiecePoint(Point(4, 0))
        c.getSelectedPoint(Point(7, 0))

        c.savePiecePoint(Point(4, 7))
        c.getSelectedPoint(Point(7, 7))

        assert(c.board.gameBoard(Point(6, 0)).toString == "King(w)")
        assert(c.board.gameBoard(Point(5, 0)).toString == "Rook(w)")

        assert(c.board.gameBoard(Point(6, 7)).toString == "King(b)")
        assert(c.board.gameBoard(Point(5, 7)).toString == "Rook(b)")
      }

      "Whites win after taking black King" in {
        val c = new Controller()
        c.boardInit()
        c.board.gameBoard += Point(4, 6) -> Queen("w")

        c.savePiecePoint(Point(4, 6))
        c.getSelectedPoint(Point(4, 7))
        assert(c.topKingDead == true)
        assert(c.board.gameBoard(Point(4, 7)).toString == "Queen(w)")
        assert(c.board.gameBoard(Point(4, 6)).toString == "EmptyField( )")
      }

      "Blacks win after taking white King" in {
        val c = new Controller()
        c.boardInit()
        c.board.gameBoard += Point(4, 1) -> Queen("b")

        c.savePiecePoint(Point(4, 1))
        c.getSelectedPoint(Point(4, 0))
        assert(c.bottomKingDead == true)
        assert(c.board.gameBoard(Point(4, 0)).toString == "Queen(b)")
        assert(c.board.gameBoard(Point(4, 1)).toString == "EmptyField( )")
      }

      "White Queen is not on the board anymore" in {
        val c = new Controller()
        c.boardInit()
        c.board.gameBoard += Point(3, 1) -> Queen("b")

        c.savePiecePoint(Point(3, 1))
        c.getSelectedPoint(Point(3, 0))
        assert(!c.listPlayer1.contains(Queen("w")))
        assert(c.listKillPlayer1.contains(Queen("w")))
      }

      "Black Queen is not on the board anymore" in {
        val c = new Controller()
        c.boardInit()
        c.board.gameBoard += Point(3, 6) -> Queen("w")

        c.savePiecePoint(Point(3, 6))
        c.getSelectedPoint(Point(3, 7))
        assert(!c.listPlayer2.contains(Queen("b")))
        assert(c.listKillPlayer2.contains(Queen("b")))
      }

      "Pawn can remove opponent going top-right" in {
        val c = new Controller()
        c.boardInit()
        c.board.gameBoard += Point(3, 5) -> Pawn("w")

        c.savePiecePoint(Point(3, 5))
        c.getSelectedPoint(Point(4, 6))
        assert(c.listKillPlayer2.contains(Pawn("b")))
        assert(c.board.gameBoard(Point(4, 6)).toString == "Pawn(w)")
        assert(c.board.gameBoard(Point(3, 5)).toString == "EmptyField( )")
      }

      "Game reset works" in {
        val c = new Controller()
        c.boardInit()

        c.savePiecePoint(Point(0, 1))
        c.getSelectedPoint(Point(0, 3))
        c.savePiecePoint(Point(4, 1))
        c.getSelectedPoint(Point(4, 3))
        c.savePiecePoint(Point(3, 0))
        c.getSelectedPoint(Point(6, 3))
        assert(c.board.gameBoard(Point(6, 3)).toString == "Queen(w)")
        assert(c.board.gameBoard(Point(3, 0)).toString == "EmptyField( )")
        c.reset()
        assert(c.board.gameBoard(Point(6, 3)).toString == "EmptyField( )")
      }
    }
  }

  "Controller" should {
    "return false" when {
      "Pawn moves 2 space forward after moving 1 space" in {
        val c = new Controller()
        c.boardInit()

        c.savePiecePoint(Point(0, 1))
        c.getSelectedPoint(Point(0, 2))
        assert(c.board.gameBoard(Point(0, 2)).toString == "Pawn(w)")
        c.savePiecePoint(Point(0, 6))
        c.getSelectedPoint(Point(0, 5))
        assert(c.board.gameBoard(Point(0, 5)).toString == "Pawn(b)")
        c.savePiecePoint(Point(0, 2))
        c.getSelectedPoint(Point(0, 4))
        assert(c.board.gameBoard(Point(0, 4)).toString != "Pawn(w)")
      }

      "Pawn moves 1 space forward blocked by opponent" in {
        val c = new Controller()
        c.boardInit()
        c.board.gameBoard(Point(0, 5)) -> Pawn("w")

        c.savePiecePoint(Point(0, 5))
        c.getSelectedPoint(Point(0, 6))
        assert(c.board.gameBoard(Point(0, 6)).toString != "Pawn(w)")
      }

      "Queem moves 1 space forward blocked by own Piece" in {
        val c = new Controller()
        c.boardInit()

        c.savePiecePoint(Point(3, 0))
        c.getSelectedPoint(Point(3, 1))
        assert(c.board.gameBoard(Point(3, 1)).toString != "Queen(w)")
      }

      "Big rochade when pieces inbetween" in {
        val c = new Controller()
        c.boardInit()

        c.savePiecePoint(Point(4, 0))
        c.getSelectedPoint(Point(0, 0))

        c.savePiecePoint(Point(4, 7))
        c.getSelectedPoint(Point(0, 7))

        assert(c.board.gameBoard(Point(2, 0)).toString != "King(w)")
        assert(c.board.gameBoard(Point(3, 0)).toString != "Rook(w)")

        assert(c.board.gameBoard(Point(2, 7)).toString != "King(b)")
        assert(c.board.gameBoard(Point(3, 7)).toString != "Rook(b)")
      }

      "Small rochade when pieces inbetween" in {
        val c = new Controller()
        c.boardInit()

        c.savePiecePoint(Point(4, 0))
        c.getSelectedPoint(Point(7, 0))

        c.savePiecePoint(Point(4, 7))
        c.getSelectedPoint(Point(7, 7))

        assert(c.board.gameBoard(Point(6, 0)).toString != "King(w)")
        assert(c.board.gameBoard(Point(5, 0)).toString != "Rook(w)")

        assert(c.board.gameBoard(Point(6, 7)).toString != "King(b)")
        assert(c.board.gameBoard(Point(5, 7)).toString != "Rook(b)")
      }

      "Queen has own piece between her and destination" in {
        val c = new Controller()
        c.boardInit()

        c.savePiecePoint(Point(3, 0))
        c.getSelectedPoint(Point(3, 4))
        assert(c.board.gameBoard(Point(3, 4)).toString != "Queen(w)")
        c.savePiecePoint(Point(3, 0))
        c.getSelectedPoint(Point(7, 4))
        assert(c.board.gameBoard(Point(7, 4)).toString != "Queen(w)")
        c.savePiecePoint(Point(3, 0))
        c.getSelectedPoint(Point(0, 3))
        assert(c.board.gameBoard(Point(0, 3)).toString != "Queen(w)")
      }

      "Rook has own piece between it and destination" in {
        val c = new Controller()
        c.boardInit()

        c.savePiecePoint(Point(0, 0))
        c.getSelectedPoint(Point(0, 4))
        assert(c.board.gameBoard(Point(0, 4)).toString != "Rook(w)")
        c.savePiecePoint(Point(0, 7))
        c.getSelectedPoint(Point(0, 3))
        assert(c.board.gameBoard(Point(7, 4)).toString != "Rook(b)")
      }

      "Bishop has own piece between it and destination" in {
        val c = new Controller()
        c.boardInit()

        c.savePiecePoint(Point(2, 0))
        c.getSelectedPoint(Point(4, 2))
        assert(c.board.gameBoard(Point(4, 2)).toString != "Bishop(w)")
        c.savePiecePoint(Point(2, 0))
        c.getSelectedPoint(Point(0, 2))
        assert(c.board.gameBoard(Point(0, 2)).toString != "Bishop(w)")
      }
    }
  }
}
