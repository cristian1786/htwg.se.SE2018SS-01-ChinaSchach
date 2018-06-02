package de.htwg.se.ChinaSchach.model

trait Piece {

  //TODO something
  val promotable: String
  val side: String

  def getSide() : String = {
    side
  }

  def getPromotable() : String = {
    promotable
  }
}
