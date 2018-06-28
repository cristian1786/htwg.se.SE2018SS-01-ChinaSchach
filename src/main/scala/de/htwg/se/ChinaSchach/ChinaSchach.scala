package de.htwg.se.ChinaSchach

import de.htwg.se.ChinaSchach.controller.Controller
import de.htwg.se.ChinaSchach.model._
import de.htwg.se.ChinaSchach.aview._

object ChinaSchach {
  def main(args: Array[String]): Unit = {
    val controller = new Controller("Peter", "Hans")
    controller.controllorInit()
  }
}