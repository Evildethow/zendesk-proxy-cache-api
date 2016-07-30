package org.evildethow.zdproxycache.server.util

import com.typesafe.config.ConfigFactory

/**
  * Created by evildethow on 28/07/2016.
  */
trait Config {
  private val config = ConfigFactory.load()
  private val database = config.getConfig("database")
  private val http = config.getConfig("http")
  private val zenDesk = config.getConfig("zd")

  val databaseImplementation = database.getString("implementation")
  val databaseUrl = database.getConfig(databaseImplementation).getString("db.url")
  val databaseUser = database.getConfig(databaseImplementation).getString("db.user")
  val databasePassword = database.getConfig(databaseImplementation).getString("db.password")

  val httpInterface = http.getString("interface")
  val httpPort = http.getInt("port")

  val zenDeskUsername = zenDesk.getString("username")
  val zenDeskPassword = zenDesk.getString("password")
  val zenDeskUrl = zenDesk.getString("url")
  import collection.JavaConversions._
  val zenDeskOrganisations = zenDesk.getStringList("organisations").toList
}
