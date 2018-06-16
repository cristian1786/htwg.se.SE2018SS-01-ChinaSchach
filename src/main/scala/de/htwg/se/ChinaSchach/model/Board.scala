package de.htwg.se.ChinaSchach.model

class Board() {
  val MAX_NUMBER = 8
//  val gameBoard = Map.newBuilder[Tuple2[Int, Int], Piece]
  val gameBoard = Array.ofDim[Piece](MAX_NUMBER,MAX_NUMBER)

  def go() : Unit = {
    placeBlackPieces()
    placeWhitePieces()
    placeEmptyFields()
  }

  //places top pieces
  def placeBlackPieces(): Unit = {
    placeBishop(MAX_NUMBER - 1, "b")
    placeKing(MAX_NUMBER - 1, "b")
    placeKnight(MAX_NUMBER - 1, "b")
    placePawn(MAX_NUMBER - 2, "b")
    placeRook(MAX_NUMBER - 1, "b")
    placeQueen(MAX_NUMBER - 1, "b")

  }

  //places bottom pieces
  def placeWhitePieces(): Unit = {
    placeBishop(MAX_NUMBER - 8, "w")
    placeKing(MAX_NUMBER - 8, "w")
    placePawn(MAX_NUMBER - 7, "w")
    placeRook(MAX_NUMBER - 8, "w")
    placeKnight(MAX_NUMBER - 8, "w")
    placeQueen(MAX_NUMBER - 8, "w")
  }

  //initialize empty fields
  def placeEmptyFields(): Unit = {
    for (x <- 2 to 5) {
      gameBoard(x)(MAX_NUMBER - 7) = EmptyField(" ")
      gameBoard(x)(MAX_NUMBER - 3) = EmptyField(" ")
    }
  }

  //place Pawns
  def placePawn(y: Int, side: String) : Unit = {
    for (x <- 0 to 7) {
      gameBoard(x)(y) = Pawn(side)
    }
  }

  //place Bishop
  def placeBishop(y: Int, side: String) : Unit = {
    gameBoard(MAX_NUMBER - 6)(y) = Bishop(side)
    gameBoard(MAX_NUMBER - 3)(y) = Bishop(side)
  }

  //place King
  def placeKing(y: Int, side: String): Unit = {
    gameBoard(MAX_NUMBER - 4)(y) = King(side)
  }

  //place Queen
  def placeQueen(y: Int, side: String): Unit = {
    gameBoard(MAX_NUMBER - 5)(y) = Queen(side)
  }

  //place Knight
  def placeKnight(y: Int, side: String): Unit = {
    gameBoard(MAX_NUMBER - 7)(y) = Knight(side)
    gameBoard(MAX_NUMBER - 1)(y) = Knight(side)
  }

  //place Rook
  def placeRook(y: Int, side: String): Unit = {
    gameBoard(MAX_NUMBER)(y) = Rook(side)
    gameBoard(MAX_NUMBER - 8)(y) = Rook(side)
  }

  //returns the value at the respective field-point
  def get(field: (Int, Int)): Piece = {
    gameBoard(field._1)(field._2)
  }

  def deletePiece(field: (Int, Int)): Unit = {
    gameBoard(field._1)(field._2) = EmptyField(" ")
  }
}