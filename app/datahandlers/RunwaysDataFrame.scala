package datahandlers

import datahandlers.DataFrameGetter
import org.apache.spark.sql.types._

/**
  * Created by ahm2320 on 11/11/17.
  */
trait RunwaysDataFrame extends DataFrameGetter{

   def getRunwayDataFrame(runwayCsv : String) = {
     val customSchema: StructType = StructType(Array(
       StructField("id", IntegerType, false),
       StructField("airport_ref", StringType, false),
       StructField("airport_ident", StringType, false),
       StructField("length_ft", StringType, true),
       StructField("width_ft", StringType, true),
       StructField("surface", StringType, false),
       StructField("lighted", IntegerType, true),
       StructField("closed", IntegerType, true),
       StructField("le_ident", StringType, true),
       StructField("le_latitude_deg", DoubleType, true),
       StructField("le_longitude_deg", DoubleType, true),
       StructField("le_elevation_ft", DoubleType, true),
       StructField("le_heading_degT", DoubleType, true),
       StructField("le_displaced_threshold_ft", DoubleType, true),
       StructField("he_ident", StringType, true),
       StructField("he_latitude_deg", DoubleType, true),
       StructField("he_longitude_deg", DoubleType, true),
       StructField("he_elevation_ft", DoubleType, true),
       StructField("he_heading_degT", DoubleType, true),
       StructField("he_displaced_threshold_ft", DoubleType, true)
     ))

     getDataFrameForCsv(runwayCsv,customSchema)

   }

 }
