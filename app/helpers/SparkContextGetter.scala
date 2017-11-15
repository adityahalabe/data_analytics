package helpers

import org.apache.spark.sql.SparkSession

/**
 * Created by ahm2320 on 11/11/17.
 */
object SparkContextGetter {

  def getSparkContext = {
    SparkSession.builder
      .master("local")
      .appName("Data Analyser")
      .getOrCreate()
  }
}
