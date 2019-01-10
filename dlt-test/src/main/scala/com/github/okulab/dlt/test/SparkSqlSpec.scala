package com.github.okulab.dlt.test

import org.apache.spark.internal.Logging
import org.apache.spark.sql.SQLContext
import org.scalatest.Suite

/**
  * SparkSqlSpec
 *
  * @author #okulab-developers<team.dev@okulab.co>
  */
trait SparkSqlSpec extends SparkSpec with Logging {
  this: Suite =>
  private var _sqlc: SQLContext = _

  def sqlc: SQLContext = _sqlc

  override def beforeAll() {
    super.beforeAll()
    _sqlc = new SQLContext(sc.sparkContext)
  }

  override def afterAll() {
    super.afterAll()
    _sqlc = null
  }
}
