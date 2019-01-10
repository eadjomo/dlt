package com.github.okulab.dlt.reporting.core

import com.github.okulab.dlt.reporting.model.Report

/**
  * Generic reporter interface
  *
  * @author #okulab-developers<team.dev@okulab.co>
  */
trait Reporter {
  def send(report: Report): Any
}
