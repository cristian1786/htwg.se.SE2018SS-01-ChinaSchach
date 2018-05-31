package de.htwg.se.ChinaSchach.model


class Board() {

  val gameBoard = Map.newBuilder[Tuple2[Int, Int], Piece]

  def go(): Unit = {
    placeBottomPieces()
    placeUpperPieces()
  }

  def placeUpperPieces(): Unit = {
    placeBishop(8, "top", "yes")
    placeGold(8, "no")
    placeKing(8, "no")
    placeLance(8, "yes")
    placePawn(8, "yes")
    placeRook(7, "top", "yes")
    placeSilver(7, "yes")
  }

  def placeBottomPieces(): Unit = {
    placeBishop(0, "top", "yes")
    placeGold(0, "no")
    placeKing(0, "no")
    placeLance(0, "yes")
    placePawn(2, "yes")
    placeRook(1, "top", "yes")
    placeSilver(7, "yes")
  }

  def placePawn(y: Int, promotable: String): Unit = {
    for (x <- 0 until 9) {
      gameBoard += (x, y) -> Pawn(promotable)
    }
  }

  def placeBishop(y: Int, side: String, promotable: String): Unit = {
    if (side == "bottom") {
      gameBoard += (1, y) -> Bishop(promotable)
    }
    else if (side == "top") {
      gameBoard += (7, y) -> Bishop(promotable)
    }
  }

  def placeGold(y: Int, promotable: String): Unit = {
    gameBoard += (2, y) -> Gold(promotable)
    gameBoard += (6, y) -> Gold(promotable)
  }

  def placeKing(y: Int, promotable: String): Unit = {
    gameBoard += (4, y) -> King(promotable)
  }

  def placeKnight(y: Int, promotable: String): Unit = {
    gameBoard += (1, y) -> Knight(promotable)
    gameBoard += (7, y) -> Knight(promotable)
  }

  def placeLance(y: Int, promotable: String): Unit = {
    gameBoard += (0, y) -> Lance(promotable)
    gameBoard += (8, y) -> Lance(promotable)
  }

  def placeRook(y: Int, side: String, promotable: String): Unit = {
    if (side == "bottom") {
      gameBoard += (7, y) -> Bishop(promotable)
    }
    else if (side == "top") {
      gameBoard += (1, y) -> Bishop(promotable)
    }
  }

  def placeSilver(y: Int, promotable: String): Unit = {
    gameBoard += (3, y) -> Silver(promotable)
    gameBoard += (5, y) -> Silver(promotable)
  }
}
