package de.htwg.se.ChinaSchach.aview

import de.htwg.se.ChinaSchach.util.Point
import scala.swing.Button

case class FieldButton(point: Point) extends Button {

  def getPoint() : Point = {
    point
  }
}
