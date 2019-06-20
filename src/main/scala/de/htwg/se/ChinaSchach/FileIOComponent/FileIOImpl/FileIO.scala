package de.htwg.se.ChinaSchach.FileIOComponent.FileIOImpl

import java.io.{File, PrintWriter}

import play.api.libs.json._
import de.htwg.se.ChinaSchach.FileIOComponent.FileIOInterface
import de.htwg.se.ChinaSchach.model.Board

class FileIO extends FileIOInterface {

  // board, playerturn. rochade, moveDone, canMove, round


  override def save(gameboard: Board): Unit = {
    val pw = new PrintWriter(new File("C:\\SE_Schach_5\\htwg.se.SE2018SS-01-ChinaSchach\\saves\\testSave.json"))
    pw.write(Json.toJson(gameboard)



    pw.close()
  }

  override def load(): Unit = {

  }


  implicit val boardWrites = new Writes[Board] {
    def writes() = Json.obj(
      //TODO fill with board pieces
    )
  }
}
