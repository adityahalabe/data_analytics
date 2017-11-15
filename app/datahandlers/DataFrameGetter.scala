package datahandlers

import helpers.SparkContextGetter
import org.apache.commons.io.FilenameUtils
import org.apache.spark.sql.types.StructType

/**
 * Created by ahm2320 on 11/11/17.
 */
trait DataFrameGetter {

  def getDataFrameForCsv(fileName : String,customSchema : StructType) = {
    val sqlContext = SparkContextGetter.getSparkContext.sqlContext
    val df = sqlContext.read
     .format("com.databricks.spark.csv")
     .schema(customSchema)
     .option("header", "true")
     .option("inferSchema", "true")
     .load(s"public/$fileName")

    df.createOrReplaceTempView(FilenameUtils.getBaseName(fileName))
    df
  }

}
