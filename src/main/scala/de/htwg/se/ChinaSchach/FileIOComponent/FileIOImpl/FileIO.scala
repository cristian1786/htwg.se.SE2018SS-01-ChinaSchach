package de.htwg.se.ChinaSchach.FileIOComponent.FileIOImpl

import java.io.{File, PrintWriter}
import play.api.libs.json._

import de.htwg.se.ChinaSchach.FileIOComponent.FileIOInterface

class FileIO extends FileIOInterface {

  // board, playerturn. rochade, moveDone, canMove, round


  override def save(): Unit = {
    val pw = new PrintWriter(new File("C:\\SE_Schach_5\\htwg.se.SE2018SS-01-ChinaSchach\\saves\\testSave.json"))




    pw.close()
  }

  override def load(): Unit = {

  }
}
