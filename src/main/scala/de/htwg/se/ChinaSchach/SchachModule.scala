package de.htwg.se.ChinaSchach

import com.google.inject.AbstractModule
import de.htwg.se.ChinaSchach.controller.{Controller, ControllerInterface}
import de.htwg.se.ChinaSchach.model._
import net.codingwell.scalaguice.ScalaModule


class SchachModule extends AbstractModule with ScalaModule {

  override def configure(): Unit = {


    bind[Piece].to[Bishop]
    bind[Piece].to[EmptyField]
    bind[Piece].to[King]
    bind[Piece].to[Knight]
    bind[Piece].to[Pawn]
    bind[Piece].to[Queen]
    bind[Piece].to[Rook]
    bind[ControllerInterface].to[Controller]

  }

}
