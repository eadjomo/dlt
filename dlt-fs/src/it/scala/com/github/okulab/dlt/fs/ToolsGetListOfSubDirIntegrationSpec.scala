package com.github.okulab.dlt.fs

import com.github.okulab.dlt.test.{LocalClusterSpec, SparkSqlSpec}
import org.apache.hadoop.fs.Path
import org.scalatest.{FeatureSpec, Matchers}

/**
  * ToolsGetListOfSubDirIntegrationSpec
  *
  * @author #okulab-developers<team.dev@okulab.co>
  */
class ToolsGetListOfSubDirIntegrationSpec extends FeatureSpec with LocalClusterSpec with Matchers with SparkSqlSpec {
  override def beforeAll(): Unit = {
    super.beforeAll()
     fsContext.mkdirs(new Path("/tmp/hadoop/utils"))
  }

  feature("Get list of all the sub-directory of a given folder") {
    scenario("simple test") {
      fsContext.mkdirs(new Path("/tmp/hadoop/utils/1/"))
      fsContext.mkdirs(new Path("/tmp/hadoop/utils/2/"))
      fsContext.mkdirs(new Path("/tmp/hadoop/utils/3/"))
      fsContext.mkdirs(new Path("/tmp/hadoop/utils/4/"))
      fsContext.mkdirs(new Path("/tmp/hadoop/utils/5/"))
      Utils(fsContext).getListOfSubDir(new Path("/tmp/hadoop/utils/")).count(_ => true) should equal(5)
    }
  }
}
