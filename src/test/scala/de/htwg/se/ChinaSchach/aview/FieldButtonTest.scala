package de.htwg.se.ChinaSchach.aview

import de.htwg.se.ChinaSchach.util.Point
import org.scalatest.WordSpec
import org.scalatest.Matchers

class FieldButtonTest extends WordSpec with Matchers {

  "A FieldButton" should {
    val testButton = new FieldButton(Point(6,8))
    "should have a Point" in{
      assert(testButton.getPoint() == Point(6, 8))
    }
  }
}
