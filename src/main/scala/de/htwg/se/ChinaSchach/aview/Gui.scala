package de.htwg.se.ChinaSchach.aview

import de.htwg.se.ChinaSchach.controller._
import de.htwg.se.ChinaSchach.model._
import de.htwg.se.ChinaSchach.observer.Observer
import de.htwg.se.ChinaSchach.util.Point
import javax.swing.ImageIcon

import scala.collection.mutable.ListBuffer
import scala.language.postfixOps
import scala.swing._
import scala.swing.event.ButtonClicked

class Gui(controller: Controller) extends Observer {

  controller.addObserver(this)

  val labelRound = new Label("Round: 0 Turn: Sponge Bob")
  val player1Label = new Label("  Sponge Bob   ")
  val player2Label = new Label("  Peter Griffin   ")

  val chessWidth = 900
  val chessHeight = 900

  val frame = new MainFrame()

  frame.title = "Chess"
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
          fieldButtons(x)(y).background = java.awt.Color.GRAY
        } else {
          fieldButtons(x)(y).background = java.awt.Color.DARK_GRAY
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

  frame.menuBar = new MenuBar {
    contents += new Menu("File") {
      contents += new MenuItem(Action("Restart") {
        //TODO maybe dialog option
        restartGame
      })
      contents += new MenuItem(Action("Save") {
        controller.save
      })
      contents += new MenuItem(Action("Load") {
        controller.load
      })
      contents += new MenuItem(Action("Quit") {
        exitGame
      })
    }
  }

  // game won dialog to choose if the players want to restart or quit the game
  def gameWonDialog(str: String): Unit = {
    val message = " Do you want to quit? (No restarts the game)"
    val res = Dialog.showConfirmation(frame.contents.last, str + message, optionType = Dialog.Options.YesNo, title = "Game over!")
    if (res == Dialog.Result.Yes) {
      sys.exit(0)
    } else if (res == Dialog.Result.No) {
      controller.reset()
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
        if (controller.playerTurnCheck(Point(x, y))) {
          controller.savePiecePoint(Point(x, y))
          fieldButtons(x)(y).background = java.awt.Color.BLUE
          //counter += 1

        } else if (controller.playerTurnCheckDest) {
          controller.getSelectedPoint(Point(x, y))
        }
    }
  }

  // Observer update
  override def update(): Unit = {
    setBackGround()
    setGameBoardImages()
    setTopLabel()
    checkForWin()
  }

  // helper function which checks for win by calling controller variable
  def checkForWin(): Unit = {
    if (controller.bottomKingDead) {
      gameWonDialog("Peter Griffin won!")
    } else if (controller.topKingDead) {
      gameWonDialog("Sponge Bob won!")
    }
  }

  // display and update top-Label
  def setTopLabel(): Unit = {
    if (controller.player1.Turn) {
      labelRound.text = "Round: " + controller.round + " Turn: Sponge Bob"
    } else {
      labelRound.text = "Round: " + controller.round + " Turn: Peter Griffin"
    }
  }

  // set the background of the chess gameboard tiles
  def setBackGround(): Unit = {
    for {
      x <- 0 until row
      y <- 0 until col
    } if ((x + y) % 2 == 0) {
      fieldButtons(x)(y).background = java.awt.Color.GRAY
    } else {
      fieldButtons(x)(y).background = java.awt.Color.DARK_GRAY
    }
  }

  def setTileBackground(x: Int, y: Int): Unit = {
    if ((x + y) % 2 == 0) {
      fieldButtons(x)(y).background = java.awt.Color.DARK_GRAY
    } else {
      fieldButtons(x)(y).background = java.awt.Color.GRAY
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
        contents += Button("Next Player") {
          controller.resetPlayerTurn
        }
        contents += Button("Undo") {
          controller.undo
        }
        contents += Button("Redo") {
          controller.redo
        }
      }, BorderPanel.Position.South)
    }
  }

  // restart game dialog helper function
  def restartGame(): Unit = {
    val res = Dialog.showConfirmation(frame.contents.last, " Do you want to restart?", optionType = Dialog.Options.YesNo)
    if (res == Dialog.Result.Yes) {
      controller.reset()
    }
  }

  // exit game dialog helper function
  def exitGame(): Unit = {
    val res = Dialog.showConfirmation(frame.contents.last, " Do you want to quit?", optionType = Dialog.Options.YesNo)
    if (res == Dialog.Result.Yes) {
      controller.exit()
    }
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