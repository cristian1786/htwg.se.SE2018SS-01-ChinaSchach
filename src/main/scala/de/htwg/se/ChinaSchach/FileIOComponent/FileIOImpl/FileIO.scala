package de.htwg.se.ChinaSchach.FileIOComponent.FileIOImpl

import play.api.libs.json._
import de.htwg.se.ChinaSchach.FileIOComponent.FileIOInterface
import de.htwg.se.ChinaSchach.model._
import de.htwg.se.ChinaSchach.util.Point
import de.htwg.se.ChinaSchach.controller._
import scala.io.Source

class FileIO extends FileIOInterface {

  override def load(): Unit = {
    val src: String = Source.fromFile("C:\\SE_Schach_5\\htwg.se.SE2018SS-01-ChinaSchach\\saves\\testSave.json").getLines.mkString
    val json: JsValue = Json.parse(src)
    //TODO read data from json file
    val rnd = (json \ "gameboard" \ "round").get
    val rochDW = (json \ "gameboard" \ "rochadeDoneW").get
    val rochDB = (json \ "gameboard" \ "rochadeDoneB").get
    val canM = (json \ "gameboard" \ "canMove").get
    val moveD = (json \ "gameboard" \ "moveDone").get
    val pTW = (json \ "gameboard" \ "playerTurnW").get
    val pTB = (json \ "gameboard" \ "playerTurnB").get


    print(canM, rochDW, rochDB, canM, moveD, pTW, pTB)
  }

  override def save(gameboard: Board, controller: Controller): Unit = {
    import java.io.{File, PrintWriter}
    val pw = new PrintWriter(new File("C:\\SE_Schach_5\\htwg.se.SE2018SS-01-ChinaSchach\\saves\\testSave.json"))
    pw.write(Json.prettyPrint(chessToJson(gameboard, controller)))
    pw.close()
  }

  /*implicit val boardWrites = new Writes[Board] {
    def writes(gameboard: Board): JsValue = Json.obj(
        "point" -> JsString(gameboard.gameBoard.get)
        "piece" -> JsString(x._2)
      }
    )
  }*/

  def chessToJson(gameboard: Board, controller: Controller):JsValue = {
    Json.obj(
      "gameboard" -> Json.obj(
        "round" -> JsNumber(gameboard.round),
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
                str ->  gameboard.gameBoard.get(Point(row, col)).toString
              )
            }
          )
        )
    )
  }
}
