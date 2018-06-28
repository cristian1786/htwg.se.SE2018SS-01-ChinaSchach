package de.htwg.se.ChinaSchach.aview

import de.htwg.se.ChinaSchach.controller.Controller
import de.htwg.se.ChinaSchach.observer.Observer
import de.htwg.se.ChinaSchach.util.Point

import scala.io.StdIn

//TODO: implement TUI
class Tui(controller: Controller) extends Observer {

  controller.addObserver(this)

  // prints an overview over the fields with the respective pieces
  //  def outputField(): Unit = {
  //    for (x <- 0 to 7) {
  //      for (y <- 0 to 7) {
  //        if (board.gameBoard(Point(x, y)).getSide() != " ") {
  //          println("Field(" + x + ", " + y + ") contains " + board.gameBoard(Point(x, y)))
  //        }
  //      }
  //    }
  //    println("")
  //    println("Field (0, 2) contains " + board.gameBoard(Point(0, 2)))
  //    println("")
  //  }
  //
  //  //prints a list with pieces for each player
  //  def outputPlayerFigures(list: ListBuffer[Piece]): Unit = {
  //    if (list.head.getSide() == "b") println("Player 1: ") else println("Player 2: ")
  //    list.foreach(println)
  //    println("")
  //  }

  // initialize TUI
  def go(): Unit = {
    drawGameboard()
    println("x = Integer, y Integer")
    println("The board uses Point-Coordinates (x, y) to initialize each chessfield.")
    println("Please input action to perform in the following format (src XY) (dest XY) (quit) (restart):")
    println("Choose source:")
    readInput()
  }

  // read user input
  def readInput(): Unit = {

    val input = StdIn.readLine()

    input match {
      case "quit" =>
        controller.exit()
      case "restart" =>
        controller.resetTUI()
        controller.setRound()
      case src if input.startsWith("src") =>
        controller.getSelectedPoint(Point(input.charAt(4).toString.toInt, input.charAt(5).toString.toInt))
        println(input.charAt(4).toString.toInt + "    #################################")
        println("Choose destination:")
        readInput()
      case dest if input.startsWith("dest") =>
        controller.getSelectedPoint(Point(input.charAt(5).toString.toInt, input.charAt(6).toString.toInt))
//        for ((k,v) <- controller.board.gameBoard) {
//          printf("keys:%s, value:%s\n", k ,v)
//        }
        println("Choose source:")
        readInput()

      case _ =>
        println("wrong input mate")
        readInput()
    }
  }

  def checkForWin() : Unit = {
    if (controller.bottomKingDead) {
      println("PLAYER 2 WON!")
      controller.resetTUI()
    } else if (controller.topKingDead) {
      println("PLAYER 1 WON!")
      controller.resetTUI()
    }
  }

  override def update() : Unit = {
    drawGameboard()
    checkForWin()
  }

  def drawGameboard() : Unit = {
    val g = controller.board.gameBoard.values.toList
    println("")

    println("00:" + g(53) + " 10:" + g(1) + " 20:" + g(14) + " 30:" + g(62) + " 40:" +  g(35) + " 50:" + g(15) + " 60:" + g(57) + " 70:" + g(10))
    println("01:" + g(38) + " 11:" + g(31) + " 21:" + g(0) + " 31:" + g(36) + " 41:" +  g(48) + " 51:" + g(47) + " 61:" + g(22) + " 71:" + g(52))
    println("02:" + g(32) + " 12:" + g(55) + " 22:" + g(25) + " 32:" + g(13) + " 42:" +  g(20) + " 52:" + g(23) + " 62:" + g(11) + " 72:" + g(50))
    println("03:" + g(40) + " 13:" + g(27) + " 23:" + g(3) + " 33:" + g(34) + " 43:" +  g(44) + " 53:" + g(21) + " 63:" + g(19) + " 73:" + g(4))
    println("04:" + g(59) + " 14:" + g(46) + " 24:" + g(9) + " 34:" + g(58) + " 44:" +  g(51) + " 54:" + g(54) + " 64:" + g(24) + " 74:" + g(12))
    println("05:" + g(45) + " 15:" + g(17) + " 25:" + g(7) + " 35:" + g(33) + " 45:" +  g(41) + " 55:" + g(8) + " 65:" + g(5) + " 75:" + g(43))
    println("06:" + g(37) + " 16:" + g(61) + " 26:" + g(18) + " 36:" + g(39) + " 46:" +  g(29) + " 56:" + g(2) + " 66" + g(49) + " 76:" + g(28))
    println("07:" + g(56) + " 17:" + g(30) + " 27:" + g(60) + " 37:" + g(63) + " 47:" +  g(6) + " 57:" + g(42) + " 67" + g(26) + " 77:" + g(16))

    println("")
  }
}
