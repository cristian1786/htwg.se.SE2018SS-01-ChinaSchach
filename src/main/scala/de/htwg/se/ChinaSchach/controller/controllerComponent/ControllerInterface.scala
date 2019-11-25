package de.htwg.se.ChinaSchach.controller.controllerComponent

import de.htwg.se.ChinaSchach.controller.controllerComponent.controllerImpl.Controller
import de.htwg.se.ChinaSchach.observer.Observable
import de.htwg.se.ChinaSchach.util.Point
import play.api.libs.json.JsValue

import scala.swing.Publisher

trait ControllerInterface extends Publisher {

  //def controllerInit(): Unit

  def tuiInit(): Unit

  def guiInit(): Unit

  def boardInit(): Unit

  def playerInit(): Unit

  def playerTurnCheck(point: Point): Boolean

  def playerTurnCheckDest: Boolean

  def resetPlayerTurn: Unit

  def getPiece(point: Point): String

  def getSelectedPoint(point: Point): Unit

  def savePiecePoint(point: Point): Unit

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

  def drawGameboard(): String

  def boardToString(): Array[String]

  def gameToJson(): JsValue

}

import scala.swing.event.Event

class Changed extends Event