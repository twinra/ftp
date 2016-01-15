package com.github.twinra

import java.io.File

import com.typesafe.scalalogging.LazyLogging
import org.apache.ftpserver.FtpServerFactory
import org.apache.ftpserver.listener.ListenerFactory
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory


object Main extends LazyLogging{
  def main(args: Array[String]): Unit = {
    logger.debug("start")
    new FtpServerFactory {
      val listenerFactory = new ListenerFactory {
        setPort(2221)
      }
      addListener("default", listenerFactory.createListener())

      val userManagerFactory = new PropertiesUserManagerFactory {
        setFile(new File("ftp.user.properties"))
      }
      setUserManager(userManagerFactory.createUserManager())
    }.createServer().start()
  }
}