package org.evildethow.zdproxycache.core.model

/**
  * Created by evildethow on 29/07/2016.
  */
case class ZenDeskInfo(url: String, organisations: List[String])
case class ServerInfo(zenDeskInfo: ZenDeskInfo)
