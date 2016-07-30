package org.evildethow.zdproxycache.server.dao

import org.evildethow.zdproxycache.core.model.Comment

import scala.concurrent.Future

/**
  * Created by evildethow on 31/07/2016.
  */
trait CommentsTable extends TicketsTable {

  import org.evildethow.zdproxycache.server.util.Database._
  import dbConfig.driver.api._

  class Comments(tag: Tag) extends Table[Comment](tag, "comments") {
    def id = column[Long]("id", O.PrimaryKey)
    def ticketId = column[Long]("ticket_id")
    def body = column[String]("body")

    def orgFk = foreignKey("TICKET_FK", ticketId, tickets)(_.id, onUpdate = ForeignKeyAction.Restrict, onDelete = ForeignKeyAction.Cascade)

    def * = (id, ticketId, body) <> ((Comment.apply _).tupled, Comment.unapply)
  }

  val comments = TableQuery[Comments]

  def getComments(orgId: Long, ticketId: Long): Future[Seq[Comment]] = db.run((for {
    ticket <- tickets.filter(_.orgId === orgId) if ticket.id === ticketId
    comment <- comments.filter(_.ticketId === ticket.id)
  } yield comment).result)

  def getComment(orgId: Long, ticketId: Long, id: Long): Future[Option[Comment]] = db.run((for {
    ticket <- tickets.filter(_.orgId === orgId) if ticket.id === ticketId
    comment <- comments.filter(_.ticketId === ticket.id) if comment.id === id
  } yield comment).result.headOption)
}
