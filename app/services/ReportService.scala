package services

import com.google.inject.Inject
import datahandlers.DataFrameHandler
import model._

import scala.collection.JavaConverters._
import org.apache.spark.sql.functions._
/**
  * Created by ahm2320 on 11/11/17.
  */
class ReportService @Inject() (dataFrameHandler: DataFrameHandler){

   def getCountryWithAirportReport()  = {

     val airportCountryJoin = dataFrameHandler.getAirportWithCountryJoin()
                                       .groupBy("country_name")
                                       .agg(count("airport_name") as "airport_count")

     val topCountries = airportCountryJoin.sort(desc("airport_count")).take(10)
     val bottomCountries = airportCountryJoin.sort(asc("airport_count")).take(10)
     val top = topCountries.map(row => CountryWithAirport(row.getAs[String]("country_name"),
                                                          row.getAs[Long]("airport_count"))).toList
     val bottom = bottomCountries.map(row => CountryWithAirport(row.getAs[String]("country_name"),
                                                                row.getAs[Long]("airport_count"))).toList
     CountryWithAirportReport(top,bottom)

   }
  def getCountryWithSurfaceReport()  = {

    val runwayJoin = dataFrameHandler.getAllJoin()
                                      .groupBy("country_name")
                                      .agg(count("surface") as "surface_count")

    val topCountries = runwayJoin.sort(desc("surface_count")).collectAsList().asScala
    val list = topCountries.map(row => CountryWithSurface(row.getAs[String]("country_name"),
                                                          row.getAs[Long]("surface_count"))).toList
    CountryWithSurfaceReport(list)
  }

  def getTopRunwayIdentReport()  = {

    val runwayDf = dataFrameHandler.getRunwayDataFrame().groupBy("le_ident")
                                      .agg(count("le_ident") as "total_count")
                                      .sort(desc("total_count"))
                                      .take(10)
    val list = runwayDf.map(row => RunwayWithCount(row.getAs[String]("le_ident"),row.getAs[Long]("total_count"))).toList

    RunwayWithCountReport(list)
  }
 }