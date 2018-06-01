package de.htwg.se.ChinaSchach

import de.htwg.se.ChinaSchach.aview.Tui
import de.htwg.se.ChinaSchach.controller.Controller
import de.htwg.se.ChinaSchach.model.Board

object ChinaSchach {
  def main(args: Array[String]): Unit = {
    println("hallo")
    var controller = new Controller
    var board = new Board
    var tui = new Tui(controller, board)
    tui.outputField()
  }
}