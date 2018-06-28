package de.htwg.se.ChinaSchach

import de.htwg.se.ChinaSchach.controller.Controller

object ChinaSchach {
  def main(args: Array[String]): Unit = {
    val controller = new Controller()
    controller.controllerInit()
  }
}