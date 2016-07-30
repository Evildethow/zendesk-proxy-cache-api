package org.evildethow.zdproxycache.server.route.http

import akka.http.scaladsl.server.Directives._
import de.heikoseeberger.akkahttpjson4s.Json4sSupport._
import org.evildethow.zdproxycache.core.model.{ServerInfo, ZenDeskInfo}
import org.evildethow.zdproxycache.server.util.{Config, JsonSupport}

/**
  * Created by evildethow on 29/07/2016.
  */
trait ServerInfoRoute extends Config with JsonSupport {

  val serverInfoRoute =
    path("server" / "info") {
      get {
        complete(ServerInfo(ZenDeskInfo(zenDeskUrl, zenDeskOrganisations)))
      }
    }
}
