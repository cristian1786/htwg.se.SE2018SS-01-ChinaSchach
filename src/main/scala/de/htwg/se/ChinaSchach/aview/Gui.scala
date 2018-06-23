package de.htwg.se.ChinaSchach.aview

import de.htwg.se.ChinaSchach.model.Board
import de.htwg.se.ChinaSchach.model._
import de.htwg.se.ChinaSchach.controller._
import de.htwg.se.ChinaSchach.util.Point
import javax.swing.ImageIcon

import scala.swing._
import scala.swing.event.ButtonClicked


// TODO: implement GUI
class Gui(controller: Controller, board: Board) extends MainFrame {

  title = "Schach"
  preferredSize = new Dimension(800, 800)
  val rounds = new Label("Round: 0")
  var counter = 0

  val row = 8
  val col = 8
  var fieldButtons = Array.ofDim[FieldButton](row, col)


  initializeButtons()

  buttonActionListener()
  drawBoard()


  //initialize Button for each field
  def initializeButtons() : Unit = {
    for (y <- 0 to col-1) {
      fieldButtons(0)(y) = new FieldButton(Point(0, y))
      fieldButtons(1)(y) = new FieldButton(Point(1, y))
      fieldButtons(2)(y) = new FieldButton(Point(2, y))
      fieldButtons(3)(y) = new FieldButton(Point(3, y))
      fieldButtons(4)(y) = new FieldButton(Point(4, y))
      fieldButtons(5)(y) = new FieldButton(Point(5, y))
      fieldButtons(6)(y) = new FieldButton(Point(6, y))
      fieldButtons(7)(y) = new FieldButton(Point(7, y))
      // blacken Fields/Buttons to mimick chess board
      for (x <- 0 to row-1) {
        if ((x + y) % 2 != 0) {
          fieldButtons(x)(y).background = java.awt.Color.BLACK
        } else {
          fieldButtons(x)(y).background = java.awt.Color.WHITE
        }
      }
    }
  }

  // TODO: implement piece graphics
  def setGameBoardImages(): Unit = {

    fieldButtons(0)(1).icon = new ImageIcon(this.getClass.getResource("resources/Pawn(b).png"))

    for {
      x <- 0 until row
      y <- 0 until col
    }
      //println("Here you go: " + board.gameBoard.get(Point(x, y)))
    board.gameBoard.get(Point(x, y)) match {
      case Some(piece) =>
        fieldButtons(0)(1).icon = new ImageIcon(this.getClass.getResource("resources/Pawn(w).png"))
        println("test1")
      case None =>
        fieldButtons(0)(1).icon = new ImageIcon(this.getClass.getResource("resources/Pawn(w).png"))
        println("test2")
    }

  }


  // TODO: define buttonclick reaction
  // implements buttonclick action for each of the fields on the board
  // calls getSelectedPoint
  def buttonActionListener() : Unit = {
    for {
      x <- 0 to row-1
      y <- 0 to col-1
    }
    fieldButtons(x)(y).reactions += {
      case _: ButtonClicked =>
        controller.getSelectedPoint(Point(x, y))
//        controller.getSelectedPoint(fieldButtons(x)(y).getPoint())
        println(fieldButtons(x)(y).getPoint())
        println(board.gameBoard.get(Point(x, y)))

    }
  }

  // TODO: draw board
  def drawBoard(): Unit = {

    contents = new FlowPanel {
      contents += new GridPanel(row, col) {
        preferredSize = new Dimension(626,626)
        for {
          x <- 0 to row-1
          y <- 0 to col-1
        }
        contents += fieldButtons(x)(y)
        setGameBoardImages()

      }
      contents += new Label("test")
      contents += Button("Restart Game") { restartGame() }
    }
  }

  // TODO: establish function to make a restart possible
  def restartGame(): Unit = {
    controller.reset()
  }


}
