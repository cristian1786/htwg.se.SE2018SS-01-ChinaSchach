package de.htwg.se.ChinaSchach.FileIOComponent

import de.htwg.se.ChinaSchach.model._
import de.htwg.se.ChinaSchach.controller.Controller

trait FileIOInterface {

  def save(controller: Controller, file: String)
  def load(controller: Controller, file: String)
}
