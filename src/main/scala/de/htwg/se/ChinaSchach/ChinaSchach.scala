package de.htwg.se.ChinaSchach

import com.google.inject.Guice
import de.htwg.se.ChinaSchach.controller.ControllerInterface

object ChinaSchach {

  val injector = Guice.createInjector(new SchachModule)
  var controller = injector.getInstance(classOf[ControllerInterface])

  def main(args: Array[String]): Unit = {
    controller.controllerInit()
  }
}