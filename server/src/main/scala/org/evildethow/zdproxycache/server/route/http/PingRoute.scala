package org.evildethow.zdproxycache.server.route.http

import akka.http.scaladsl.server.Directives._

/**
  * Created by evildethow on 29/07/2016.
  */
trait PingRoute {

  val pingRoute =
    path("ping") {
      get {
        complete("pong")
      }
    }
}
