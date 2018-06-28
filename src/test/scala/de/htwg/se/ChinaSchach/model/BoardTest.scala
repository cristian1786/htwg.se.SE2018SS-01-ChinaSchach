package de.htwg.se.ChinaSchach.model

import de.htwg.se.ChinaSchach.util.Point
import org.scalatest.{ Matchers, WordSpec }

class BoardTest extends WordSpec with Matchers {
  "A Board Movement should contain the figures" in {
    val board = new Board
    board.go()

    board.gameBoard(Point(0, 0)) shouldBe Rook("w")
    board.gameBoard(Point(7, 0)) shouldBe Rook("w")
    board.gameBoard(Point(0, 7)) shouldBe Rook("b")
    board.gameBoard(Point(7, 7)) shouldBe Rook("b")

    board.gameBoard(Point(1, 0)) shouldBe Knight("w")
    board.gameBoard(Point(6, 0)) shouldBe Knight("w")
    board.gameBoard(Point(1, 7)) shouldBe Knight("b")
    board.gameBoard(Point(6, 7)) shouldBe Knight("b")

    board.gameBoard(Point(2, 0)) shouldBe Bishop("w")
    board.gameBoard(Point(5, 0)) shouldBe Bishop("w")
    board.gameBoard(Point(2, 7)) shouldBe Bishop("b")
    board.gameBoard(Point(5, 7)) shouldBe Bishop("b")

    board.gameBoard(Point(3, 0)) shouldBe Queen("w")
    board.gameBoard(Point(3, 7)) shouldBe Queen("b")

    board.gameBoard(Point(4, 0)) shouldBe King("w")
    board.gameBoard(Point(4, 7)) shouldBe King("b")

    board.gameBoard(Point(0, 1)) shouldBe Pawn("w")
    board.gameBoard(Point(1, 1)) shouldBe Pawn("w")
    board.gameBoard(Point(2, 1)) shouldBe Pawn("w")
    board.gameBoard(Point(3, 1)) shouldBe Pawn("w")
    board.gameBoard(Point(4, 1)) shouldBe Pawn("w")
    board.gameBoard(Point(5, 1)) shouldBe Pawn("w")
    board.gameBoard(Point(6, 1)) shouldBe Pawn("w")
    board.gameBoard(Point(7, 1)) shouldBe Pawn("w")

    board.gameBoard(Point(0, 6)) shouldBe Pawn("b")
    board.gameBoard(Point(1, 6)) shouldBe Pawn("b")
    board.gameBoard(Point(2, 6)) shouldBe Pawn("b")
    board.gameBoard(Point(3, 6)) shouldBe Pawn("b")
    board.gameBoard(Point(4, 6)) shouldBe Pawn("b")
    board.gameBoard(Point(5, 6)) shouldBe Pawn("b")
    board.gameBoard(Point(6, 6)) shouldBe Pawn("b")
    board.gameBoard(Point(7, 6)) shouldBe Pawn("b")
  }
}
