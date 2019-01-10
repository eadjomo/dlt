package com.github.okulab.dlt.reporting.sdk.mail

import com.github.okulab.dlt.reporting.core.{Reporter, ReporterProvider}

/**
  * [[ReporterProvider]] implementation for [[EmailReporter]]
  *
  * @author #okulab-developers<team.dev@okulab.co>
  */
class EmailReporterProvider extends ReporterProvider {

  override def reporterName: String = "reporting"

  override def prepareReporter(conf: Any): Reporter = {
    if (!conf.isInstanceOf[(String, Int)]) {
      throw new IllegalArgumentException(
        s"${this.getClass.getName}.prepareReporter(..) parameter should be (String, Int), current is ${conf.getClass}"
      )
    }

    val (host, port) = conf.asInstanceOf[(String, Int)]
    new EmailReporter(host, port)
  }
}
