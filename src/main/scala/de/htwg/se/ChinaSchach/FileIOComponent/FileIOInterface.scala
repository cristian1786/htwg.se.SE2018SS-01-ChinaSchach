package de.htwg.se.ChinaSchach.FileIOComponent

import de.htwg.se.ChinaSchach.model._
import de.htwg.se.ChinaSchach.controller.Controller

trait FileIOInterface {

  def save(controller: Controller)
  def load(controller: Controller)
}
