package de.htwg.se.ChinaSchach

import de.htwg.se.ChinaSchach.controller.ControllerInterface
import com.google.inject.Guice

object ChinaSchach {
  def main(args: Array[String]): Unit = {
    val injector = Guice.createInjector(new SchachModule)
    val controller = injector.getInstance(classOf[ControllerInterface])
    controller.controllerInit()
  }
}