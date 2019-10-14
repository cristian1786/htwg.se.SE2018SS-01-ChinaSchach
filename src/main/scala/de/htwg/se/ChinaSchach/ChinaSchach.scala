package de.htwg.se.ChinaSchach

import com.google.inject.Guice
import de.htwg.se.ChinaSchach.aview.{Gui, Tui}
import de.htwg.se.ChinaSchach.controller.controllerComponent.ControllerInterface
//import de.htwg.se.ChinaSchach.controller.controllerComponent.controllerImpl.Controller

object ChinaSchach {

  val injector = Guice.createInjector(new SchachModule)
  var controller = injector.getInstance(classOf[ControllerInterface])

  //controller.controllerInit()
  //
  //val tui = Tui(controller)
  //controller.controllerInit()
  def main(args: Array[String]): Unit = {
    /*var input = ""
    do {
      input = scala.io.StdIn.readLine()
      tui.readInput(input)

    } while (input != "exit")*/
  }
}