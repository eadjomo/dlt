package com.github.okulab.dlt.commons.client

import com.github.okulab.dlt.commons.connector.Spark
import org.apache.spark.sql.DataFrame

class KafkaClient() extends StreamClient {

  override def getData(): Seq[DataFrame] = {

    behaviour match {
      case "one-shot" => {
        Seq(Spark().sparkSession.read.format("kafka").options(options).load())
      }
      case "continuously" => {
        Seq(Spark().sparkSession.readStream.format("kafka").options(options).load())

      }
      case _ => throw new Exception(s"The behaviour for your stream reader $behaviour is not set or supported. It's either 'one-shot' or 'continuously'")
    }
  }
}
