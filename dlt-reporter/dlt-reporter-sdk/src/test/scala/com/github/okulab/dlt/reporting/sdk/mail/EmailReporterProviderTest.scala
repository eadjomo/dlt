package com.github.okulab.dlt.reporting.sdk.mail

import com.github.okulab.dlt.reporting.core.{ReporterProvider, ReporterProviderException}
import org.scalatest.{FlatSpec, Matchers}

/**
  * @author #okulab-developers<team.dev@okulab.co>
  */
class EmailReporterProviderTest extends FlatSpec with Matchers {
  val host = "0.0.0.0"
  val port: Int = 2222

  "The ReporterProvider" should "return the proper class" in {
    ReporterProvider.getReporter("reporting", (host, port)).getClass should be(classOf[EmailReporter])
  }

  it should "throws a ReporterProviderException" in {
    // Wrong job
    assertThrows[ReporterProviderException]{
      ReporterProvider.getReporter("UnknownReporter", None)
    }

    // Missing params
    assertThrows[ReporterProviderException]{
      ReporterProvider.getReporter("reporting", None)
    }

    // Wrong params
    assertThrows[ReporterProviderException]{
      ReporterProvider.getReporter("reporting", s"$host:$port")
    }
  }
}
