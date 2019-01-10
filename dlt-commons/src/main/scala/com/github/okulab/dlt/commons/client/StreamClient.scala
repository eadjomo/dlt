package com.github.okulab.dlt.commons.client

import org.apache.spark.internal.Logging
import org.apache.spark.sql.DataFrame

case class StreamClient() extends Logging {

  protected var options: Map[String, String] = Map.empty[String, String]
  protected var sink: String = ""
  protected var behaviour: String = ""

  def option(key: String, value: String): StreamClient = {
    this.options = this.options + (key -> value)
    this
  }

  def options(options: Map[String, String]): StreamClient = {
    this.options = options
    this
  }

  def sink(sink: String): StreamClient = {
    this.sink = sink
    this
  }

  def behaviour(behaviour : String): StreamClient ={
    this.behaviour =behaviour
    this
  }


  def build(): StreamClient = {
    sink match {
      case "kafka" => new KafkaClient().behaviour(behaviour).options(options)
      case _ => throw new Exception(s"STREAM SINK '$sink' IS NOT SUPPORTED")
    }
  }

  def getData(): Seq[DataFrame] = {
    Seq.empty[DataFrame]
  }


}
