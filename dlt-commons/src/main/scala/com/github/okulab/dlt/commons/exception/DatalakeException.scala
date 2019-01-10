package com.github.okulab.dlt.commons.exception
import com.github.okulab.dlt.commons.logging.LoggingMessage
import org.apache.log4j.Logger

/**
  * DatalakeException
  *
  * @author #okulab-developers<team.dev@okulab.co>
  */
trait DatalakeException extends Exception{
  var message: String = _
  var cause: Throwable = None orNull
  var projetName: String = _
  var language: String = _
  lazy val logger = Logger.getLogger(this.getClass.getName)
  def init(message: String, cause: Throwable) = {
    logger.error(LoggingMessage(projetName, language, message).toString, cause)
   initCause(cause)
  }

  def init(message: String) = {
    logger.error(LoggingMessage(projetName, language, message).toString,new Throwable())
    initCause(new Throwable())
  }

  def init(cause: Throwable) = {
    logger.error(LoggingMessage(projetName, language, cause.getMessage).toString,cause)
    initCause(cause)
  }

}
