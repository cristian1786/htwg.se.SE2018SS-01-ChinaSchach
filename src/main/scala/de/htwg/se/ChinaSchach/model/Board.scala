package de.htwg.se.ChinaSchach.model


class Board() {

//  val gameBoard = Map.newBuilder[Tuple2[Int, Int], Piece]
  val gameBoard = Array.ofDim[Piece](9,9)

  def go(): Unit = {
    placeBottomPieces()
    placeUpperPieces()
  }

  def placeUpperPieces(): Unit = {
    placeBishop(8, "top", "yes")
    placeGold(8, "top", "no")
    placeKing(8, "top", "no")
    placeLance(8, "top", "yes")
    placePawn(6, "top", "yes")
    placeRook(7, "top", "yes")
    placeSilver(7, "top", "yes")
  }

  def placeBottomPieces(): Unit = {
    placeBishop(0, "bottom", "yes")
    placeGold(0, "bottom", "no")
    placeKing(0, "bottom", "no")
    placeLance(0, "bottom", "yes")
    placePawn(2, "bottom", "yes")
    placeRook(1, "bottom", "yes")
    placeSilver(0, "bottom", "yes")
  }

  /*def setEmptyFields(): Unit = {
    for (x <- 3 until 6) {
      for (y <- 0 until 9) {
        gameBoard(x)(y) =  new Piece {
          override val promotable: String = _
          override val side: String = _
        }
      }
    }
  }*/

  def placePawn(y: Int, side: String, promotable: String): Unit = {
    for (x <- 0 until 9) {
      gameBoard(x)(y) =  Pawn(side, promotable)
    }
  }

  def placeBishop(y: Int, side: String, promotable: String): Unit = {
    if (side == "bottom") {
      gameBoard(1)(y) = Bishop(side, promotable)
    }
    else if (side == "top") {
      gameBoard(7)(y) = Bishop(side, promotable)
    }
  }

  def placeGold(y: Int, side: String, promotable: String): Unit = {
    gameBoard(2)(y) = Gold(side, promotable)
    gameBoard(6)(y) = Gold(side, promotable)
  }

  def placeKing(y: Int, side: String, promotable: String): Unit = {
    gameBoard(4)(y) = King(side,promotable)
  }

  def placeKnight(y: Int, side: String, promotable: String): Unit = {
    gameBoard(1)(y) = Knight(side, promotable)
    gameBoard(7)(y) = Knight(side, promotable)
  }

  def placeLance(y: Int, side: String, promotable: String): Unit = {
    gameBoard(0)(y) = Lance(side, promotable)
    gameBoard(8)(y) = Lance(side, promotable)
  }

  def placeRook(y: Int, side: String, promotable: String): Unit = {
    if (side == "bottom") {
      gameBoard(1)(y) = Bishop(side, promotable)
    }
    else if (side == "top") {
      gameBoard(7)(y) = Bishop(side, promotable)
    }
  }

  def placeSilver(y: Int, side: String, promotable: String): Unit = {
    gameBoard(3)(y) = Silver(side, promotable)
    gameBoard(5)(y) = Silver(side, promotable)
  }
}
