package de.htwg.se.ChinaSchach.model

import de.htwg.se.ChinaSchach.util.Point
import org.scalatest.{ Matchers, WordSpec }

class EmptyFieldTest extends WordSpec with Matchers {
  "A EmptyField" should {
    "return true" when {
      "it moves moves in every direction" in {
        val board = new Board
        board.go()
        val sourcePoint = Point(3, 3)

        assert(board.gameBoard(sourcePoint).toString == "EmptyField( )")
      }
    }
  }

  "A EmptyField" should {
    "return false" when {
      "it moves moves in every direction" in {
        val board = new Board
        board.go()
        val sourcePoint = Point(0, 0)

        assert(board.gameBoard(sourcePoint).toString != "EmptyField( )")
      }
    }
  }
}
