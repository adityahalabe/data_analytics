package services

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkContext, SparkConf}

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
