package org.evildethow.zdproxycache.server.route.http

import akka.http.scaladsl.server.Directives._
import de.heikoseeberger.akkahttpjson4s.Json4sSupport._
import org.evildethow.zdproxycache.server.dao.OrganisationsTable
import org.evildethow.zdproxycache.server.util.JsonSupport

/**
  * Created by evildethow on 31/07/2016.
  */
trait OrganisationRoute extends OrganisationsTable with JsonSupport {

  val organisationRoute = pathPrefix("organisations") {
    pathEndOrSingleSlash {
      get {
        complete(getOrganisations)
      }
    } ~
    pathPrefix(LongNumber) { orgId =>
      get {
        complete(getOrganisation(orgId))
      }
    }
  }
}
