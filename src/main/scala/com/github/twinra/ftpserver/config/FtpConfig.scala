package com.github.twinra.ftpserver.config

import com.typesafe.config.{ConfigFactory, Config}

class FtpConfig(config: Config) {

  lazy val ftpPort: Int = config.getInt("cfg.ftp.port")
}

object FtpConfig {
  def parse(fileName: String) = {
    val config = ConfigFactory.parseResources(fileName)
    new FtpConfig(config)
  }
}
