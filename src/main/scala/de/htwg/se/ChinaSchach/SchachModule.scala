package de.htwg.se.ChinaSchach

import com.google.inject.AbstractModule
import de.htwg.se.ChinaSchach.controller.{Controller, ControllerInterface}
import de.htwg.se.ChinaSchach.model.{Piece, Queen}
import net.codingwell.scalaguice.ScalaModule


class SchachModule extends AbstractModule with ScalaModule {

  override def configure(): Unit = {


    //bind[Piece].to[Queen]
    bind[ControllerInterface].to[Controller]

  }

}
