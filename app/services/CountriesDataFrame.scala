package services

import org.apache.spark.sql.types.{StringType, IntegerType, StructField, StructType}

/**
 * Created by ahm2320 on 11/11/17.
 */
trait CountriesDataFrame extends DataFrameGetter{

  def getCountryDataFrame(countryCsv : String) = {
    val customSchema: StructType = StructType(Array(
      StructField("id", IntegerType, false),
      StructField("code", StringType, false),
      StructField("name", StringType, false),
      StructField("continent", StringType, false),
      StructField("wikipedia_link", StringType, false),
      StructField("keywords", StringType, true)))

    getDataFrameForCsv(countryCsv,customSchema)
              .withColumnRenamed("name","country_name")
              .withColumnRenamed("id","country_id")

  }

}
