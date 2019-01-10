package com.github.okulab.dlt.commons.logging

import java.text.SimpleDateFormat

import com.github.okulab.dlt.commons.connector.Spark
import org.apache.commons.lang3.builder.{ToStringBuilder, ToStringStyle}

/**
  * LoggingMessage
  *
  * @author #okulab-developers<team.dev@okulab.co>
  */
case class LoggingMessage(projectName: String, language: String, message: String) {

  val time: String = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS").format(System.currentTimeMillis())

  val sparkLang: String = language

  val sparkUser: String = Spark().sc.sparkUser

  var appId: String = Spark().sc.applicationId

  var appName: String = Spark().sc.appName

  var sparkVersion: String = Spark().sc.version

  var executorMemory: Map[String, (Long, Long)] = Spark().sc.getExecutorMemoryStatus.toMap

  override def toString: String = {
    ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE)
  }

}
