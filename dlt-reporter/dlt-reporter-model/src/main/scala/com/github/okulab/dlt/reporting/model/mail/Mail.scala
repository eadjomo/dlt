package com.github.okulab.dlt.reporting.model.mail

import com.github.okulab.dlt.reporting.model.Report

/**
  * Structure definition of an Email
  *
  * @author #okulab-developers<team.dev@okulab.co>
  */
case class Mail
(
  from: (String, String), // (email -> name)
  to: Seq[String],
  cc: Seq[String] = Seq.empty,
  bcc: Seq[String] = Seq.empty,
  subject: String,
  message: String,
  richMessage: Option[String] = None,
  attachment: Option[java.io.File] = None
) extends Report
