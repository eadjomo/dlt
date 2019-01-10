package com.github.okulab.dlt.commons.test

import com.github.okulab.dlt.commons.connector.Spark
import com.github.okulab.dlt.commons.tools.Tools
import com.github.okulab.dlt.test.SparkSqlSpec
import org.scalatest.{BeforeAndAfter, FeatureSpec, Matchers}
import org.apache.spark.sql.functions._
/**
  * ToolsTest
  *
  * @author #okulab-developers<team.dev@okulab.co>
  */
class ToolsTest extends FeatureSpec with Matchers with SparkSqlSpec with BeforeAndAfter {
  override def beforeAll: Unit = {
    super.beforeAll()
    Spark(sc, sqlc)
  }

  override def afterAll: Unit = {
    super.afterAll()
  }

  feature("Tools.computeDataframe") {
    scenario("Get datastructure without technical fields") {
      val rdd = Spark().sc.parallelize(Array(("a1", "b1", "c1"), ("a2", "b2", "c2"), ("a3", "b3", "c3")))
      val df = Spark().sqlContext.createDataFrame(rdd)

      val computedDf = Tools.computeDataframe(df, Seq(("field1", "String", "1",None)))

      val t=Seq(("field2", "String", "concat(field1,'#')",1),("field3", "String", "concat(field2,'#')",2))

 val extracFields=t.sortBy(_._4)
      extracFields.foldLeft(computedDf) ((memoDF, colName) =>
          memoDF.withColumn(colName._1,expr(colName._3))
        ).show()

      // computedDf.show
      computedDf.schema.count(_ => true) should equal(8)
    }

    scenario("Get datastructure with DLL_VALIDITE") {
      val rdd = Spark().sc.parallelize(Array(("a1", "b1", "c1"), ("a2", "b2", "c2"), ("a3", "b3", "c3")))
      val df = Spark().sqlContext.createDataFrame(rdd)

      val computedDf = Tools.computeDataframe(df, Seq(("field1", "String", "1",None), ("DLL_VALIDITE", "Int", "5",None)))

      // computedDf.show
      computedDf.schema.count(_ => true) should equal(8)

      val _sqlc = Spark().sqlContext
      import _sqlc.implicits._

      computedDf.select("DLL_VALIDITE").as[Int].collect().count(_ == 5) should equal(3)
    }

    scenario("Get datastructure with DLL_CHECKSUM") {
      val rdd = Spark().sc.parallelize(Array(("a1", "b1", "c1"), ("a2", "b2", "c2"), ("a3", "b3", "c3")))
      val df = Spark().sqlContext.createDataFrame(rdd)

      val computedDf = Tools.computeDataframe(df, Seq(("field1", "String", "1",None), ("DLL_CHECKSUM", "INT", "10",None)))

      //computedDf.show
      computedDf.schema.count(_ => true) should equal(8)

      val _sqlc = Spark().sqlContext
      import _sqlc.implicits._

      computedDf.select("DLL_CHECKSUM").as[Int].collect().count(_ == 10) should equal(3)
    }

    scenario("Get datastructure with all technical fields ") {
      val rdd = Spark().sc.parallelize(Array(("a1", "b1", "c1"), ("a2", "b2", "c2"), ("a3", "b3", "c3")))
      val df = Spark().sqlContext.createDataFrame(rdd)
      val computeFields = Seq(
        ("field1", "String", "1",None),
        ("DLL_CHECKSUM", "INT", "10",None),
        ("DLL_VALIDITE", "Int", "5",None),
        ("DLL_DATE_MAJ", "String", "'OTHER'",None),
        ("DLL_DATE_CREATION", "String", "'OTHER_'",None)
      )

      val computedDf = Tools.computeDataframe(df, computeFields)

      // computedDf.show
      computedDf.schema.count(_ => true) should equal(8)

      val _sqlc = Spark().sqlContext
      import _sqlc.implicits._

      computedDf.select("DLL_CHECKSUM").as[Int].collect().count(_ == 10) should equal(3)
      computedDf.select("DLL_VALIDITE").as[Int].collect().count(_ == 5) should equal(3)
      computedDf.select("DLL_DATE_MAJ").as[String].collect().count(_ == "OTHER") should equal(3)
      computedDf.select("DLL_DATE_CREATION").as[String].collect().count(_ == "OTHER_") should equal(3)
    }
  }

  feature("Tools.getCaseClassParamsAndValues") {
    scenario("Convert simple case class to Map") {
      case class TestClass(a: String, b: String)
      val testObject = TestClass("a1", "b1")

      Tools.getCaseClassParamsAndValues(testObject) should equal(Map("a" -> "a1", "b" -> "b1"))
    }
  }
}
