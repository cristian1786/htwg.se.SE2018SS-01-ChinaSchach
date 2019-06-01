package de.htwg.se.ChinaSchach

import de.htwg.se.ChinaSchach.controller.ControllerInterface
import com.google.inject.Guice

object ChinaSchach {

  val injector = Guice.createInjector(new SchachModule)
  var controller = injector.getInstance(classOf[ControllerInterface])

  def main(args: Array[String]): Unit = {
    controller.controllerInit()
  }
}