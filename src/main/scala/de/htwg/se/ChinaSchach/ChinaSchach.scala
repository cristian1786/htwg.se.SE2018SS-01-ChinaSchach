package de.htwg.se.ChinaSchach

import de.htwg.se.ChinaSchach.controller.Controller
import de.htwg.se.ChinaSchach.model._
import de.htwg.se.ChinaSchach.aview._


object ChinaSchach {
  def main(args: Array[String]): Unit = {
    var board = new Board
    val controller = new Controller("Peter", "Hans", board)
    val gui = new Gui(controller, board)
//    val tui = new Tui(board)
    controller.boardInit()
    gui.guiStart()
  }
}