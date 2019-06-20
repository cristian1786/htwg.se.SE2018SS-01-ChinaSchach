package de.htwg.se.ChinaSchach.model

import de.htwg.se.ChinaSchach.util.Point

import scala.collection.mutable.Map

class Board() {

  val MAX_NUMBER = 8
  var gameBoard = Map.empty[Point, Piece]
  var round = 0

  // initialize map with pieces and empty fields
  def go(): Unit = {
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
    for (x <- 0 to 7) {
      gameBoard += Point(x, MAX_NUMBER - 6) -> EmptyField(" ")
      gameBoard += Point(x, MAX_NUMBER - 3) -> EmptyField(" ")
      gameBoard += Point(x, MAX_NUMBER - 4) -> EmptyField(" ")
      gameBoard += Point(x, MAX_NUMBER - 5) -> EmptyField(" ")
    }
  }

  //place Pawns
  def placePawn(y: Int, side: String): Unit = {
    for (x <- 0 to 7) {
      gameBoard += Point(x, y) -> Pawn(side)
    }
  }

  //place Bishop
  def placeBishop(y: Int, side: String): Unit = {
    gameBoard += Point(MAX_NUMBER - 6, y) -> Bishop(side)
    gameBoard += Point(MAX_NUMBER - 3, y) -> Bishop(side)
  }

  //place King
  def placeKing(y: Int, side: String): Unit = {
    gameBoard += Point(MAX_NUMBER - 4, y) -> King(side)
  }

  //place Queen
  def placeQueen(y: Int, side: String): Unit = {
    gameBoard += Point(MAX_NUMBER - 5, y) -> Queen(side)
  }

  //place Knight
  def placeKnight(y: Int, side: String): Unit = {
    gameBoard += Point(MAX_NUMBER - 7, y) -> Knight(side)
    gameBoard += Point(MAX_NUMBER - 2, y) -> Knight(side)
  }

  //place Rook
  def placeRook(y: Int, side: String): Unit = {
    gameBoard += Point(MAX_NUMBER - 1, y) -> Rook(side)
    gameBoard += Point(MAX_NUMBER - 8, y) -> Rook(side)
  }

  def deletePiece(field: (Int, Int)): Unit = {
    gameBoard += Point(field._1, field._2) -> EmptyField(" ")
  }
}