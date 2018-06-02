package de.htwg.se.ChinaSchach.model

class Board() {
  val MAX_NUMBER = 9
//  val gameBoard = Map.newBuilder[Tuple2[Int, Int], Piece]
  val gameBoard = Array.ofDim[Piece](MAX_NUMBER,MAX_NUMBER)

  def go() : Unit = {
    placeBottomPieces()
    placeTopPieces()
    placeEmptyFields()
  }

  //places top pieces
  def placeTopPieces(): Unit = {
    placeBishop(MAX_NUMBER - 2, "top", "yes")
    placeGold(MAX_NUMBER - 1, "top", "no")
    placeKing(MAX_NUMBER - 1, "top", "no")
    placeLance(MAX_NUMBER - 1, "top", "yes")
    placePawn(MAX_NUMBER - 3, "top", "yes")
    placeRook(MAX_NUMBER - 2, "top", "yes")
    placeSilver(MAX_NUMBER - 1, "top", "yes")
    placeKnight(MAX_NUMBER - 1, "top", "yes")
  }

  //places bottom pieces
  def placeBottomPieces(): Unit = {
    placeBishop(MAX_NUMBER - 8, "bottom", "yes")
    placeGold(MAX_NUMBER - 9, "bottom", "no")
    placeKing(MAX_NUMBER - 9, "bottom", "no")
    placeLance(MAX_NUMBER - 9, "bottom", "yes")
    placePawn(MAX_NUMBER - 7, "bottom", "yes")
    placeRook(MAX_NUMBER - 8, "bottom", "yes")
    placeSilver(MAX_NUMBER - 9, "bottom", "yes")
    placeKnight(MAX_NUMBER - 9, "bottom", "yes")
  }

  //initialize empty fields
  def placeEmptyFields(): Unit = {
    for (x <- 0 to 8) {
      for (y <- 3 to 5) {
        gameBoard(x)(y) = EmptyField(" ", " ")
      }
    }
    for (x <- 2 to 6) {
      gameBoard(x)(MAX_NUMBER - 8) = EmptyField(" ", " ")
      gameBoard(x)(MAX_NUMBER - 2) = EmptyField(" ", " ")
    }
    gameBoard(MAX_NUMBER - 9)(MAX_NUMBER - 8) = EmptyField(" ", " ")
    gameBoard(MAX_NUMBER - 1)(MAX_NUMBER - 8) = EmptyField(" ", " ")

    gameBoard(MAX_NUMBER - 9)(MAX_NUMBER - 2) = EmptyField(" ", " ")
    gameBoard(MAX_NUMBER - 1)(MAX_NUMBER - 2) = EmptyField(" ", " ")

  }

  //place Pawns
  def placePawn(y: Int, side: String, promotable: String) : Unit = {
    for (x <- 0 to 8) {
      gameBoard(x)(y) = Pawn(side, promotable)
    }
  }

  //place Bishop
  def placeBishop(y: Int, side: String, promotable: String) : Unit = {
    if (side == "bottom") {
      gameBoard(MAX_NUMBER - 8)(y) = Bishop(side, promotable)
    }
    else if (side == "top") {
      gameBoard(MAX_NUMBER -2)(y) = Bishop(side, promotable)
    }
  }

  //place Gold
  def placeGold(y: Int, side: String, promotable: String): Unit = {
    gameBoard(MAX_NUMBER - 7)(y) = Gold(side, promotable)
    gameBoard(MAX_NUMBER -3)(y) = Gold(side, promotable)
  }

  //place King
  def placeKing(y: Int, side: String, promotable: String): Unit = {
    gameBoard(MAX_NUMBER - 5)(y) = King(side,promotable)
  }

  //place Knight
  def placeKnight(y: Int, side: String, promotable: String): Unit = {
    gameBoard(MAX_NUMBER - 8)(y) = Knight(side, promotable)
    gameBoard(MAX_NUMBER - 2)(y) = Knight(side, promotable)
  }

  //place Lance
  def placeLance(y: Int, side: String, promotable: String): Unit = {
    gameBoard(MAX_NUMBER - 9)(y) = Lance(side, promotable)
    gameBoard(MAX_NUMBER - 1)(y) = Lance(side, promotable)
  }

  //place Rook
  def placeRook(y: Int, side: String, promotable: String): Unit = {
    if (side == "bottom") {
      gameBoard(MAX_NUMBER - 2)(y) = Rook(side, promotable)
    }
    else if (side == "top") {
      gameBoard(MAX_NUMBER - 8)(y) = Rook(side, promotable)
    }
  }

  //place Silver
  def placeSilver(y: Int, side: String, promotable: String): Unit = {
    gameBoard(MAX_NUMBER - 6)(y) = Silver(side, promotable)
    gameBoard(MAX_NUMBER - 4)(y) = Silver(side, promotable)
  }

  //returns the value at the respective field-point
  def get(field: (Int, Int)): Piece = {
    gameBoard(field._1)(field._2)
  }

  def deletePiece(field: (Int, Int)): Unit = {
    gameBoard(field._1)(field._2) = EmptyField("empty", "empty")
  }
}
