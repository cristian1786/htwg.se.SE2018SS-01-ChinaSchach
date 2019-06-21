package de.htwg.se.ChinaSchach.FileIOComponent.FileIOImpl

import play.api.libs.json._
import de.htwg.se.ChinaSchach.FileIOComponent.FileIOInterface
import de.htwg.se.ChinaSchach.model._
import de.htwg.se.ChinaSchach.util.Point
import de.htwg.se.ChinaSchach.controller._
import scala.io.Source

class FileIO extends FileIOInterface {

  override def load(controller: Controller): Unit = {
    val src: String = Source.fromFile("C:\\SE_Schach_5\\htwg.se.SE2018SS-01-ChinaSchach\\saves\\testSave.json").getLines.mkString
    val json: JsValue = Json.parse(src)
    val rnd = (json \ "gameboard" \ "round").get
    val rochDW = (json \ "gameboard" \ "rochadeDoneW").get
    val rochDB = (json \ "gameboard" \ "rochadeDoneB").get
    val canM = (json \ "gameboard" \ "canMove").get
    val moveD = (json \ "gameboard" \ "moveDone").get
    val pTW = (json \ "gameboard" \ "playerTurnW").get
    val pTB = (json \ "gameboard" \ "playerTurnB").get

    for (i <- 0 until 64) {
      val boardPiecesLength = (json \ "gameboard" \ "board")(0).\(i).get.toString().size
      val boardPiece = (json \ "gameboard" \ "board")(0).\(i).get.toString().slice(12, boardPiecesLength-6).toString
      val bPSide = (json \ "gameboard" \ "board")(0).\(i).get.toString().slice(boardPiecesLength-5, boardPiecesLength-4).toString
      val bPX = (json \ "gameboard" \ "board")(0).\(i).get.toString().slice(2, 3).toInt
      val bPY = (json \ "gameboard" \ "board")(0).\(i).get.toString().slice(3, 4).toInt

      boardPiece match {
        case "Pawn" =>
          controller.board.gameBoard += Point(bPX, bPY) -> Pawn(bPSide)
        case "EmptyField" =>
          controller.board.gameBoard += Point(bPX, bPY) -> EmptyField(bPSide)
        case "Rook" =>
          controller.board.gameBoard += Point(bPX, bPY) -> Rook(bPSide)
        case "Knight" =>
          controller.board.gameBoard += Point(bPX, bPY) -> Knight(bPSide)
        case "King" =>
          controller.board.gameBoard += Point(bPX, bPY) -> King(bPSide)
        case "Queen" =>
          controller.board.gameBoard += Point(bPX, bPY) -> Queen(bPSide)
        case "Bishop" =>
          controller.board.gameBoard += Point(bPX, bPY) -> Bishop(bPSide)
      }
    }
  }

  override def save(controller: Controller): Unit = {
    import java.io.{File, PrintWriter}
    val pw = new PrintWriter(new File("C:\\SE_Schach_5\\htwg.se.SE2018SS-01-ChinaSchach\\saves\\testSave.json"))
    pw.write(Json.prettyPrint(chessToJson(controller)))
    pw.close()
  }

  /*implicit val boardWrites = new Writes[Board] {
    def writes(gameboard: Board): JsValue = Json.obj(
        "point" -> JsString(gameboard.gameBoard.get)
        "piece" -> JsString(x._2)
      }
    )
  }*/

  def chessToJson(controller: Controller):JsValue = {
    Json.obj(
      "gameboard" -> Json.obj(
        "round" -> JsNumber(controller.board.round),
        "rochadeDoneW" -> JsBoolean(controller.rochadeDoneW),
        "rochadeDoneB" -> JsBoolean(controller.rochadeDoneB),
        "canMove" -> JsBoolean(controller.canMove),
        "moveDone" -> JsBoolean(controller.moveDone),
        "playerTurnW" -> JsBoolean(controller.player1.Turn),
        "playerTurnB" -> JsBoolean(controller.player2.Turn),
        "board" -> Json.arr(
            for {
              row <- 0 to 7;
              col <- 0 to 7
            } yield {
              //var str = row.toString+col.toString
              var str = ""+row+col
              Json.obj(
                str ->  controller.board.gameBoard.get(Point(row, col)).toString
              )
            }
          )
        )
    )
  }
}
