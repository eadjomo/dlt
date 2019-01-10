package com.github.okulab.dlt.test

import java.net.URL
import java.util.concurrent.TimeUnit._

import org.scalatest.{BeforeAndAfterAll, Suite}
import pl.allegro.tech.embeddedelasticsearch.{EmbeddedElastic, PopularProperties}


/**
  * ElasticSpec
  *
  * @author #okulab-developers<team.dev@okulab.co>
  */
trait ElasticSpec extends BeforeAndAfterAll with SparkSpec {
  this: Suite =>

  private val elasticVersion = "6.3.1"
  var _embeddedElastic: EmbeddedElastic = _

  override def beforeAll() {
    super.beforeAll()

    _embeddedElastic = EmbeddedElastic.builder()
      .withSetting(PopularProperties.HTTP_PORT, 9002)
      .withSetting(PopularProperties.TRANSPORT_TCP_PORT, 9003)
      .withSetting(PopularProperties.CLUSTER_NAME, "elasticsearch_test")
      .withStartTimeout(5, MINUTES)
      .build()
      .start()
  }


  override def afterAll(): Unit = {
    super.afterAll()

    if (_embeddedElastic != null) {
      try {
        _embeddedElastic.stop()
      } catch {
        case e: Exception => e.printStackTrace()
      }
    }
  }
}
