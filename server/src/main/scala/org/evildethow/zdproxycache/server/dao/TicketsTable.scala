package org.evildethow.zdproxycache.server.dao

import org.evildethow.zdproxycache.core.model.Ticket

import scala.concurrent.Future

/**
  * Created by evildethow on 31/07/2016.
  */
trait TicketsTable extends OrganisationsTable {

  import org.evildethow.zdproxycache.server.util.Database._
  import dbConfig.driver.api._

  class Tickets(tag: Tag) extends Table[Ticket](tag, "tickets") {
    def id = column[Long]("id", O.PrimaryKey)
    def orgId = column[Long]("org_id")

    def orgFk = foreignKey("ORG_FK", orgId, organisations)(_.id, onUpdate = ForeignKeyAction.Restrict, onDelete = ForeignKeyAction.Cascade)

    def * = (id, orgId) <> ((Ticket.apply _).tupled, Ticket.unapply)
  }

  protected val tickets = TableQuery[Tickets]

  def getTickets(orgId: Long): Future[Seq[Ticket]] = db.run(tickets.filter(_.orgId === orgId).result)

  def getTicket(orgId: Long, id: Long): Future[Option[Ticket]] = db.run((for {
    ticket <- tickets.filter(_.orgId === orgId) if ticket.id === id
  } yield ticket).result.headOption)
}
