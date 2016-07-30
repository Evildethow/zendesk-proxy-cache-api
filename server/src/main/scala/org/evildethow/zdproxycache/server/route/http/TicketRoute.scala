package org.evildethow.zdproxycache.server.route.http

import akka.http.scaladsl.server.Directives._
import de.heikoseeberger.akkahttpjson4s.Json4sSupport._
import org.evildethow.zdproxycache.server.dao.TicketsTable
import org.evildethow.zdproxycache.server.util.JsonSupport

/**
  * Created by evildethow on 31/07/2016.
  */
trait TicketRoute extends TicketsTable with JsonSupport {

  val ticketRoute = pathPrefix("organisations" / LongNumber / "tickets") { orgId =>
    pathEndOrSingleSlash {
      get {
        complete(getTickets(orgId))
      }
    } ~
    pathPrefix(LongNumber) { id =>
      get {
        complete(getTicket(orgId, id))
      }
    }
  }
}
