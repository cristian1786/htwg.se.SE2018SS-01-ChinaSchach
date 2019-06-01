package de.htwg.se.ChinaSchach.controller

import de.htwg.se.ChinaSchach.model.{Board, Piece}
import de.htwg.se.ChinaSchach.util.Point

trait UndoManager {
  var undoStack: List[((Point, Piece), (Point, Piece))]
  var redoStack: List[((Point, Piece), (Point, Piece))]

  def undoMove: Unit

  def redoMove: Unit
}

class UndoManagerImpl(var board: Board) extends UndoManager {
  var undoStack: List[((Point, Piece), (Point, Piece))] = Nil
  var redoStack: List[((Point, Piece), (Point, Piece))] = Nil

  def undoMove: Unit = {
    val cells: ((Point, Piece), (Point, Piece)) = undoStack.head
    undoStack = undoStack.tail
    redoStack = (cells._2, cells._1) :: redoStack

    board.gameBoard += cells._1._1 -> cells._1._2
    board.gameBoard += cells._2._1 -> cells._2._2

  }

  def redoMove: Unit = {
    val cells: ((Point, Piece), (Point, Piece)) = redoStack.head
    redoStack = redoStack.tail
    undoStack = (cells._2, cells._1) :: undoStack

    board.gameBoard += cells._1._1 -> cells._1._2
    board.gameBoard += cells._2._1 -> cells._2._2
  }
}

