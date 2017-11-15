package services

import org.apache.spark.sql.types._

/**
  * Created by ahm2320 on 11/11/17.
  */
trait AirportsDataFrame extends DataFrameGetter{

   def getAirportDataFrame(airportCsv : String) = {
     val customSchema: StructType = StructType(Array(
       StructField("id", IntegerType, false),
       StructField("ident", StringType, false),
       StructField("type", StringType, false),
       StructField("name", StringType, false),
       StructField("latitude_deg", DoubleType, true),
       StructField("longitude_deg", DoubleType, true),
       StructField("elevation_ft", IntegerType, true),
       StructField("continent", StringType, true),
       StructField("iso_country", StringType, true),
       StructField("iso_region", StringType, true),
       StructField("municipality", StringType, true),
       StructField("scheduled_service", StringType, true),
       StructField("gps_code", StringType, true),
       StructField("iata_code", StringType, true),
       StructField("home_link", StringType, true),
       StructField("wikipedia_link", StringType, true),
       StructField("keywords", StringType, true)
     ))

     getDataFrameForCsv(airportCsv,customSchema)
             .withColumnRenamed("name","airport_name")
             .withColumnRenamed("id","airport_id")
   }

 }
