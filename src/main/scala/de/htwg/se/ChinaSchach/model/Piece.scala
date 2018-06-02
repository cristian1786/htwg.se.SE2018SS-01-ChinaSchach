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

  def checkValidPoss(poss: (Int, Int)): Boolean = {
    var isValid: Boolean = false
    if (poss._1 >= 0 && poss._1 <= 9 && poss._2 >= 0 && poss._2 <= 9) {
      isValid = true
    }
    isValid
  }
}
