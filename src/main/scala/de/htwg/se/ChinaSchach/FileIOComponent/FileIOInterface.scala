package de.htwg.se.ChinaSchach.FileIOComponent

import de.htwg.se.ChinaSchach.model._
import de.htwg.se.ChinaSchach.controller.Controller

trait FileIOInterface {

  def save(gameboard: Board, controller: Controller)
  def load()
}
