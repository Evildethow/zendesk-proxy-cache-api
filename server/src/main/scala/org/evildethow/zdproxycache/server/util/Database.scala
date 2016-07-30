package org.evildethow.zdproxycache.server.util

import org.evildethow.zdproxycache.server.Main.databaseImplementation
import slick.backend.DatabaseConfig
import slick.driver.JdbcProfile
import slick.jdbc.JdbcBackend

object Database {
  lazy val dbConfig = DatabaseConfig.forConfig[JdbcProfile](s"database.$databaseImplementation")
  lazy val db: JdbcBackend#DatabaseDef = dbConfig.db
}
