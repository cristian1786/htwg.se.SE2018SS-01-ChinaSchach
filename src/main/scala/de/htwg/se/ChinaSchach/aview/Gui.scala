package de.htwg.se.ChinaSchach.aview

import de.htwg.se.ChinaSchach.model.Board
import de.htwg.se.ChinaSchach.controller._
import de.htwg.se.ChinaSchach.util.Point

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
//  initializeGameBoardImages()
  drawBoard()
  buttonActionListener(fieldButtons)

  //initialize Button for each field
  def initializeButtons() : Unit = {
    for (y <- 0 to col-1) {
      fieldButtons(0)(y) = FieldButton(Point(0, y))
      fieldButtons(1)(y) = FieldButton(Point(1, y))
      fieldButtons(2)(y) = FieldButton(Point(2, y))
      fieldButtons(3)(y) = FieldButton(Point(3, y))
      fieldButtons(4)(y) = FieldButton(Point(4, y))
      fieldButtons(5)(y) = FieldButton(Point(5, y))
      fieldButtons(6)(y) = FieldButton(Point(6, y))
      fieldButtons(7)(y) = FieldButton(Point(7, y))
      // blacken Fields/Buttons to mimick chess board
      for (x <- 0 to row-1) {
        if ((x + y) % 2 != 0) {
          fieldButtons(x)(y).background = java.awt.Color.BLACK
        }
      }
    }
  }

  // TODO: implement piece graphics
  def initializeGameBoardImages(): Unit = {

  }


  // TODO: define buttonclick reaction
  // implements buttonclick action for each of the fields on the board
  // calls getSelectedPoint
  def buttonActionListener(fieldButtons: Array[Array[FieldButton]]) : Unit = {
    for {
      x <- 0 to row-1
      y <- 0 to col-1
    }
    fieldButtons(x)(y).reactions += {
      case _: ButtonClicked =>
//        controller.getSelectedPoint(Point(x, y))
//        controller.getSelectedPoint(fieldButtons(x)(y).getPoint())
        println(fieldButtons(x)(y).getPoint())

    }
  }

  // TODO: draw board
  def drawBoard(): Unit = {

    contents = new BoxPanel(Orientation.Horizontal) {
      contents += new GridPanel(row, col) {
        preferredSize = new Dimension(626,626)
        for {
          x <- 0 to row-1
          y <- 0 to col-1
        }
        contents += fieldButtons(x)(y)

      }
      contents += new Label("test")
    }
  }

  // TODO: establish function to make a restart possible
  def restartGame(): Unit = {

  }


}
