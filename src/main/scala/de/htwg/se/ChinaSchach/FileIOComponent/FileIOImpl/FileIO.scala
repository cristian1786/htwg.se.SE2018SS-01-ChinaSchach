package de.htwg.se.ChinaSchach.FileIOComponent.FileIOImpl

import play.api.libs.json._
import de.htwg.se.ChinaSchach.FileIOComponent.FileIOInterface
import de.htwg.se.ChinaSchach.model._
import de.htwg.se.ChinaSchach.util.Point
import de.htwg.se.ChinaSchach.controller._

class FileIO extends FileIOInterface {

  override def save(gameboard: Board, controller: Controller): Unit = {
    import java.io.{File, PrintWriter}
    val pw = new PrintWriter(new File("C:\\SE_Schach_5\\htwg.se.SE2018SS-01-ChinaSchach\\saves\\testSave.json"))
    pw.write(Json.prettyPrint(chessToJson(gameboard, controller)))
    pw.close()
  }

  override def load(): Unit = {

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
      //print(gameboard.gameBoard.get(Point(2,1)))
      //print(gameboard.gameBoard.head)
      //gameboard.gameBoard foreach (x => printf("%s, %s\n", x._1, x._2))
      //gameboard.gameBoard foreach {case (key, value) => printf("%s, %s\n", key, value)}
      //print("\n" + controller.round)
    )
  }
}
