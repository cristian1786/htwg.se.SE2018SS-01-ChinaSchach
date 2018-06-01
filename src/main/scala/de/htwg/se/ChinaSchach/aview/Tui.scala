package de.htwg.se.ChinaSchach.aview

import de.htwg.se.ChinaSchach.controller.Controller
import de.htwg.se.ChinaSchach.model.Board
import scala.runtime.ScalaRunTime._

class Tui(controller: Controller, board: Board) {
  //TODO: implement TUI for ChinaSchach

  def outputField(): Unit = {
    var counter = 0
    for (x <- 0 to 8) {
      for (y <- 0 to 8) {
        if (board.get(x, y) != null) {
          println("Field(" + x + ", " + y + ") contains " + board.get(x, y))
          counter += 1
        }
      }
    }
    println(" ")
    println(" ")
    println(counter)
  }

  //TODO: which figures are left on the field


  //TODO: killed figures


  //TODO: which Round
}
