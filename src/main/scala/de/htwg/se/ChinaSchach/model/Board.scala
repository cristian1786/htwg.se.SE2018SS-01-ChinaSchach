package de.htwg.se.ChinaSchach.model


class Board() {
  val MAX_NUMBER = 9
//  val gameBoard = Map.newBuilder[Tuple2[Int, Int], Piece]
  val gameBoard = Array.ofDim[Piece](MAX_NUMBER,MAX_NUMBER)

  def go() : Unit = {
    placeBottomPieces()
    placeTopPieces()
  }

  //places top pieces
  def placeTopPieces(): Unit = {
    placeBishop(7, "top", "yes")
    placeGold(8, "top", "no")
    placeKing(8, "top", "no")
    placeLance(8, "top", "yes")
    placePawn(6, "top", "yes")
    placeRook(7, "top", "yes")
    placeSilver(8, "top", "yes")
    placeKnight(8, "top", "yes")
  }

  //places bottom pieces
  def placeBottomPieces(): Unit = {
    placeBishop(1, "bottom", "yes")
    placeGold(0, "bottom", "no")
    placeKing(0, "bottom", "no")
    placeLance(0, "bottom", "yes")
    placePawn(2, "bottom", "yes")
    placeRook(1, "bottom", "yes")
    placeSilver(0, "bottom", "yes")
    placeKnight(0, "bottom", "yes")
  }

  //place Pawns
  def placePawn(y: Int, side: String, promotable: String) : Unit = {
    for (x <- 0 until 9) {
      gameBoard(x)(y) =  Pawn(side, promotable)
    }
  }

  //place Bishop
  def placeBishop(y: Int, side: String, promotable: String) : Unit = {
    if (side == "bottom") {
      gameBoard(1)(y) = Bishop(side, promotable)
    }
    else if (side == "top") {
      gameBoard(7)(y) = Bishop(side, promotable)
    }
  }

  //place Gold
  def placeGold(y: Int, side: String, promotable: String): Unit = {
    gameBoard(2)(y) = Gold(side, promotable)
    gameBoard(6)(y) = Gold(side, promotable)
  }

  //place King
  def placeKing(y: Int, side: String, promotable: String): Unit = {
    gameBoard(4)(y) = King(side,promotable)
  }

  //place Knight
  def placeKnight(y: Int, side: String, promotable: String): Unit = {
    gameBoard(1)(y) = Knight(side, promotable)
    gameBoard(7)(y) = Knight(side, promotable)
  }

  //place Lance
  def placeLance(y: Int, side: String, promotable: String): Unit = {
    gameBoard(0)(y) = Lance(side, promotable)
    gameBoard(8)(y) = Lance(side, promotable)
  }

  //place Rook
  def placeRook(y: Int, side: String, promotable: String): Unit = {
    if (side == "bottom") {
      gameBoard(7)(y) = Rook(side, promotable)
    }
    else if (side == "top") {
      gameBoard(1)(y) = Rook(side, promotable)
    }
  }

  //place Silver
  def placeSilver(y: Int, side: String, promotable: String): Unit = {
    gameBoard(3)(y) = Silver(side, promotable)
    gameBoard(5)(y) = Silver(side, promotable)
  }

  //returns the value at the respective field-point
  def get(field: Tuple2[Int, Int]): Piece = {
    gameBoard(field._1)(field._2)
  }
}
