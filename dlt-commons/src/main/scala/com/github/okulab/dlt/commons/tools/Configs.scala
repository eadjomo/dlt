package com.github.okulab.dlt.commons.tools

import com.typesafe.config.{Config, ConfigFactory}


/**
  * getConfig from external file
  *
  * @author #okulab-developers<team.dev@okulab.co>
  */
object Configs {

  //config file
  private var myConfig: Config = ConfigFactory.load(System.getProperty("config.resource"))
  val keyStorePass: String = "37karj-b9Nm_Kw_2WFt"

  def setConfig(newConfig: Config): Unit = {
    myConfig = newConfig
  }

  def getConfig(): Config = {
    myConfig
  }

  /**
    * getString value
    */
  def getString(key: String): String = {
    myConfig.getString(key)
  }

}
