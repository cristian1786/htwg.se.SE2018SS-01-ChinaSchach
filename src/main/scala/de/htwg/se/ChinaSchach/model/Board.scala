package de.htwg.se.ChinaSchach.model

class Board() {
  val MAX_NUMBER = 8
//  val gameBoard = Map.newBuilder[Tuple2[Int, Int], Piece]
  val gameBoard = Array.ofDim[Piece](MAX_NUMBER,MAX_NUMBER)

  def go() : Unit = {
    placeBottomPieces()
    placeTopPieces()
    placeEmptyFields()
  }

  //places top pieces
  def placeTopPieces(): Unit = {
    placeBishop(MAX_NUMBER - 2, "top")
    placeKing(MAX_NUMBER - 1, "top", "no")
    placeKnight(MAX_NUMBER - 1, "top", "yes")
    placePawn(MAX_NUMBER - 3, "top", "yes")
    placeRook(MAX_NUMBER - 2, "top", "yes")
    placeQueen()

  }

  //places bottom pieces
  def placeBottomPieces(): Unit = {
    placeBishop(MAX_NUMBER - 8, "bottom", "yes")
    placeKing(MAX_NUMBER - 9, "bottom", "no")
    placePawn(MAX_NUMBER - 7, "bottom", "yes")
    placeRook(MAX_NUMBER - 8, "bottom", "yes")
    placeKnight(MAX_NUMBER - 9, "bottom", "yes")
    placeQueen()
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
  def placeBishop(y: Int, side: String) : Unit = {
    if (side == "bottom") {
      gameBoard(MAX_NUMBER - 8)(y) = Bishop(side)
    }
    else if (side == "top") {
      gameBoard(MAX_NUMBER -2)(y) = Bishop(side)
    }
  }

  //place King
  def placeKing(y: Int, side: String): Unit = {
    gameBoard(MAX_NUMBER - 5)(y) = King(side)
  }

  //place Queen
  def placeKing(y: Int, side: String): Unit = {
    gameBoard(MAX_NUMBER - 5)(y) = King(side)
  }

  //place Knight
  def placeKnight(y: Int, side: String): Unit = {
    gameBoard(MAX_NUMBER - 8)(y) = Knight(side)
    gameBoard(MAX_NUMBER - 2)(y) = Knight(side)
  }

  //place Rook
  def placeRook(y: Int, side: String): Unit = {
    if (side == "bottom") {
      gameBoard(MAX_NUMBER - 2)(y) = Rook(side)
    }
    else if (side == "top") {
      gameBoard(MAX_NUMBER - 8)(y) = Rook(side)
    }
  }

  //returns the value at the respective field-point
  def get(field: (Int, Int)): Piece = {
    gameBoard(field._1)(field._2)
  }

  def deletePiece(field: (Int, Int)): Unit = {
    gameBoard(field._1)(field._2) = EmptyField(" ", " ")
  }
}
