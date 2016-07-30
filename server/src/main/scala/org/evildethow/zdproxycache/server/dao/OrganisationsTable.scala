package org.evildethow.zdproxycache.server.dao

import org.evildethow.zdproxycache.core.model.Organisation

import scala.concurrent.Future

/**
  * Created by evildethow on 31/07/2016.
  */
trait OrganisationsTable {

  import org.evildethow.zdproxycache.server.util.Database._
  import dbConfig.driver.api._

  class Organisations(tag: Tag) extends Table[Organisation](tag, "organisations") {
    def id = column[Long]("id", O.PrimaryKey)
    def externalId = column[String]("external_id")

    def * = (id, externalId) <> ((Organisation.apply _).tupled, Organisation.unapply)
  }

  protected val organisations = TableQuery[Organisations]

  def getOrganisations: Future[Seq[Organisation]] = db.run(organisations.result)
  def getOrganisation(id: Long): Future[Option[Organisation]] = db.run(organisations.filter(_.id === id).result.headOption)
}
