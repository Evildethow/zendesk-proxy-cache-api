package org.evildethow.zdproxycache.server.route

import org.evildethow.zdproxycache.server.route.http._

/**
  * Created by evildethow on 28/07/2016.
  */
trait Routes extends PingRoute with ServerInfoRoute with OrganisationRoute with TicketRoute with CommentRoute {

  import akka.http.scaladsl.server.Directives._

  private val restRoutes = pathPrefix("rest") {
    serverInfoRoute ~ commentRoute ~ ticketRoute ~ organisationRoute
  }

  val routes = pingRoute ~ restRoutes
}
