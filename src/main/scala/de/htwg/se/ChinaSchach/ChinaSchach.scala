package de.htwg.se.ChinaSchach

import de.htwg.se.ChinaSchach.controller.Controller
import com.google.inject.AbstractModule
import com.google.inject.Guice
import com.google.inject.Inject
import com.google.inject.Injector

object ChinaSchach {
  def main(args: Array[String]): Unit = {
    val injector = Guice.createInjector(new SchachModule)
    val controller = new Controller()
    controller.controllerInit()
  }
}