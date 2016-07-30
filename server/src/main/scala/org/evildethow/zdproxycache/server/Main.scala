package org.evildethow.zdproxycache.server

import akka.actor.ActorSystem
import akka.event.{Logging, LoggingAdapter}
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import org.evildethow.zdproxycache.server.route.Routes
import org.evildethow.zdproxycache.server.util.{Config, MigrationSupport}
import org.evildethow.zdproxycache.server.worker.ZenDeskOrgActor
import org.evildethow.zdproxycache.server.worker.ZenDeskOrgActor.Tick

import scala.concurrent.ExecutionContext
import scala.concurrent.duration.DurationInt

/**
  * Created by evildethow on 28/07/2016.
  */
object Main extends App with Routes with Config with MigrationSupport {
  // setup akka
  private implicit val system = ActorSystem()
  implicit val executor: ExecutionContext = system.dispatcher
  protected implicit val materializer: ActorMaterializer = ActorMaterializer()
  protected val log: LoggingAdapter = Logging(system, getClass)

  // migrate database
  migrate()

  // bind http server and start
  Http().bindAndHandle(handler = logRequestResult("log")(routes), interface = httpInterface, port = httpPort)

  // workers
  private val zenDeskOrgActor = system.actorOf(ZenDeskOrgActor.props, ZenDeskOrgActor.getClass.getSimpleName)
  system.scheduler.schedule(0 milliseconds, 60 seconds, zenDeskOrgActor, Tick)
}
