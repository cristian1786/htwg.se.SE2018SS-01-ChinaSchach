package de.htwg.se.ChinaSchach.aview

import de.htwg.se.ChinaSchach.controller.controllerComponent.ControllerInterface
import de.htwg.se.ChinaSchach.controller.controllerComponent.controllerImpl.Controller
import de.htwg.se.ChinaSchach.model.EmptyField
import de.htwg.se.ChinaSchach.observer.Observer
import de.htwg.se.ChinaSchach.util.Point

import scala.io.StdIn
import scala.xml.dtd.impl.PointedHedgeExp

//TODO: implement TUI
case class Tui(controller: Controller) extends Observer {
  controller.addObserver(this)

  var counter = 0

  // initialize TUI
  def go(): Unit = {
    println(drawGameboard2())

    println("The board uses Point-Coordinates (X, Y) to initialize each chessfield with a corresponding piece if existant.")
    println("Please select and move a Chesspiece by using 'p XY'.")
    println("Format: X = Integer, Y = Integer")
    println("It is possible to 'quit' and 'restart' anytime.\n")
    println("Select source point:")
  }
  // read user input
  def readInput(input: String): Unit = {
    input match {
      case "restart" =>
        restart()
      case "show" => println(drawGameboard2())
      case p if input.startsWith("p") && input.length == 4 =>
        if (controller.playerTurnCheck(Point(input.charAt(2).toString.toInt, input.charAt(3).toString.toInt)) && counter == 0) {
          counter += 1
          println("Choose destination:")
        } else if (controller.playerTurnCheck(Point(input.charAt(2).toString.toInt, input.charAt(3).toString.toInt)) && counter == 0) {
          counter += 1
          println("Select destination point:")
        } else if (counter % 2 != 0) {
          controller.getSelectedPoint(Point(input.charAt(2).toString.toInt, input.charAt(3).toString.toInt))
          if (controller.board.round % 2 == 0) {
            println("Round: " + controller.board.round + " Turn: player 1\n")
          } else {
            println("Round: " + controller.board.round + " Turn: player 2\n")
          }
          println("Select source point:")
        } else {
          println("Wrong selection! Its not your turn!")
        }
      case _ =>
        println("wrong input mate")
    }
  }

  def checkForWin(): Unit = {
    if (controller.bottomKingDead) {
      println("PLAYER 2 WON!")
      controller.reset()
    } else if (controller.topKingDead) {
      println("PLAYER 1 WON!")
      controller.reset()
    }
  }

  def restart(): Unit = {
    controller.reset()
  }

  override def update(): Unit = {
    //drawGameboard2()
    //checkForWin()
    //counter = 0
  }

  def drawGameboard2(): String = {
    val g = controller.board.gameBoard.values.toList
    val list = g.map { case EmptyField(" ") => "--"; case x => x }

    var s = ""
    s +=
    "SCHACH\n" +
      "=========================================================================================================================================================================\n" +
      "| 00: " + list(53) + "| 10: %-15s|" + list(1) + "20: %-15s|" + list(14) + "30: %-15s|" + list(62) + " 40: %-15s|" + list(35) + " 50: %-15s|" + list(15) + " 60: %-15s|" + list(57) + " 70: %-15s|" + list(10) + "\n" +
      "-------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
      "| 01: %-15s|" + list(38) + "11: %-15s|" + list(31) + " 21: %-15s|" + list(0) + " 31: %-15s|" + list(36) + " 41: %-15s|" + list(48) + " 51: %-15s|" + list(47) + " 61: %-15s|" + list(22) + " 71: %-15s|" + list(52) + "\n" +
      "-------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
      "| 02: %-15s|" + list(32) + " 12: %-15s|" + list(55) + " 22: %-15s|" + list(25) + " 32: %-15s|" + list(13) + " 42: %-15s|" + list(20) + " 52: %-15s|" + list(23) + " 62: %-15s|" + list(11) + " 72: %-15s|" + list(50) + "\n" +
      "-------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
      "| 03: %-15s|" + list(40) + " 13: %-15s|" + list(27) + " 23: %-15s|" + list(3) + " 33: %-15s|" + list(34) + " 43: %-15s|" + list(44) + " 53: %-15s|" + list(21) + " 63: %-15s|" + list(19) + " 73: %-15s|" + list(4) + "\n" +
      "-------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
      "| 04: %-15s|" + list(59) + " 14: %-15s|" + list(46) + " 24: %-15s|" + list(9) + " 34: %-15s|" + list(58) + " 44: %-15s|" + list(51) + " 54: %-15s|" + list(54) + " 64: %-15s|" + list(24) + " 74: %-15s|" + list(12) + "\n" +
      "-------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
      "| 05: %-15s|" + list(45) + " 15: %-15s|" + list(17) + " 25: %-15s|" + list(7) + " 35: %-15s|" + list(33) + " 45: %-15s|" + list(41) + " 55: %-15s|" + list(8) + " 65: %-15s|" + list(5) + " 75: %-15s|" + list(43) + "\n" +
      "-------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
      "| 06: %-15s|" + list(37) + " 16: %-15s|" + list(61) + " 26: %-15s|" + list(18) + " 36: %-15s|" + list(39) + " 46: %-15s|" + list(29) + " 56: %-15s|" + list(2) + " 66: %-15s|" + list(49) + " 76: %-15s|" + list(28) + "\n" +
      "-------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
      "| 07: %-15s|" + list(56) + " 17: %-15s|" + list(30) + " 27: %-15s|" + list(60) + " 37: %-15s|" + list(63) + " 47: %-15s|" + list(6) + " 57: %-15s|" + list(42) + " 67: %-15s|" + list(26) + " 77: %-15s|" + list(16) + "\n" +
      "=========================================================================================================================================================================\n"
    s
  }
}
