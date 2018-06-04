package de.htwg.se.ChinaSchach.model

trait Piece {

  //TODO: something
  def promotable: String
  def side: String

//  def setSide(side: String) : Unit = this.side = side
//
//  def setPromotable(promotable: String) : Unit = this.promotable = promotable

  def getSide() : String = side

  def getPromotable() : String = promotable

  def checkValidPoss(poss: (Int, Int)): Boolean = {
    var isValid: Boolean = false
    if (poss._1 >= 0 && poss._1 <= 8 && poss._2 >= 0 && poss._2 <= 8) {
      isValid = true
    }
    isValid
  }

  def movesAllowed(source: (Int, Int), destination: (Int, Int), possibleMoves: List[(Int, Int)]): Boolean = {
    if (checkValidPoss(destination)) {
      for (x <- possibleMoves) {
        val checkMove = (source._1 + x._1) (source._2 + x._2)
        if (destination == checkMove) {
          true
        }
      }
    }
    false
  }
}
