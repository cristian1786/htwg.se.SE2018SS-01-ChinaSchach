package de.htwg.se.ChinaSchach.controller

import de.htwg.se.ChinaSchach.model.Piece
import de.htwg.se.ChinaSchach.observer.Observable
import de.htwg.se.ChinaSchach.util.Point

trait ControllerInterface extends Observable{

  def controllerInit(): Unit
  def tuiInit() : Unit
  def guiInit(): Unit
  def boardInit(): Unit
  def playerInit(): Unit
  def playerTurn2(point: Point): Boolean
  def playerTurn1(point: Point): Boolean
  def getSelectedPoint(point: Point): Unit
  def savePiecePoint(piece: Piece, point: Point): Unit
  def ifEnemy(source: Point, destination: Point): Unit
  def movePiece(source: Point, destination: Point): Unit
  def rowOneEight(destination: Point): Unit
  def pawnReplace(destination: Point, list: List[(Int, Int)]): Unit
  def gameWon(destination: Point): Unit
  def reset(): Unit
  def exit(): Unit
  def setRound(): Unit
  def doRochade(sourcePoint: Point, point: Point): Unit
  def bigRochade(source: Point, point: Point): Unit
  def smallRochade(source: Point, point: Point): Unit
  def bigRochadeMove(source: Point, point: Point): Unit
  def smallRochadeMove(source: Point, point: Point): Unit

}
