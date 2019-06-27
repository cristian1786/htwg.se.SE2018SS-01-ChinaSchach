package de.htwg.se.ChinaSchach.FileIOComponent.FileIOImpl

import play.api.libs.json._
import de.htwg.se.ChinaSchach.FileIOComponent.FileIOInterface
import de.htwg.se.ChinaSchach.model._
import de.htwg.se.ChinaSchach.util.Point
import de.htwg.se.ChinaSchach.controller._
import scala.io.Source

class FileIO extends FileIOInterface {

  override def load(controller: Controller, file: String): Unit = {
    val src: String = Source.fromFile(file).getLines.mkString
    val json: JsValue = Json.parse(src)
    controller.board.round = (json \ "gameboard" \ "round").get.as[Int]
    controller.rochadeDoneW = (json \ "gameboard" \ "rochadeDoneW").get.toString().toBoolean
    controller.rochadeDoneB = (json \ "gameboard" \ "rochadeDoneB").get.toString().toBoolean
    controller.canMove = (json \ "gameboard" \ "canMove").get.toString().toBoolean
    controller.moveDone = (json \ "gameboard" \ "moveDone").get.toString().toBoolean
    controller.player1.Turn = (json \ "gameboard" \ "playerTurnW").get.toString().toBoolean
    controller.player2.Turn = (json \ "gameboard" \ "playerTurnB").get.toString().toBoolean

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

  override def save(controller: Controller, file: String): Unit = {
    import java.io.{File, PrintWriter}
    val pw = new PrintWriter(new File(file))
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
