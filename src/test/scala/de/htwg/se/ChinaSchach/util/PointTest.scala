package de.htwg.se.ChinaSchach.util

import org.scalatest.WordSpec
import org.scalatest.Matchers

class PointTest extends WordSpec with Matchers {

  "A Point" should {
    val testingPoint = Point(6,8)
    "should have a X" in{
      assert(testingPoint.getX() == 6)
    }
    "should have a Y" in{
      assert(testingPoint.getY() == 8)
    }
  }
}
