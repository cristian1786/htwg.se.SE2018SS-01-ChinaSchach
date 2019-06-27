package de.htwg.se.ChinaSchach.aview

import de.htwg.se.ChinaSchach.controller.controllerComponent.controllerImpl.Controller
import de.htwg.se.ChinaSchach.model.EmptyField
import de.htwg.se.ChinaSchach.observer.Observer
import de.htwg.se.ChinaSchach.util.Point

import scala.io.StdIn

//TODO: implement TUI
class Tui(controller: Controller) extends Observer {

  controller.addObserver(this)

  var counter = 0

  // initialize TUI
  def go(): Unit = {
    drawGameboard()
    println("The board uses Point-Coordinates (X, Y) to initialize each chessfield with a corresponding piece if existant.")
    println("Please select and move a Chesspiece by using 'p XY'.")
    println("Format: X = Integer, Y = Integer")
    println("It is possible to 'quit' and 'restart' anytime.\n")
    println("Select source point:")
    readInput()
  }

  // read user input
  def readInput(): Unit = {
    val input = StdIn.readLine()
    input match {
      case "quit" =>
        controller.exit()
      case "restart" =>
        restart()
      case p if input.startsWith("p") && input.length == 4 =>
        if (controller.playerTurnCheck(Point(input.charAt(2).toString.toInt, input.charAt(3).toString.toInt)) && counter == 0) {
          counter += 1
          println("Choose destination:")
          readInput()
        } else if (controller.playerTurnCheck(Point(input.charAt(2).toString.toInt, input.charAt(3).toString.toInt)) && counter == 0) {
          counter += 1
          println("Select destination point:")
          readInput()
        } else if (counter % 2 != 0) {
          controller.getSelectedPoint(Point(input.charAt(2).toString.toInt, input.charAt(3).toString.toInt))
          if (controller.board.round % 2 == 0) {
            println("Round: " + controller.board.round + " Turn: player 1\n")
          } else {
            println("Round: " + controller.board.round + " Turn: player 2\n")
          }
          println("Select source point:")
          readInput()
        } else {
          println("Wrong selection! Its not your turn!")
          readInput()
        }
      case _ =>
        println("wrong input mate")
        readInput()
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
    readInput()
  }

  override def update(): Unit = {
    drawGameboard()
    checkForWin()
    counter = 0
  }

  def drawGameboard(): Unit = {
    val g = controller.board.gameBoard.values.toList
    val list = g.map { case EmptyField(" ") => "--"; case x => x }

    println("SCHACH\n")
    println("=========================================================================================================================================================================")

    printf("| 00: %-15s| 10: %-15s| 20: %-15s| 30: %-15s| 40: %-15s| 50: %-15s| 60: %-15s| 70: %-15s|\n", list(53), list(1), list(14), list(62), list(35), list(15), list(57), list(10))
    println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------")
    printf("| 01: %-15s| 11: %-15s| 21: %-15s| 31: %-15s| 41: %-15s| 51: %-15s| 61: %-15s| 71: %-15s|\n", list(38), list(31), list(0), list(36), list(48), list(47), list(22), list(52))
    println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------")
    printf("| 02: %-15s| 12: %-15s| 22: %-15s| 32: %-15s| 42: %-15s| 52: %-15s| 62: %-15s| 72: %-15s|\n", list(32), list(55), list(25), list(13), list(20), list(23), list(11), list(50))
    println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------")
    printf("| 03: %-15s| 13: %-15s| 23: %-15s| 33: %-15s| 43: %-15s| 53: %-15s| 63: %-15s| 73: %-15s|\n", list(40), list(27), list(3), list(34), list(44), list(21), list(19), list(4))
    println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------")
    printf("| 04: %-15s| 14: %-15s| 24: %-15s| 34: %-15s| 44: %-15s| 54: %-15s| 64: %-15s| 74: %-15s|\n", list(59), list(46), list(9), list(58), list(51), list(54), list(24), list(12))
    println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------")
    printf("| 05: %-15s| 15: %-15s| 25: %-15s| 35: %-15s| 45: %-15s| 55: %-15s| 65: %-15s| 75: %-15s|\n", list(45), list(17), list(7), list(33), list(41), list(8), list(5), list(43))
    println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------")
    printf("| 06: %-15s| 16: %-15s| 26: %-15s| 36: %-15s| 46: %-15s| 56: %-15s| 66: %-15s| 76: %-15s|\n", list(37), list(61), list(18), list(39), list(29), list(2), list(49), list(28))
    println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------")
    printf("| 07: %-15s| 17: %-15s| 27: %-15s| 37: %-15s| 47: %-15s| 57: %-15s| 67: %-15s| 77: %-15s|\n", list(56), list(30), list(60), list(63), list(6), list(42), list(26), list(16))

    println("=========================================================================================================================================================================\n")
  }
}
