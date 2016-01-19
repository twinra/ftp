package com.github.twinra.ftpserver

import java.io.File

import com.github.twinra.ftpserver.config.FtpConfig
import com.typesafe.scalalogging.LazyLogging
import org.apache.ftpserver.FtpServerFactory
import org.apache.ftpserver.listener.ListenerFactory
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory

object Main extends LazyLogging {

  def main(args: Array[String]): Unit = {

    val config = FtpConfig.parse("application.conf")
    logger.debug("loaded configuration")
    logger.debug(s"starting ftp server on ${config.ftpPort} port")

    new FtpServerFactory {
      val listenerFactory = new ListenerFactory {
        setPort(config.ftpPort)
      }
      addListener("default", listenerFactory.createListener())

      val userManagerFactory = new PropertiesUserManagerFactory {
        setFile(new File("ftp.user.properties"))
      }
      setUserManager(userManagerFactory.createUserManager())
    }.createServer().start()
  }
}
