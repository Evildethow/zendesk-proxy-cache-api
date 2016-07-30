package org.evildethow.zdproxycache.server.route.http

import akka.http.scaladsl.server.Directives._
import de.heikoseeberger.akkahttpjson4s.Json4sSupport._
import org.evildethow.zdproxycache.server.dao.CommentsTable
import org.evildethow.zdproxycache.server.util.JsonSupport

/**
  * Created by evildethow on 31/07/2016.
  */
trait CommentRoute extends CommentsTable with JsonSupport {

  val commentRoute = pathPrefix("organisations" / LongNumber / "tickets" / LongNumber / "comments") { (orgId, ticketId) =>
    pathEndOrSingleSlash {
      get {
        complete(getComments(orgId, ticketId))
      }
    } ~
      pathPrefix(LongNumber) { id =>
        get {
          complete(getComment(orgId, ticketId, id))
        }
      }
  }
}
