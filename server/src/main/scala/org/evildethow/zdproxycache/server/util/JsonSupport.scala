package org.evildethow.zdproxycache.server.util

import org.json4s.{DefaultFormats, jackson}

/**
  * Created by evildethow on 29/07/2016.
  */
trait JsonSupport {
  implicit val serialization = jackson.Serialization
  implicit val formats = DefaultFormats
}
