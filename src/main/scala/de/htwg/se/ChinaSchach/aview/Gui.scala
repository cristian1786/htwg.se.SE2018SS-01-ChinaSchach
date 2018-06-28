package de.htwg.se.ChinaSchach.aview

import de.htwg.se.ChinaSchach.model.Board
import de.htwg.se.ChinaSchach.model._
import de.htwg.se.ChinaSchach.controller._
import de.htwg.se.ChinaSchach.observer.Observer
import de.htwg.se.ChinaSchach.util.Point
import javax.swing.ImageIcon

import scala.collection.mutable.ListBuffer
import scala.language.postfixOps
import scala.swing._
import scala.swing.event.ButtonClicked

class Gui(controller: Controller) extends Observer {

  val labelRound = new Label("Round: 0 Turn: player 1")
  val player1Label = new Label("  player 1   ")
  val player2Label = new Label("  player 2   ")

  val chessWidth = 900
  val chessHeight = 900

  val frame = new MainFrame()

  frame.title = "Schach"
  frame.preferredSize = new Dimension(chessWidth, chessHeight)

  val row = 8
  val col = 8
  var fieldButtons = Array.ofDim[FieldButton](row, col)

  var counter = 0

  // initialize Gui
  def go(): Unit = {
    initializeButtons()
    buttonActionListener()
    drawBoard()
  }

  //initialize Button for each field
  def initializeButtons(): Unit = {
    for (y <- 0 until col) {
      fieldButtons(0)(y) = new FieldButton(Point(0, y))
      fieldButtons(1)(y) = new FieldButton(Point(1, y))
      fieldButtons(2)(y) = new FieldButton(Point(2, y))
      fieldButtons(3)(y) = new FieldButton(Point(3, y))
      fieldButtons(4)(y) = new FieldButton(Point(4, y))
      fieldButtons(5)(y) = new FieldButton(Point(5, y))
      fieldButtons(6)(y) = new FieldButton(Point(6, y))
      fieldButtons(7)(y) = new FieldButton(Point(7, y))
      // colorizes Fields/Buttons to mimic chess board
      for (x <- 0 until row) {
        if ((x + y) % 2 == 0) {
          fieldButtons(x)(y).background = java.awt.Color.DARK_GRAY
        } else {
          fieldButtons(x)(y).background = java.awt.Color.LIGHT_GRAY
        }
      }
    }
  }

  // sets Piece icons on each tile containing a piece
  def setGameBoardImages(): Unit = {
    for {
      x <- 0 until row
      y <- 0 until col
    } controller.board.gameBoard.get(Point(x, y)) match {
      case Some(_: EmptyField) =>
        try {
          fieldButtons(x)(y).icon = new ImageIcon(this.getClass.getResource("resources/empty.png"))
        } catch {
          case e: NullPointerException => {
            println("Could not find resource!", e)
            fieldButtons(x)(y).icon = null
          }
        }
      case Some(piece) =>
        try {
          fieldButtons(x)(y).icon = new ImageIcon(this.getClass.getResource("resources/" + piece.toString + ".png"))
        } catch {
          case e: NullPointerException => println("Could not find resource!", e)
        }
      case None =>
    }
  }

  // game won dialog to choose if the players want to restart or quit the game
  def gameWonDialog(str: String): Unit = {
    val message = " Do you want to quit? (No restarts the game)"
    val res = Dialog.showConfirmation(frame.contents.last, str + message, optionType = Dialog.Options.YesNo, title = "Game over!")
    if (res == Dialog.Result.Yes) {
      sys.exit(0)
    } else if (res == Dialog.Result.No) {
      restartGame()
    }
  }

  // implements buttonclick action for each of the fields on the board
  // calls getSelectedPoint
  def buttonActionListener(): Unit = {
    for {
      x <- 0 until row
      y <- 0 until col
    } fieldButtons(x)(y).reactions += {
      case _: ButtonClicked =>
        if (controller.round % 2 != 0 && controller.board.gameBoard(fieldButtons(x)(y).getPoint()).getSide() == "b") {
          controller.getSelectedPoint(Point(x, y))
          val xx = controller.sourcePoint.getX()
          val yy = controller.sourcePoint.getY()
          setGameBoardImages()
          if (counter != 0) {
            setTileBackground(xx, yy)
            setCounter()
          } else {
            fieldButtons(x)(y).background = java.awt.Color.GREEN
            counter += 1
          }
        } else if (controller.round % 2 == 0 && controller.board.gameBoard(fieldButtons(x)(y).getPoint()).getSide() == "w") {
          controller.getSelectedPoint(Point(x, y))
          val xx = controller.sourcePoint.getX()
          val yy = controller.sourcePoint.getY()
          setGameBoardImages()
          if (counter != 0) {
            setTileBackground(xx, yy)
            setCounter()
          } else {
            fieldButtons(x)(y).background = java.awt.Color.GREEN
            counter += 1
          }
        } else if (counter % 2 != 0) {
          controller.getSelectedPoint(Point(x, y))
          update()
        }
    }
  }

  // Observer update
  override def update() : Unit = {
    setBackGround()
    setGameBoardImages()
    setTopLabel()
    setCounter()
    checkForWin()
  }

  // reset counter to 0
  def setCounter(): Unit = {
    counter = 0
  }

  // helper function which checks for win by calling controller variable
  def checkForWin(): Unit = {
    if (controller.bottomKingDead) {
      gameWonDialog("Player 2 won!")
    } else if (controller.topKingDead) {
      gameWonDialog("Player 1 won!")
    }
  }

  // display and update top-Label
  def setTopLabel(): Unit = {
    if (controller.round % 2 == 0) {
      labelRound.text = "Round: " + controller.round + " Turn: player 1"
    } else {
      labelRound.text = "Round: " + controller.round + " Turn: player 2"
    }
  }

  // set the background of the chess gameboard tiles
  def setBackGround(): Unit = {
    for {
      x <- 0 until row
      y <- 0 until col
    } if ((x + y) % 2 == 0) {
      fieldButtons(x)(y).background = java.awt.Color.DARK_GRAY
    } else {
      fieldButtons(x)(y).background = java.awt.Color.LIGHT_GRAY
    }
  }

  def setTileBackground(x: Int, y: Int): Unit = {
    if ((x + y) % 2 == 0) {
      fieldButtons(x)(y).background = java.awt.Color.DARK_GRAY
    } else {
      fieldButtons(x)(y).background = java.awt.Color.LIGHT_GRAY
    }
  }

  // function draws the chessboard GUI
  // establishes all the contents of the GUI
  def drawBoard(): Unit = {
    frame.contents = new BorderPanel {
      add(player1Label, BorderPanel.Position.West)
      add(player2Label, BorderPanel.Position.East)
      add(new GridPanel(row, col) {
        preferredSize = new Dimension(626, 626)
        for {
          x <- 0 until row
          y <- 0 until col
        } contents += fieldButtons(x)(y)
        setGameBoardImages()
      }, BorderPanel.Position.Center)
      add(labelRound, BorderPanel.Position.North)
      add(new GridPanel(1, 2) {
        contents += Button("Restart Game") {
          val res = Dialog.showConfirmation(contents.head, " Do you want to restart?", optionType = Dialog.Options.YesNo)
          if (res == Dialog.Result.Yes) { restartGame() }
        }
        contents += Button("Quit") { exitGame() }
      }, BorderPanel.Position.South)
    }
  }

  // exit game dialog helper function
  def exitGame(): Unit = {
    val res = Dialog.showConfirmation(frame.contents.last, " Do you want to quit?", optionType = Dialog.Options.YesNo)
    if (res == Dialog.Result.Yes) {
      controller.exit()
    }
  }

  // restart game dialog helper function
  def restartGame(): Unit = {
    controller.reset()
    setGameBoardImages()
    setCounter()
    controller.setRound()
    labelRound.text = "Round: " + controller.round + " Turn: player 1"
    go()
  }

  // Dialog to promote Pawn
  def promotePawnDialog(list: ListBuffer[Piece], side: String): Piece = {
    for (piece <- list) {
      if (piece.equals(Pawn("w"))) {
        list.-=(piece)
      }
      if (piece.equals(Pawn("b"))) {
        list.-=(piece)
      }
    }
    val listSq: Seq[Piece] = list.toList
    val icon = new ImageIcon(this.getClass.getResource("resources/empty.png"))
    val input: Option[Piece] = Dialog.showInput(frame.contents.last, "Choose now...", "Promote Pawn", Dialog.Message.Info, icon, listSq, listSq.head)
    val ret = input match {
      case Some(_: Rook) =>
        Rook(side)
      case Some(_: Queen) =>
        Queen(side)
      case Some(_: Bishop) =>
        Bishop(side)
      case Some(_: Knight) =>
        Knight(side)
      case _ => promotePawnDialog(list, side)
    }
    ret
  }
}