package com.github.okulab.dlt.commons.test

import com.github.okulab.dlt.commons.tools.Tools
import org.apache.spark.sql.types.DataTypes.{LongType, StringType, createMapType,createDecimalType}
import org.apache.spark.sql.types._
import org.scalatest._


/**
  * ToolsEvalDataTypesUnitSpec
  *
  * @author #okulab-developers<team.dev@okulab.co>
  */
class ToolsEvalDataTypesUnitSpec extends FunSuite with Matchers with BeforeAndAfter {
  test("Type is int Tools.evalDataTypes(int) should be int ") {
    assert(Tools.toHIveString(IntegerType) equals "int")
  }

  test("Type is Integer Tools.evalDataTypes(Integer) should be int") {
    assert(Tools.toHIveString(IntegerType) equals "int")
  }

  test("Type is float Tools.evalDataTypes(float) should be float") {
    assert(Tools.toHIveString(FloatType) equals "float")
  }

  test("Type is decimal Tools.evalDataTypes(decimal) should be decimal") {
    assert(Tools.toHIveString(createDecimalType(12,2)) equals "decimal(12,2)")
  }

  test("Type is string Tools.evalDataTypes(string) should be string") {
    assert(Tools.toHIveString(StringType) equals "string")
  }

  test("Type is DATE Tools.evalDataTypes(DATE) should be date") {
    assert(Tools.toHIveString(DateType).equals("date"))
  }

  test("Type is Timestamp Tools.evalDataTypes(Timestamp) should be Timestamp") {
    assert(Tools.toHIveString(TimestampType).equals("timestamp"))
  }

  test("Type is DOUBLE Tools.evalDataTypes(DOUBLE) should be double") {
    assert(Tools.toHIveString(DoubleType).equals("double"))
  }

  test("Type STRUCT with Map") {
    Tools.toHIveString(
      StructType(StructField("longs2strings", createMapType(LongType, StringType), nullable = false) :: Nil)
    ).equals("struct<longs2strings:map<bigint,string>>")
  }

  test("Type STRUCT<city:STRING, state:STRING>is DOUBLE Tools.evalDataTypes(STRUCT)") {
    Tools.toHIveString(
      StructType(StructField("city", StringType):: StructField("state", StringType, nullable = false) :: Nil)
    ).equals("struct<city:string,state:string>")
  }
}
