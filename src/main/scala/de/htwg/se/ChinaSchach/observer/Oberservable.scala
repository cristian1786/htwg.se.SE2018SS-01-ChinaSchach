package de.htwg.se.ChinaSchach.observer

import scala.collection.mutable.ListBuffer

trait Observer {
  def update()
}

class Observable {
  var observers = ListBuffer.empty[Observer]

  def addObserver(o: Observer): Unit = {
    observers.+=(o)
  }

  def removeObserver(o: Observer): Unit = {
    observers.-=(o)
  }

  def notifyObservers(): Unit = {
    for (observer <- observers) {
      observer.update()
    }
  }
}