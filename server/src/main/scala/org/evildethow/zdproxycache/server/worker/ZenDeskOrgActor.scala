package org.evildethow.zdproxycache.server.worker

import akka.actor.{Actor, ActorLogging, Props}
import org.evildethow.zdproxycache.server.worker.ZenDeskOrgActor.Tick

/**
  * Created by evildethow on 28/07/2016.
  */
class ZenDeskOrgActor extends Actor with ActorLogging {
  override def receive: Receive = {
    case Tick => {
      log.debug(s"Running worker [${ZenDeskOrgActor.getClass.getSimpleName}]")
      // TODO: Query ZD API and update DB here
    }
  }
}

object ZenDeskOrgActor {
  val props = Props[ZenDeskOrgActor]
  case class Tick()
}