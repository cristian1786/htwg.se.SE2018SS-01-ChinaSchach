package de.htwg.se.ChinaSchach

import de.htwg.se.ChinaSchach.aview.Tui
import de.htwg.se.ChinaSchach.controller.Controller
import de.htwg.se.ChinaSchach.model.Board
//import scala.util.control.Breaks._

object ChinaSchach {
  def main(args: Array[String]): Unit = {
    val controller = new Controller("Peter", "Opa")
    controller.boardInit()
  }
}