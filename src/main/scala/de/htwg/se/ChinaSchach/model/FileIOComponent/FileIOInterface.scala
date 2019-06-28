package de.htwg.se.ChinaSchach.model.FileIOComponent

import de.htwg.se.ChinaSchach.controller.controllerComponent.controllerImpl.Controller

trait FileIOInterface {

  def save(controller: Controller, file: String)

  def load(controller: Controller, file: String)
}
