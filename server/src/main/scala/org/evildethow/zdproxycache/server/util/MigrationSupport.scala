package org.evildethow.zdproxycache.server.util

import org.flywaydb.core.Flyway

/**
  * Created by evildethow on 31/07/2016.
  */
trait MigrationSupport extends Config {

  private val flyway = new Flyway() {{
    setDataSource(databaseUrl, databaseUser, databasePassword)
  }}

  def migrate() = flyway.migrate()
  def dropDatabase() = flyway.clean()

  def reloadSchema() = {
    flyway.clean()
    flyway.migrate()
  }
}
